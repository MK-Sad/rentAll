package com.monika.rentaladder.Rental;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @PostMapping("/rentItem")
    public RentalEntity rentItem(@RequestBody RentalEntity rental) {
        return rentalFacade.rentItem(rental);
    }

    @PutMapping("/returnItem/{itemId}")
    public RentalEntity returnItem(@PathVariable Long itemId) {
        return rentalFacade.returnItem(itemId);
    }

    @GetMapping("/rentalsByUser/{userName}")
    public List<RentalEntity> getRentalsByUser(@PathVariable String userName) {
        return rentalFacade.getCurrentRentalsByUser(userName);
    }

}
