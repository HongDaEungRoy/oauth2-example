package com.example.oauth2_example.security.oauth.strategy.impl;

import com.example.oauth2_example.security.oauth.strategy.Oauth2PlatformStrategy;
import com.example.oauth2_example.security.oauth.strategy.OauthUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class KakaoPlatformStrategy implements Oauth2PlatformStrategy {

    @Override
    public String getPlatform() {
        return "kakao";
    }

    @Override
    public OauthUserInfo extractUserInfo(OAuth2User oauth2User) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) oauth2User.getAttributes().get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = getStringAttribute(profile, "nickname");
        String profileImageUrl = getStringAttribute(profile, "profile_image_url");

        // name은 현재 사용할 수 없으므로 빈 문자열로 설정
        String name = "";

        return new OauthUserInfo(name, nickname, profileImageUrl);
    }

    private String getStringAttribute(Map<String, Object> attributes, String key) {
        return Optional.ofNullable(attributes)
                .map(attr -> (String) attr.get(key))
                .orElse("");
    }
}

