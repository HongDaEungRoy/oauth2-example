package com.example.oauth2_example.security;


import com.example.oauth2_example.security.jwt.JwtUtil;
import com.example.oauth2_example.security.oauth.Oauth2PlatformStrategyFactory;
import com.example.oauth2_example.security.oauth.OauthUserInfo;
import com.example.oauth2_example.security.oauth.OauthUserInfoPersist;
import com.example.oauth2_example.security.oauth.strategy.Oauth2PlatformStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final OauthUserInfoPersist oauthUserInfoPersist;
    private final Oauth2PlatformStrategyFactory oauth2PlatformStrategyFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String platForm = ((OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getAuthorizedClientRegistrationId();
        Oauth2PlatformStrategy platformStrategy = oauth2PlatformStrategyFactory.getOauth2PlatformStrategy(platForm);
        OauthUserInfo userInfo = platformStrategy.extractUserInfo(oAuth2User);

        oauthUserInfoPersist.persistUserInfo(userInfo);

        String token = jwtUtil.generateToken(new CustomUserDetails(userInfo.getUsername(), userInfo.getName(), userInfo.getUserProfileImage()));
        // 원래는 프론트에서 redirect 하도록 함.
        // getRedirectStrategy().sendRedirect(request, response, "http://localhost:프론트포트/oauth2/redirect?token=" + token);

        // 아래는 token 정보를 반환하기 위한 임시 코드
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"token\":\"%s\"}", token));
    }
}
