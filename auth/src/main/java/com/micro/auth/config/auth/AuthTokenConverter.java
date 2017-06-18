package com.micro.auth.config.auth;

import com.micro.auth.domain.user.UserDetails;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Customization of JwtAccessTokenConverter.
 * Add the additional information to JWT token:
 * authorities("authorities") and user details("extra")
 *
 * @author Sergey Bezvershenko
 */
public class AuthTokenConverter extends JwtAccessTokenConverter {
    private static final String EXTRA = "extra";

    /**
     * Add additional information to token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);

        TokenExtra tokenEx = TokenExtra.builder()
                .userId(user.getId())
                .build();

        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        token.getAdditionalInformation().put(AUTHORITIES, authorities);
        token.getAdditionalInformation().put(EXTRA, tokenEx);

        return super.enhance(token, authentication);
    }

    /**
     * Extract additional information from Map
     */
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);

        Object obj = map.get(EXTRA);
        if (obj != null && obj instanceof Map) {
            Map extraMap = (Map) obj;
            TokenExtra tokenEx = TokenExtra.builder()
                    .userId(NumberUtils.toLong(String.valueOf(extraMap.get("userId"))))
                    .build();

            authentication.getOAuth2Request().getExtensions().put(EXTRA, tokenEx);
        }

        return authentication;
    }
}
