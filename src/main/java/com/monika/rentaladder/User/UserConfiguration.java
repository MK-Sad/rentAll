package com.monika.rentaladder.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class UserConfiguration {

    UserFacade userFacade(){
        return new UserFacade(new InMemoryUserRepository(),
                new MailService(new JavaMailSenderImpl()));
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository, MailService mailService){
        return new UserFacade(userRepository, mailService);
    }
}
