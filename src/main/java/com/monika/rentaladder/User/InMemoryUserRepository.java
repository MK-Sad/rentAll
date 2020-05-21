package com.monika.rentaladder.User;

import com.monika.rentaladder.User.UserDTOs.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository{

    private Map<String, UserEntity> map = new HashMap();

    @Override
    public UserEntity save(UserEntity userEntity) {
        map.put(userEntity.getName(), userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findByName(String name) {
        return map.get(name);
    }


}
