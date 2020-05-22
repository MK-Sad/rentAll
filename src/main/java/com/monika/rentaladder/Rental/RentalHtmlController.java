package com.monika.rentaladder.Rental;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class RentalHtmlController {

    private RentalFacade rentalFacade;

    public RentalHtmlController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @RequestMapping("/confirmRentalMail/{rentalId}")
    public String confirmRental(@PathVariable Long rentalId) {
        return rentalFacade.confirmRental(rentalId) != null ? "confirmed" : "alreadyClicked";
    }

    @RequestMapping("/denyRentalMail/{rentalId}")
    public String denyRental(@PathVariable Long rentalId) {
        return rentalFacade.denyRental(rentalId) != null ? "deny" : "alreadyClicked";
    }

    @RequestMapping("/")
    public String start() {
        return "index";
    }

}
