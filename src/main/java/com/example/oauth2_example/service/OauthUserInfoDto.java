package com.example.oauth2_example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OauthUserInfoDto {
    private String name;
    private String username;
    private String userProfileImage;
}
