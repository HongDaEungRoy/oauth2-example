package com.example.oauth2_example.security.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OauthUserInfo {
    private String name;
    private String username;
    private String userProfileImage;
}
