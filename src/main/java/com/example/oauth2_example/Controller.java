package com.example.oauth2_example;


import com.example.oauth2_example.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {

     @GetMapping("/")
    public String home() {
        return "<a href='/login'>Login</a>";
    }

    @GetMapping("/login/google")
    public ResponseEntity<Map<String, String>> loginGoogle() {
         String googleUrl = "/oauth2/authorization/google";
        return ResponseEntity.ok(Collections.singletonMap("loginUrl", googleUrl));
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<?> loginKakao() {
         String kakaoUrl = "/oauth2/authorization/kakao";
        return ResponseEntity.ok(Collections.singletonMap("loginUrl", kakaoUrl));
    }

    @GetMapping("/login/naver")
    public ResponseEntity<?> loginNaver() {
         String naverUrl = "/oauth2/authorization/naver";
        return ResponseEntity.ok(Collections.singletonMap("loginUrl", naverUrl));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal CustomUserDetails user) {
        UserResponse userResponse = new UserResponse(user.getUsername(), user.getName(), user.getUserProfileImage());
        return ResponseEntity.ok(userResponse);
    }
}
