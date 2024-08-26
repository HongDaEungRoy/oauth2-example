package com.example.oauth2_example.security.oauth.strategy;

import com.example.oauth2_example.security.oauth.OauthUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

public interface Oauth2PlatformStrategy {
    String getPlatform();
    OauthUserInfo extractUserInfo(OAuth2User oauth2User);

    default String getStringAttribute(Map<String, Object> attributes, String key) {
        return Optional.ofNullable(attributes)
                .map(attr -> (String) attr.get(key))
                .orElse("");
    }
}
