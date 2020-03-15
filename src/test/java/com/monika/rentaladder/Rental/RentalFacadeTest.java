package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.User.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class RentalFacadeTest {

    RentalFacade rentalFacade = new RentalConfiguration().rentalFacade();

    @Test
    void rentItemTest() {

        //what you have
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setName("Monika");

        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(9L);

        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItem(testItemEntity);
        testRentalEntity.setUser(testUserEntity);


        //than what do you do with what you have
        rentalFacade.rentItem(testRentalEntity);

        //check result (is rented)
        assertTrue(rentalFacade.isItemRented(testItemEntity));
    }

    @Test
    void returnItemTest() {

        //what you have
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setName("Monika");

        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(9L);

        RentalEntity testRentalEntity = new RentalEntity();
        testRentalEntity.setId(2L);
        testRentalEntity.setItem(testItemEntity);
        testRentalEntity.setUser(testUserEntity);


        rentalFacade.rentItem(testRentalEntity);

        //when
        Boolean result = rentalFacade.returnItem(testItemEntity);

        //then
        assertTrue(result);
        assertFalse(rentalFacade.isItemRented(testItemEntity));
    }
}