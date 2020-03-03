package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class RentalController {

    private RentalFacade rentalFacade;

    public RentalController(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @PostMapping("/rentFilm")
    PriceDto rentFilm(@RequestBody RentRequest rentRequest) {
        return rentFacade.rentFilm(rentRequest.getFilmTitle(),rentRequest.getDays());}

}
