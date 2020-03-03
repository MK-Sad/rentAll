package com.monika.rentaladder.User;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class UserFacade {

    @Autowired
    private UserRepository userRepository;

    public void add(UserEntity user){
        userRepository.save(user);
        }

    public List<UserEntity> searchByName(String name){
        return Collections.emptyList();
    }
}
