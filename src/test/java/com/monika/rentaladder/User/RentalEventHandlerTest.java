package com.monika.rentaladder.User;

import com.monika.rentaladder.User.MailSender.MailService;
import com.monika.rentaladder.User.MailSender.RentalEvent;
import com.monika.rentaladder.User.MailSender.RentalEventHandler;
import com.monika.rentaladder.User.UserDTOs.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RentalEventHandlerTest {

    @Spy
    UserFacade userFacade = new UserConfiguration().userFacade();

    @Mock
    MailService mockMailService;

    @InjectMocks
    RentalEventHandler rentalEventHandler;

    @Captor
    ArgumentCaptor<UserEntity> captorUser;
    @Captor
    ArgumentCaptor<UserEntity> captorOwner;
    @Captor
    ArgumentCaptor<String> captorName;
    @Captor
    ArgumentCaptor<Long> captorId;

    @Test
    void addingPointsOnRentalTest() throws Exception {
        MockitoAnnotations.initMocks(this);
        //given
        UserEntity user = new UserEntity();
        user.setName("Monika");
        user.setPoints(0);
        UserEntity owner = new UserEntity();
        owner.setName("Kazik");
        owner.setPoints(0);
        userFacade.addUser(user);
        userFacade.addUser(owner);
        RentalEvent rentalEvent = new RentalEvent(RentalEvent.Type.CONTACT, user.getName(),
                owner.getName(), "item name",  2L);

        //when
        rentalEventHandler.eventListener(rentalEvent);

        //then
        user = userFacade.getUserByName(owner.getName());
        assertEquals(+2, user.getPoints());
        //ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(mockMailService).sendOwnerDataMail(captorUser.capture(), captorOwner.capture(),
                captorName.capture(), captorId.capture());
        assertEquals("Monika", captorUser.getValue().getName());
        assertEquals("Kazik", captorOwner.getValue().getName());
        assertEquals("item name", captorName.getValue());
        assertEquals(2L, captorId.getValue());
    }

    @Test
    void addingPointsReturnDelayedTest() throws Exception {
        MockitoAnnotations.initMocks(this);
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
        UserEntity updatedUser = userFacade.getUserByName(user.getName());
        assertEquals(-10, updatedUser.getPoints());
    }
}