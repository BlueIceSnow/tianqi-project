package com.tianqi.movie.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/20 21:29
 * @Description:
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("demo-aa");
        remoteTokenServices.setClientSecret("123456");
//        remoteTokenServices.setAccessTokenConverter(jwtAccessTokenConverter());
        remoteTokenServices.setCheckTokenEndpointUrl("http://project-auth-service-dev/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate);
        return remoteTokenServices;
    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
//        return jwtTokenStore;
//    }
//
//    @SneakyThrows
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
//        rsa.initialize(2048);
//        KeyPair keyPair = rsa.generateKeyPair();
//        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//        tokenConverter.setKeyPair(keyPair);
//        return tokenConverter;
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
