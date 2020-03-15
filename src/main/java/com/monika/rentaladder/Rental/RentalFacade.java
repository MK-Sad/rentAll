package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;


public class RentalFacade {

    private RentalRepository rentalRepository;
    private ItemFacade itemFacade;

    public RentalFacade(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void rentItem(RentalEntity rental) {
        rentalRepository.save(rental);
    }

    public Boolean returnItem(ItemEntity item) {
        //String userName = userGetter.getSignedInUserNameOrAnonymous();
        RentalEntity rentalEntity = rentalRepository.findByItemAndReturnDate(item, null);
        if(rentalEntity==null){
            throw new IllegalArgumentException("You did not rent this item");
        }
        Clock clock = Clock.systemUTC();
        Instant returnDate = clock.instant();
        rentalEntity.setReturnDate(returnDate);
        if (rentalRepository.save(rentalEntity) != null) {
            return true;
        }
        return false;
        //int realDays = calculateRentalDays(itemEntity.getRentDate(), returnDate);
    }

    public Boolean isItemRented(ItemEntity item){
        RentalEntity rentalEntity = rentalRepository.findByItemAndReturnDate(item, null);
        if (rentalEntity==null){
            return false;
        }
        return true;
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
