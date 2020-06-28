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

class RentalHtmlControllerTest {

    @Mock
    ApplicationEventPublisher mockPublisher;

    ItemRepository itemRepository = new InMemoryItemRepository();

    RentalFacade rentalFacade;

    RentalHtmlController rentalHtmlController;

    @Test
    void confirmRentalTest() {

        MockitoAnnotations.initMocks(this);
        rentalFacade = new RentalConfiguration().rentalFacade(itemRepository, mockPublisher);
        rentalHtmlController = new RentalHtmlController(rentalFacade);

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
        RentalEvent requestEvent = new RentalEvent(RentalEvent.Type.REQUEST, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        RentalEntity result = rentalFacade.rentItem(testRentalEntity);

        //check result (is requested)
        assertTrue(rentalFacade.isItemRented(testItemId));
        assertTrue(itemRepository.findById(testItemId).isRented());
        assertTrue(result.getRentalDate() != null);
        verify(mockPublisher).publishEvent(requestEvent);


        //than
        RentalEvent confirmedEvent = new RentalEvent(RentalEvent.Type.CONTACT, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        String resultPageName = rentalHtmlController.confirmRental(2L);

        //check result (is rented)
        assertTrue("confirmed".equals(resultPageName));
        //verify(mockPublisher).publishEvent(confirmedEvent);

        //than
        resultPageName = rentalHtmlController.confirmRental(2L);

        //check result (is rented)
        assertTrue("alreadyClicked".equals(resultPageName));
    }

    @Test
    void denyRentalTest() {
        MockitoAnnotations.initMocks(this);
        rentalFacade = new RentalConfiguration().rentalFacade(itemRepository, mockPublisher);
        rentalHtmlController = new RentalHtmlController(rentalFacade);

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
        RentalEvent requestEvent = new RentalEvent(RentalEvent.Type.REQUEST, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        RentalEntity result = rentalFacade.rentItem(testRentalEntity);

        //check result (is requested)
        assertTrue(rentalFacade.isItemRented(testItemId));
        assertTrue(itemRepository.findById(testItemId).isRented());
        assertTrue(result.getRentalDate() != null);
        verify(mockPublisher).publishEvent(requestEvent);


        //than
        RentalEvent deniedEvent = new RentalEvent(RentalEvent.Type.CONTACT, testRentalEntity.getUserName(),
                testRentalEntity.getOwnerName(), testRentalEntity.getItemName(), testRentalEntity.getId());
        String resultPageName = rentalHtmlController.denyRental(2L);

        //check result (is rented)
        assertTrue("deny".equals(resultPageName));
        //verify(mockPublisher).publishEvent(confirmedEvent);

        //than
        resultPageName = rentalHtmlController.denyRental(2L);

        //check result (is rented)
        assertTrue("alreadyClicked".equals(resultPageName));
    }
}