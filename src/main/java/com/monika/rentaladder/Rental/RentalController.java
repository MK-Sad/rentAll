package com.monika.rentaladder.Rental;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping("/rentItem")
    public RentalEntity rentItem(@RequestBody RentalEntity rental) {
        return rentalFacade.rentItem(rental);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping("/returnItem/{iitemId}")
    public Boolean returnItem(@PathVariable Long itemId) {
        return rentalFacade.returnItem(itemId);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("/rentalsByUser/{userName}")
    public List<RentalEntity> getRentalsByUser(String userName) {
        return rentalFacade.getCurrentRentalsByUser(userName);
    }

}
