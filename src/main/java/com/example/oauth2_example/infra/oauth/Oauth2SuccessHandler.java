package com.example.oauth2_example.infra.oauth;


import com.example.oauth2_example.infra.security.CustomUserDetails;
import com.example.oauth2_example.infra.jwt.JwtUtil;
import com.example.oauth2_example.service.OauthUserInfoDto;
import com.example.oauth2_example.infra.oauth.strategy.Oauth2PlatformStrategy;
import com.example.oauth2_example.service.Oauth2UserService;
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
    private final Oauth2UserService oauth2UserService;
    private final Oauth2PlatformStrategyFactory oauth2PlatformStrategyFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String platForm = ((OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getAuthorizedClientRegistrationId();
        Oauth2PlatformStrategy platformStrategy = oauth2PlatformStrategyFactory.getOauth2PlatformStrategy(platForm);
        OauthUserInfoDto userInfo = platformStrategy.extractUserInfo(oAuth2User);

        oauth2UserService.persistUserInfo(userInfo);

        String token = jwtUtil.generateToken(new CustomUserDetails(userInfo.getUsername(), userInfo.getName(), userInfo.getUserProfileImage()));
        // 원래는 프론트에서 redirect 하도록 함.
        // getRedirectStrategy().sendRedirect(request, response, "http://localhost:프론트포트/oauth2/redirect?token=" + token);

        // 아래는 token 정보를 반환하기 위한 임시 코드
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"token\":\"%s\"}", token));
    }
}
