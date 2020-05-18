package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.InMemoryItemRepository;
import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalFacadeTest {

    ItemRepository itemRepository = new InMemoryItemRepository();
    RentalFacade rentalFacade = new RentalConfiguration().rentalFacade(itemRepository, null);

    @Test
    void rentItemTest() {

        //what you have
        Long testItemId = 9L;
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(testItemId);
        testItemEntity.setRented(false);
        itemRepository.save(testItemEntity);
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setUserName("Monika");

        //than what do you do with what you have
        rentalFacade.rentItem(testRentalEntity);

        //check result (is rented)
        assertTrue(rentalFacade.isItemRented(testItemId));
        assertTrue(itemRepository.findById(testItemId).isRented());
    }

    @Test
    void returnItemTest() {

        //what you have
        Long testItemId = 9L;
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(testItemId);
        testItemEntity.setRented(true);
        itemRepository.save(testItemEntity);
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setUserName("Monika");


        rentalFacade.rentItem(testRentalEntity);

        //when
        RentalEntity result = rentalFacade.returnItem(testItemId);

        //then
        assertTrue(result.getReturnDate()!=null);
        assertFalse(rentalFacade.isItemRented(testItemId));
        assertFalse(itemRepository.findById(testItemId).isRented());
    }
}