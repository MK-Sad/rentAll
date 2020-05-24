package com.monika.rentaladder.Rental;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://rentall-hosting.firebaseapp.com"}, maxAge = 3600)
@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @PostMapping("/rentItem")
    public RentalEntity rentItem(@RequestBody RentalEntity rental) {
        try {
            return rentalFacade.rentItem(rental);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Nie odnaleziono zasobu", e);
        }
    }

    @PutMapping("/returnItem/{itemId}")
    public RentalEntity returnItem(@PathVariable Long itemId) {
        return rentalFacade.returnItem(itemId);
    }

    @PutMapping("/confirmRental/{rentalId}")
    public RentalEntity confirmRental(@PathVariable Long rentalId) {
        return rentalFacade.confirmRental(rentalId);
    }

    @PutMapping("/denyRental/{rentalId}")
    public RentalEntity denyRental(@PathVariable Long rentalId) {
        return rentalFacade.denyRental(rentalId);
    }

    @GetMapping("/rentalsByUser/{userName}")
    public List<RentalEntity> getRentalsByUser(@PathVariable String userName) {
        return rentalFacade.getCurrentRentalsByUser(userName);
    }

    @GetMapping("/rentalsByOwner/{ownerName}")
    public List<RentalEntity> getRentalsByOwner(@PathVariable String ownerName) {
        return rentalFacade.getCurrentRentalsByOwner(ownerName);
    }
}
