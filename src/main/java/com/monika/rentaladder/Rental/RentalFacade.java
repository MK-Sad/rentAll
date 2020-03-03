package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemFacade;
import com.monika.rentaladder.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class RentalFacade {

    @Autowired
    private RentalRepository rentalRepository;
    private ItemFacade itemFacade;


    public void rent(RentalEntity rental) {
        rentalRepository.save(rental);
    }

    public void returnItem(ItemEntity item) {

        //String userName = userGetter.getSignedInUserNameOrAnonymous();
        RentalEntity rentalEntity = rentalRepository.findByItemAndReturnDate(item, null);
        if(rentalEntity==null){
            throw new IllegalArgumentException("You did not rent this item");
        }
        Clock clock = Clock.systemUTC();
        Instant returnDate = clock.instant();
        rentalEntity.setReturnDate(returnDate);
        rentalRepository.save(rentalEntity);
        //int realDays = calculateRentalDays(itemEntity.getRentDate(), returnDate);
    }

    private int calculateRentalDays(Instant rentalDate, Instant returnDate)
    {
        final Duration timeDiff = Duration.between(rentalDate, returnDate);
        return Math.toIntExact(timeDiff.toDays());
    }
}
