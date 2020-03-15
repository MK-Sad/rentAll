package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    public void rentItem(RentalEntity rental) {
        rentalFacade.rentItem(rental);
    }

    @PostMapping("/returnItem")
    public Boolean returnItem(ItemEntity item) {
        return rentalFacade.returnItem(item);
    }



}
