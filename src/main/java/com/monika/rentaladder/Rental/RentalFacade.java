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
        ItemEntity item = itemRepository.findById(rental.getItemId());
        RentalEntity rentalEntity = rentalRepository.findByItemIdAndReturnDate(rental.getItemId(), null);
        if((rentalEntity!=null)||(item.isRented())){
            throw new IllegalArgumentException("Item is already rented");
        }
        item.setRented(true);
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.REQUEST, item.getOwner(), item.getName(), rental.getUserName(), rental.getId()));
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
        ItemEntity item = itemRepository.findById(itemId);
        RentalEntity result = rentalRepository.save(rentalEntity);
        if (result != null) {
            item.setRented(false);
            itemRepository.save(item);
        }
        int realDays = calculateRentalDays(result.getConfirmedDate(), returnDate);
        if (realDays <= rentalEntity.getRentalPeriod()) {
            publisher.publishEvent(new RentalEvent(RentalEvent.Type.RETURN_IN_TIME, result.getOwnerName(),
                    item.getName(), result.getUserName(), result.getId()));
        } else {
            publisher.publishEvent(new RentalEvent(RentalEvent.Type.RETURN_DELAYED, result.getOwnerName(),
                    item.getName(), result.getUserName(), result.getId()));
        }
        return rentalEntity;
    }

    public RentalEntity confirmRental(Long rentalId) {
        RentalEntity rental = rentalRepository.findById(rentalId);
        Clock clock = Clock.systemUTC();
        Instant confirmedDate = clock.instant();
        rental.setConfirmedDate(confirmedDate);
        ItemEntity item = itemRepository.findById(rental.getItemId());
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.CONTACT, rental.getOwnerName(), item.getName(), rental.getUserName(), rental.getId()));
        return rentalRepository.save(rental);
    }

    public RentalEntity denyRental(Long rentalId) {
        RentalEntity rental = rentalRepository.findById(rentalId);
        Clock clock = Clock.systemUTC();
        Instant returnDate = clock.instant();
        rental.setReturnDate(returnDate);
        ItemEntity item = itemRepository.findById(rental.getItemId());
        RentalEntity resultRental = rentalRepository.save(rental);
        if (resultRental != null) {
            item.setRented(false);
            itemRepository.save(item);
        }
        publisher.publishEvent(new RentalEvent(RentalEvent.Type.SORRY, rental.getOwnerName(), item.getName(), rental.getUserName(), rental.getId()));
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
