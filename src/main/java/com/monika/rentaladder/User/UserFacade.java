package com.monika.rentaladder.User;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class UserFacade {

    private UserRepository userRepository;

    public UserFacade(UserRepository userRepository){
            this.userRepository = userRepository;
    }

    public void addUser(UserEntity user){
        userRepository.save(user);
        }

    public UserEntity getUserByName(String name){
        return userRepository.findByName(name);
    }
}
