package com.monika.rentaladder.User;

public class UserFacade {

    private UserRepository userRepository;

    public UserFacade(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity getUserByName(String name){
        return userRepository.findByName(name);
    }

    public UserDTO authenticateUser(UserDTO user) throws Exception {
        UserEntity userEntity = userRepository.findByName(user.getName());
        if ((!user.getPassword().equals(userEntity.getPassword())) || userEntity==null) {
            throw new Exception("Wrong name or password");
        }
        return user.noPassword();
    }

}
