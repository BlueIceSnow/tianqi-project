package com.tianqi.auth.config.security.authentication;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianqi.client.config.security.authorization.JwtAuthenticationToken;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.pojo.JwtUserClaims;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rest.entity.ValidateEntity;
import com.tianqi.common.util.SpringUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/15 10:22
 * @Description: 调用认证管理器进行认证
 */
public class JwtTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String APP_KEY = "appKey";

    public JwtTokenAuthenticationFilter() {
        setFilterProcessesUrl("/token");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response)
            throws AuthenticationException {
        final StringBuilder requestParamStr = new StringBuilder();
        try (final BufferedReader reader =
                     new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line = "";

            while ((line = reader.readLine()) != null) {
                requestParamStr.append(line);
            }
        } catch (final Exception e) {
            logger.error("用户登录请求参数流存在问题.");
        }
        final JSONObject params = JSON.parseObject(requestParamStr.toString());
        final List<ValidateEntity> validateEntities = validateParams(params);
        if (validateEntities.size() != 0) {
            final ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
            final ResultEntity<Object> result =
                    RestResult.builder().withValidates(validateEntities)
                            .withStatus(StatusEnum.PARAM_PARSE_ERROR).build();
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().write(objectMapper.writeValueAsBytes(result));
            } catch (final IOException e) {
                logger.error("write login filter validate fail!");
            }
            return null;
        }
        final JwtAuthenticationToken authenticationToken =
                new JwtAuthenticationToken(null);
        final String username = params.getString(USERNAME);
        final String password = params.getString(PASSWORD);
        final String appKey = params.getString(APP_KEY);
        final JwtUserClaims jwtUserClaims = new JwtUserClaims();
        jwtUserClaims.setUsername(username);
        authenticationToken.setPassword(password);
        jwtUserClaims.setAppKey(appKey);
        authenticationToken.setDetails(jwtUserClaims);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    /**
     * 校验参数.
     *
     * @param: request
     * @param: response
     * @return: boolean
     * @date: 2021/10/21 16:20:33
     */
    public List<ValidateEntity> validateParams(final JSONObject params) {

        final String username = params.getString(USERNAME);
        final String password = params.getString(PASSWORD);
        final String appKey = params.getString(APP_KEY);
        final List<ValidateEntity> validateEntities = new ArrayList<>();
        if (StrUtil.isEmpty(username)) {
            validateEntities.add(new ValidateEntity("username", "用户名不能为空!"));
        }
        if (StrUtil.isEmpty(password)) {
            validateEntities.add(new ValidateEntity("password", "密码不能为空!"));
        }
        if (StrUtil.isEmpty(appKey)) {
            validateEntities.add(new ValidateEntity("appKey", "应用ID不能为空!"));
        }
        return validateEntities;
    }

}
