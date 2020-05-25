package com.monika.rentaladder.User;

import com.monika.rentaladder.User.MailSender.RentalEvent;
import com.monika.rentaladder.User.UserDTOs.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private UserRepository userRepository = new InMemoryUserRepository();
    private UserFacade userFacade = new UserConfiguration().userFacade(userRepository);
    private ApplicationEventPublisher publisher;

    @Test
    void updateUser() throws Exception {
        //given
        UserEntity user = new UserEntity();
        user.setName("Monika");
        user.setPassword("123");
        user.setPhoneNumber("123123123");
        user.seteMail("sbcdef@yahoo.com");
        userFacade.addUser(user);
        user.seteMail("abc@gmail.com");

        //when
        UserEntity result = userFacade.updateUser(user);

        //then
        assertEquals("abc@gmail.com", result.geteMail());
        assertEquals(0, result.getPoints());
    }

    @Test
    void addingPointsOnRentalTest() {
        //given
        UserEntity user = new UserEntity();
        RentalEvent rentalEvent = null;

        //when
        //publisher.publishEvent(rentalEvent);

        //then
    }
}