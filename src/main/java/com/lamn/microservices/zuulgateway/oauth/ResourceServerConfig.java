package com.lamn.microservices.zuulgateway.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * The type Resource server config.
 */
@Configuration
@EnableResourceServer
@RefreshScope
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/security/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/products/products",
                        "/api/items/items",
                        "/api/users/users").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/products/products/{id}",
                        "/api/items/items/{id}/count/{count}",
                        "/api/users/users/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/products/**",
                        "/api/items/**",
                        "/api/users/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    /**
     * Access token converter jwt access token converter.
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtKey);
        return tokenConverter;
    }


    /**
     * Token store jwt token store.
     *
     * @return the jwt token store
     */
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
}
