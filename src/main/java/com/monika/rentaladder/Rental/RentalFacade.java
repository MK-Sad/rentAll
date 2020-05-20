package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Item.ItemRepository;
import com.monika.rentaladder.User.MailSender.RentalEvent;
import org.springframework.context.ApplicationEventPublisher;
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

    private ApplicationEventPublisher publisher;

    public RentalFacade(RentalRepository rentalRepository, ItemRepository itemRepository, ApplicationEventPublisher publisher) {
        this.rentalRepository = rentalRepository;
        this.itemRepository = itemRepository;
        this.publisher = publisher;
    }

    public RentalEntity rentItem(RentalEntity rental) {
        Clock clock = Clock.systemUTC();
        rental.setRentalDate(clock.instant());
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(rental.getItemId(), null);
        if(rentalEntity!=null){
            throw new IllegalArgumentException("Item is already rented");
        }
        ItemEntity item = itemRepository.findById(rental.getItemId());
        item.setRented(true);
        itemRepository.save(item);
        RentalEntity newRental = rentalRepository.save(rental);
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.REQUEST, newRental.getUserName(),
                newRental.getOwnerName(), newRental.getItemName(), newRental.getId()));
        return newRental;
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
        RentalEntity result = rentalRepository.save(rentalEntity);
        if (result != null) {
            ItemEntity item = itemRepository.findById(itemId);
            item.setRented(false);
            itemRepository.save(item);
        }
        int realDays = calculateRentalDays(result.getConfirmedDate(), returnDate);
        if (realDays <= rentalEntity.getRentalPeriod()) {
            publisher.publishEvent(new RentalEvent(RentalEvent.Type.RETURN_IN_TIME, result.getUserName(),
                    result.getOwnerName(), result.getItemName(),  result.getId()));
        } else {
            publisher.publishEvent(new RentalEvent(RentalEvent.Type.RETURN_DELAYED, result.getUserName(),
                    result.getOwnerName(), result.getItemName(),  result.getId()));
        }
        return rentalEntity;
    }

    public RentalEntity confirmRental(Long rentalId) {
        RentalEntity rental = rentalRepository.findById(rentalId);
        if (rental.getConfirmedDate() != null) {
            return rental;
        }
        Clock clock = Clock.systemUTC();
        Instant confirmedDate = clock.instant();
        rental.setConfirmedDate(confirmedDate);
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.CONTACT, rental.getUserName(),
                rental.getOwnerName(), rental.getItemName(), rental.getId()));
        return rentalRepository.save(rental);
    }

    public RentalEntity denyRental(Long rentalId) {
        RentalEntity rental = rentalRepository.findById(rentalId);
        if (rental.getReturnDate() != null) {
            return rental;
        }
        Clock clock = Clock.systemUTC();
        Instant returnDate = clock.instant();
        rental.setReturnDate(returnDate);
        RentalEntity resultRental = rentalRepository.save(rental);
        if (resultRental != null) {
            ItemEntity item = itemRepository.findById(rental.getItemId());
            item.setRented(false);
            itemRepository.save(item);
        }
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.SORRY, resultRental.getUserName(),
                resultRental.getOwnerName(), resultRental.getItemName(), resultRental.getId()));
        return rental;
    }

    public List<RentalEntity> getCurrentRentalsByUser(String userName){
        List<RentalEntity> rentalsList = rentalRepository.findByUserNameAndReturnDate(userName, null);
        if (rentalsList == null){
            return Collections.emptyList();
        }
        return rentalsList;
    }

    public List<RentalEntity> getCurrentRentalsByOwner(String ownerName){
        List<RentalEntity> rentalsList = rentalRepository.findByOwnerNameAndReturnDate(ownerName, null);
        if (rentalsList == null){
            return Collections.emptyList();
        }
        return rentalsList;
    }

    //Not used
    public Boolean isItemRented(Long itemId){
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(itemId, null);
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
