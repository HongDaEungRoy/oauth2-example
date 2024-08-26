package com.example.oauth2_example;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private String username;
    private String name;
    private String profileImage;
}
