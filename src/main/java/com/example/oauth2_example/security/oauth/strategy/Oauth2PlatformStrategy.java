package com.example.oauth2_example.security.oauth.strategy;

import com.example.oauth2_example.security.oauth.OauthUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Oauth2PlatformStrategy {
    String getPlatform();
    OauthUserInfo extractUserInfo(OAuth2User oauth2User);
}
