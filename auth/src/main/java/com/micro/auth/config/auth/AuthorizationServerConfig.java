package com.micro.auth.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

/**
 * Configuration of authorization server
 *
 * @author Sergey Bezvershenko
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Custom JWT token converter
     */
    @Bean
    public AuthTokenConverter tokenConverter() {
        AuthTokenConverter enhancer = new AuthTokenConverter();

        String pa = "micro1234";
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("security/micro.jks"), pa.toCharArray())
                .getKeyPair("micro", pa.toCharArray());

        enhancer.setKeyPair(keyPair);

        return enhancer;
    }

    /**
     * Configuration of clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("micro")
                .secret("micro1234")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .scopes("read", "write")
                .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                .refreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(10));
    }

    /**
     * Endpoints configuration
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(tokenConverter());
    }
}
