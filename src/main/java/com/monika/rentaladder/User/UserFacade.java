package com.monika.rentaladder.User;

import com.monika.rentaladder.User.UserDTOs.UserCredentials;
import com.monika.rentaladder.User.UserDTOs.UserEntity;
import com.monika.rentaladder.User.UserDTOs.UserPoints;

public class UserFacade {

    private UserRepository userRepository;

    public UserFacade(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity user) throws Exception {
        UserEntity userEntity = userRepository.findByName(user.getName());
        if (userEntity!=null) {
            throw new Exception("Name already exist");
        }
        user.setPoints(0);
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity getUserByName(String name){
        return userRepository.findByName(name);
    }

    public UserPoints getPointsByName(String name){
        return new UserPoints(userRepository.findByName(name));
    }

    public UserPoints authenticateUser(UserCredentials user) throws Exception {
        UserEntity userEntity = userRepository.findByName(user.getName());
        if ((!user.getPassword().equals(userEntity.getPassword())) || userEntity==null) {
            throw new Exception("Wrong name or password");
        }
        return new UserPoints(userEntity);
    }

}
