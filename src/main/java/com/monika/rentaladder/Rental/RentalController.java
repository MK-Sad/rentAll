package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    public rentItem(RentalEntity rental) {
        return rentalFacade.rentItem(rental);
    }

    public returnItem(ItemEntity item) {
        return rentalFacade.returnItem(item);
    }



}
