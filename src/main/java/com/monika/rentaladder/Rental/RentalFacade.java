package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class RentalFacade {

    private RentalRepository rentalRepository;

    private ItemRepository itemRepository;

    public RentalFacade(RentalRepository rentalRepository, ItemRepository itemRepository) {
        this.rentalRepository = rentalRepository;
        this.itemRepository = itemRepository;
    }

    public RentalEntity rentItem(RentalEntity rental) {
        Clock clock = Clock.systemUTC();
        rental.setRentalDate(clock.instant());
        ItemEntity item = itemRepository.findById(rental.getItemId());
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(rental.getItemId(), null);
        if((rentalEntity!=null)||(item.isRented())){
            throw new IllegalArgumentException("Item is already rented");
        }
        item.setRented(true);
        itemRepository.save(item);
        return rentalRepository.save(rental);
    }

    public RentalEntity returnItem(Long itemId) {
        //String userName = userGetter.getSignedInUserNameOrAnonymous();
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(itemId, null);
        if(rentalEntity==null){
            throw new IllegalArgumentException("You did not rent this item");
        }
        Clock clock = Clock.systemUTC();
        Instant returnDate = clock.instant();
        rentalEntity.setReturnDate(returnDate);
        if (rentalRepository.save(rentalEntity) != null) {
            ItemEntity item = itemRepository.findById(itemId);
            item.setRented(false);
            itemRepository.save(item);
        }
        return rentalEntity;
        //int realDays = calculateRentalDays(itemEntity.getRentDate(), returnDate);
    }

    //Not used
    public Boolean isItemRented(Long itemId){
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(itemId, null);
        if (rentalEntity==null){
            return false;
        }
        return true;
    }

    public List<RentalEntity> getCurrentRentalsByUser(String userName){
        List<RentalEntity> rentalsList = rentalRepository.findByUserNameAndReturnDate(userName, null);
        if (rentalsList == null){
            return Collections.emptyList();
        }
        return rentalsList;
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
