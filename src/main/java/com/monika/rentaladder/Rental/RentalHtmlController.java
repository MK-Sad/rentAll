package com.monika.rentaladder.Rental;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class RentalHtmlController {

    private RentalFacade rentalFacade;

    public RentalHtmlController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @GetMapping("/confirmRentalMail/{rentalId}")
    public String confirmRental(@PathVariable Long rentalId) {
        rentalFacade.confirmRental(rentalId);
        return "confirmed";
    }

    @GetMapping("/denyRentalMail/{rentalId}")
    public String denyRental(@PathVariable Long rentalId) {
        rentalFacade.denyRental(rentalId);
        return "deny";
    }
}
