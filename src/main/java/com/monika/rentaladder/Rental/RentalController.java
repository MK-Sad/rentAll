package com.monika.rentaladder.Rental;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @PostMapping("/rentItem")
    public void rentItem(RentalEntity rental) {
        rentalFacade.rentItem(rental);
    }

    @PostMapping("/returnItem")
    public Boolean returnItem(Long itemId) {
        return rentalFacade.returnItem(itemId);
    }

    @GetMapping("/rentalsByUser")
    public List<RentalEntity> getRentalsByUser(String userName) {
        return rentalFacade.getCurrentRentalsByUser(userName);
    }

}
