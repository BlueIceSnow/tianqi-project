package com.tianqi.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianqi.common.constant.SystemConstant;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.pojo.JwtUserClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/20 14:50
 * @Description:
 */
@Slf4j
public class SignUtil {

    public static final String PRIVATE_KEY_PATH =
            "/Users/yuantianqi/Desktop/keys/pri.key";
    public static final String PUBLIC_KEY_PATH =
            "/Users/yuantianqi/Desktop/keys/pub.key";
    public static final String AUDIENCE = "USER";
    public static final String ISSUER = "WWW.YTQ.COM";
    public static final String TOKEN_REDIS_KEY_PREFIX =
            SystemConstant.REDIS_PREFIX + "TOKEN:JTI:";

    static {
        final KeyPair secretKey = Keys.keyPairFor(SignatureAlgorithm.RS256);
        final PrivateKey aPrivate = secretKey.getPrivate();
        final PublicKey aPublic = secretKey.getPublic();
        final byte[] aPrivateEncoded = aPrivate.getEncoded();
        final byte[] aPublicEncoded = aPublic.getEncoded();
        final File privateFile = new File(PRIVATE_KEY_PATH);
        final File publicFile = new File(PUBLIC_KEY_PATH);
        if (!privateFile.exists() && !publicFile.exists()) {
            if (!privateFile.exists()) {
                privateFile.getParentFile().mkdirs();
            }
            if (!publicFile.exists()) {
                publicFile.getParentFile().mkdirs();
            }
            try (final FileOutputStream outputStream =
                         new FileOutputStream(privateFile)) {
                outputStream.write(aPrivateEncoded);
            } catch (final IOException e) {
                throw new BaseException("generator private key file error");
            }
            try (final FileOutputStream outputStream =
                         new FileOutputStream(publicFile)) {
                outputStream.write(aPublicEncoded);
            } catch (final IOException e) {
                throw new BaseException("generator public key file error");
            }
        }
    }

    public static void main(final String[] args) {
////        final JwtUserClaims blue =
////                new JwtUserClaims(1L, "blue", "1820846241", new ArrayList<>(),
////                        UUID.randomUUID().toString());
//        final String sign = sign(blue, 5, DateField.SECOND);
//        log.info("sign content: {}", sign);
//        try {
//            Thread.sleep(5000);
//            final JwtUserClaims jwtUserClaims = parseSign(sign);
//            log.info("parse sign content: {}", JSON.toJSONString(jwtUserClaims));
//        } catch (InterruptedException exception) {
//            log.error("thread sleep error");
//        } catch (ExpiredJwtException expiredJwtException) {
//            log.error("sign is expired");
//        }
        final JwtUserClaims jwtUserClaims = parseSign(
                "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE2MzA1Nzg1MTAsImF1ZCI6IlVTRVIiLCJpc3MiOiJXV1cuWVRRLkNPTSIsInN1YiI6eyJpZCI6MSwibmFtZSI6Inl1YW50aWFucWkiLCJ1c2VybmFtZSI6Inl1YW50aWFucWkiLCJyb2xlcyI6WyJERU1PIl0sImRhdGFQZXJtaXNzaW9ucyI6W10sInRlbmFudElkIjoxLCJvcmdDb2RlIjoiMTAwMDAxIiwib3JnSWQiOjEsImFwcEtleSI6IlhRSTd0blJweUZkZHNOTmZPIiwiYXBwSWQiOjEsImp0aSI6bnVsbH19.ZcW7UHWc8n91eD7N2CbWsY3RXg3WHOtJJhaCplhm3682Tt9FAqkJwOIIZ-KOS6S2LwZJBBkF_jnMp6YiXQkRwcsAGglnLhikosBa1bzMAhzgMlsJbEDdSCd6_WhAQ74jjlrXUUSQblyE4JaAqEEAJDtbsVclRlnTQ4Dd9QE-o57Gv_EYmkIJ_necY0RP35ddh8sGuMwcCYnJIZFA6T0g0oybQFGrvp0AxBfGmgQPvlstaiBo5NYWNn4He1WQID6x4JTmHy05y1VLuwcvsUPCqcs_hRc5gfbFnMU2rCoFIwkn0oXux-Ij49w1pUqUIasCuWo5mtsZucN9NtUSleNu4w");
        System.out.println(jwtUserClaims);
    }

