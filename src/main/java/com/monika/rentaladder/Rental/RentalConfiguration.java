package com.monika.rentaladder.Rental;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalConfiguration {

    RentalFacade rentalFacade() {
        return new RentalFacade(new InMemoryRentalRepository());
        }

    RentalFacade rentalFacade(RentalRepository rentalRepository){
        return new RentalFacade(rentalRepository){
        };

    }
}
