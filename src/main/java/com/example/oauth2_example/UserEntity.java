package com.example.oauth2_example;

import lombok.Getter;

@Getter
public class UserEntity {
    private String username;
    private String name;
    private String userProfileImage;

    public UserEntity() {}

    public UserEntity(String username, String name, String userProfileImage) {
        this.username = username;
        this.name = name;
        this.userProfileImage = userProfileImage;
    }
}
