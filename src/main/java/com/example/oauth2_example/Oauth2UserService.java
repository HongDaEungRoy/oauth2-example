package com.example.oauth2_example;

import com.example.oauth2_example.security.oauth.OauthUserInfo;
import com.example.oauth2_example.security.oauth.OauthUserInfoPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Oauth2UserService implements OauthUserInfoPersist {

    private final EntityRepository entityRepository;

    @Override
    public void persistUserInfo(OauthUserInfo userInfo) {
        UserEntity userEntity = entityRepository.findByUsername(userInfo.getUsername())
                .orElse(new UserEntity(userInfo.getUsername(), userInfo.getName(), userInfo.getUserProfileImage()));
        entityRepository.save(userEntity);
    }
}
