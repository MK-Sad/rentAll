package com.monika.rentaladder.Rental;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalFacadeTest {

    RentalFacade rentalFacade = new RentalConfiguration().rentalFacade();

    @Test
    void rentItemTest() {

        //what you have
        Long testItemId = 9L;
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setUserName("Monika");


        //than what do you do with what you have
        rentalFacade.rentItem(testRentalEntity);

        //check result (is rented)
        assertTrue(rentalFacade.isItemRented(testItemId));
    }

    @Test
    void returnItemTest() {

        //what you have
        Long testItemId = 9L;
        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItemId(testItemId);
        testRentalEntity.setUserName("Monika");


        rentalFacade.rentItem(testRentalEntity);

        //when
        Boolean result = rentalFacade.returnItem(testItemId);

        //then
        assertTrue(result);
        assertFalse(rentalFacade.isItemRented(testItemId));
    }
}