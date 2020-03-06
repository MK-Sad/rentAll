package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class RentalFacade {

    private RentalRepository rentalRepository;
    private ItemFacade itemFacade;

    public RentalFacade(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void rentItem(RentalEntity rental) {
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

    public Page<RentalEntity> getAllRentals(Pageable pageable) {
        return rentalRepository.findAll(pageable);
    }
}
