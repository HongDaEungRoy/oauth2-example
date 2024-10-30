package com.example.oauth2_example.infra.oauth.strategy;

import com.example.oauth2_example.service.OauthUserInfoDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

public interface Oauth2PlatformStrategy {
    String getPlatform();
    OauthUserInfoDto extractUserInfo(OAuth2User oauth2User);

    default String getStringAttribute(Map<String, Object> attributes, String key) {
        return Optional.ofNullable(attributes)
                .map(attr -> (String) attr.get(key))
                .orElse("");
    }
}
