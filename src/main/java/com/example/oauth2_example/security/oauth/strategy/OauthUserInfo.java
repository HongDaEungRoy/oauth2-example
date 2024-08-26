package com.example.oauth2_example.security.oauth.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OauthUserInfo {
    private String name;
    private String username;
    private String userProfileImage;
}
