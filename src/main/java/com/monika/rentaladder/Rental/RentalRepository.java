package com.monika.rentaladder.Rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.time.Instant;
import java.util.List;

interface RentalRepository extends Repository<RentalEntity, Long> {

    RentalEntity save(RentalEntity rentalEntity);

    RentalEntity findById(Long itemId);

    RentalEntity findByItemIdAndReturnDate(Long itemId, Instant returnDate);

    List<RentalEntity> findByUserNameAndReturnDate(String userName, Instant returnDate);

    List<RentalEntity> findByOwnerNameAndReturnDate(String ownerName, Instant returnDate);

    Page<RentalEntity> findAll(Pageable pageable);

}
