package com.monika.rentaladder.User;

import com.monika.rentaladder.User.MailSender.MailService;
import com.monika.rentaladder.User.MailSender.RentalEvent;
import com.monika.rentaladder.User.MailSender.RentalEventHandler;
import com.monika.rentaladder.User.UserDTOs.UserEntity;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class RentalEventHandlerTest {

    @Spy
    UserFacade userFacade = new UserConfiguration().userFacade();

    @Mock
    MailService mailService;

    @InjectMocks
    RentalEventHandler rentalEventHandler;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addingPointsOnRentalTest() throws Exception {
        //given
        UserEntity user = new UserEntity();
        user.setName("Monika");
        user.setPoints(0);
        UserEntity owner = new UserEntity();
        owner.setName("Kazik");
        owner.setPoints(0);
        userFacade.addUser(user);
        userFacade.addUser(owner);
        RentalEvent rentalEvent = new RentalEvent(RentalEvent.Type.RETURN_DELAYED, user.getName(),
                owner.getName(), "item name",  2L);

        //when
        rentalEventHandler.eventListener(rentalEvent);

        //then
        user = userFacade.getUserByName(user.getName());
        assertEquals(-10, user.getPoints());
    }
}