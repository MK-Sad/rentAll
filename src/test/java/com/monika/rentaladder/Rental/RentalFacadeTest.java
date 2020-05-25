package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.InMemoryItemRepository;
import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemRepository;
import com.monika.rentaladder.User.MailSender.RentalEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RentalFacadeTest {

    @Mock
    ApplicationEventPublisher mockPublisher;

    ItemRepository itemRepository = new InMemoryItemRepository();

    RentalFacade rentalFacade;

    @Test
    void rentAndConfirmedItemTest() {

        MockitoAnnotations.initMocks(this);
        rentalFacade = new RentalConfiguration().rentalFacade(itemRepository, mockPublisher);

        //what you have
        Long testItemId = 9L;
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(testItemId);
        testItemEntity.setRented(false);
        itemRepository.save(testItemEntity);
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setItemName("Klocek");
        testRentalEntity.setUserName("Monika");
        testRentalEntity.setOwnerName("Kazik");

        //than what do you do with what you have
        RentalEntity result = rentalFacade.rentItem(testRentalEntity);

        //check result (is requested)
        RentalEvent expectedRequestEvent = new RentalEvent(RentalEvent.Type.REQUEST, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        assertTrue(rentalFacade.isItemRented(testItemId));
        assertTrue(itemRepository.findById(testItemId).isRented());
        assertTrue(result.getRentalDate() != null);
        verify(mockPublisher).publishEvent(expectedRequestEvent);

        //when
        RentalEvent confirmedEvent = new RentalEvent(RentalEvent.Type.CONTACT, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        RentalEntity resultConfirmed = rentalFacade.confirmRental(2L);

        //check result (is rented)
        assertTrue(resultConfirmed.getConfirmedDate() != null);
        verify(mockPublisher).publishEvent(confirmedEvent);
    }

    @Test
    void returnItemTest() {

        MockitoAnnotations.initMocks(this);
        rentalFacade = new RentalConfiguration().rentalFacade(itemRepository, mockPublisher);

        //what you have
        Long testItemId = 9L;
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(testItemId);
        testItemEntity.setRented(true);
        itemRepository.save(testItemEntity);
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setRentalPeriod(3);
        testRentalEntity.setItemName("Klocek");
        testRentalEntity.setUserName("Monika");
        testRentalEntity.setOwnerName("Kazik");
        rentalFacade.rentItem(testRentalEntity);
        rentalFacade.confirmRental(2L);

        //when
        RentalEntity result = rentalFacade.returnItem(testItemId);

        //then
        RentalEvent expectedInTimeEvent = new RentalEvent(RentalEvent.Type.RETURN_IN_TIME, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        assertTrue(result.getReturnDate() != null);
        assertFalse(rentalFacade.isItemRented(testItemId));
        assertFalse(itemRepository.findById(testItemId).isRented());
        verify(mockPublisher).publishEvent(expectedInTimeEvent);
    }
}