    /**
     * read key from file
     *
     * @param keyPath
     * @return
     */
    private static byte[] readKey(final String keyPath) {
        final File file = new File(keyPath);
        final byte[] bytes;
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            bytes = IoUtil.readBytes(fileInputStream);
        } catch (final Exception exception) {
            if (log.isErrorEnabled()) {
                log.error("read key fail");
            }
            throw new BaseException("read public key fail");
        }
        return bytes;
    }

    /**
     * read private key from file
     *
     * @return
     */
    private static RSAPrivateKey readPrivateKey() {
        final byte[] bytes = readKey(PRIVATE_KEY_PATH);
        RSAPrivateKey rsaPrivateKey = null;
        try {
            rsaPrivateKey = RSAPrivateCrtKeyImpl.newKey(bytes);
        } catch (final Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("key file is error, can't generator private key");
            }
        }
        return rsaPrivateKey;
    }

    /**
     * read private key from file
     *
     * @return
     */
    private static RSAPublicKey readPublicKey() {
        final byte[] bytes = readKey(PUBLIC_KEY_PATH);
        RSAPublicKey rsaPublicKey = null;
        try {
            rsaPublicKey = RSAPublicKeyImpl.newKey(bytes);
        } catch (final Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("key file is error, can't generator public key");
            }
        }
        return rsaPublicKey;
    }


    /**
     * 令牌签名
     *
     * @param userClaims
     * @return
     */
    public static String sign(final JwtUserClaims userClaims, final int time,
                              final DateField dateField) {
        final RSAPrivateKey rsaPrivateKey = readPrivateKey();
        final Date expireDate = DateUtil.date()
                .offset(dateField, time).toJdkDate();
        if (userClaims.getJti() != null) {
            final StringRedisTemplate redisTemplate =
                    SpringUtil.getBean(StringRedisTemplate.class);
            final ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);
            final ValueOperations<String, String> stringValueOperations =
                    redisTemplate.opsForValue();
            try {
                stringValueOperations.set(TOKEN_REDIS_KEY_PREFIX + userClaims.getJti(),
                        mapper.writeValueAsString(userClaims),
                        expireDate.getTime(),
                        TimeUnit.MILLISECONDS);
            } catch (final JsonProcessingException e) {
                log.error("user sign is error, user claims convert to json error");
            }
        }
        final Claims claims = Jwts.claims();
        claims.setExpiration(expireDate)
                .setAudience(AUDIENCE)
                .setIssuer(ISSUER);
        final String sign = Jwts.builder()
                .signWith(rsaPrivateKey)
                .setClaims(claims)
                .claim("sub", userClaims)
                .compact();
        return sign;
    }

    /**
     * 解析签名
     *
     * @param sign
     * @return
     */
    public static JwtUserClaims parseSign(final String sign) throws JwtException {
        final RSAPublicKey rsaPublicKey = readPublicKey();
        final JwtUserClaims userClaims = Jwts.parserBuilder()
                .setSigningKey(rsaPublicKey)
                .deserializeJsonWith(
                        new JacksonDeserializer(
                                Maps.of("sub", JwtUserClaims.class).build()))
                .build()
                .parseClaimsJws(sign)
                .getBody().get("sub", JwtUserClaims.class);
        if (userClaims.getJti() != null) {
            final StringRedisTemplate redisTemplate =
                    SpringUtil.getBean(StringRedisTemplate.class);
            final String jti = redisTemplate.opsForValue()
                    .get(TOKEN_REDIS_KEY_PREFIX + userClaims.getJti());
            if (StringUtils.isEmpty(jti)) {
                throw new ExpiredJwtException(null, null, "jwt token is expire");
            }
        }
        return userClaims;
    }
}
