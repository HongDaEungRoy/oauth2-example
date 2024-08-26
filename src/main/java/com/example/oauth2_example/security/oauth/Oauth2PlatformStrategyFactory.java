package com.example.oauth2_example.security.oauth;

import com.example.oauth2_example.security.oauth.strategy.Oauth2PlatformStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Oauth2PlatformStrategyFactory {
    private final List<Oauth2PlatformStrategy> oauth2PlatformStrategies;

    public Oauth2PlatformStrategy getOauth2PlatformStrategy(String platform) {
        return oauth2PlatformStrategies.stream()
                .filter(strategy -> strategy.getPlatform().equals(platform))
                .findFirst()
                .orElseThrow();
    }

}
