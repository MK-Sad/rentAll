package com.monika.rentaladder.Rental;

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
    public Boolean returnItem(Long itemId) {
        return rentalFacade.returnItem(itemId);
    }



}
