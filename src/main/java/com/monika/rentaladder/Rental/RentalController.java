package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    public void rentItem(RentalEntity rental) {
        return rentalFacade.rentItem(rental);
    }

    public void returnItem(ItemEntity item) {
        return rentalFacade.returnItem(item);
    }



}
