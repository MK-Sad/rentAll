package com.monika.rentaladder.User;

import org.springframework.context.annotation.Bean;

public class UserConfiguration {

    UserFacade userFacade(){
        return new UserFacade(new InMemoryUserRepository());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository){
        return new UserFacade(userRepository);
    }
}
