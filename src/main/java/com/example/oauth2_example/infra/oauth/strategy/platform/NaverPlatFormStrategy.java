package com.example.oauth2_example.infra.oauth.strategy.platform;

import com.example.oauth2_example.service.OauthUserInfoDto;
import com.example.oauth2_example.infra.oauth.strategy.Oauth2PlatformStrategy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NaverPlatFormStrategy implements Oauth2PlatformStrategy {

    @Override
    public String getPlatform() {
        return "naver";
    }

    @Override
    public OauthUserInfoDto extractUserInfo(OAuth2User oauth2User) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) oauth2User.getAttributes().get("response");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = getStringAttribute(profile, "nickname");
        String profileImageUrl = getStringAttribute(profile, "profile_image");
        String name = getStringAttribute(profile, "name");

        return new OauthUserInfoDto(name, nickname, profileImageUrl);
    }
}
