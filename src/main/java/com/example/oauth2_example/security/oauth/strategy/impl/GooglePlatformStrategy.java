package com.example.oauth2_example.security.oauth.strategy.impl;

import com.example.oauth2_example.security.oauth.strategy.Oauth2PlatformStrategy;
import com.example.oauth2_example.security.oauth.strategy.OauthUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;


@Component
public class GooglePlatformStrategy implements Oauth2PlatformStrategy {

    @Override
    public String getPlatform() {
        return "google";
    }

    @Override
    public OauthUserInfo extractUserInfo(OAuth2User oauth2User) {
        String name = oauth2User.getAttribute("name");
        String username = oauth2User.getAttribute("email");
        String userProfileImage = oauth2User.getAttribute("profile");
        return new OauthUserInfo(name, username, userProfileImage);
    }
}
