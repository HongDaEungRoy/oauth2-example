package com.example.oauth2_example.entity;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Getter
@Repository
public class EntityRepository {
    private final HashMap<String, UserEntity> db = new HashMap<>();

    public EntityRepository() {}

    /**
     * 사용자 이름으로 사용자를 찾습니다.
     *
     * @param name 찾을 사용자의 이름
     * @return 찾은 사용자 엔티티, 없으면 null
     */
    public Optional<UserEntity> findByUsername(String name) {
        return Optional.ofNullable(db.get(name));
    }

    /**
     * 사용자를 저장하거나 업데이트합니다.
     *
     * @param user 저장 또는 업데이트할 사용자 엔티티
     * @return 저장 또는 업데이트된 사용자 엔티티
     */
    public void save(UserEntity user) {
        db.put(user.getUsername(), user);
    }
}

