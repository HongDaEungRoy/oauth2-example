package com.example.oauth2_example.service;

import com.example.oauth2_example.entity.EntityRepository;
import com.example.oauth2_example.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Oauth2UserService {

    private final EntityRepository entityRepository;

    public void persistUserInfo(OauthUserInfoDto userInfo) {
        UserEntity userEntity = entityRepository.findByUsername(userInfo.getUsername())
                .orElse(new UserEntity(userInfo.getUsername(), userInfo.getName(), userInfo.getUserProfileImage()));
        entityRepository.save(userEntity);
    }
}
