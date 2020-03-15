package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.time.Instant;

interface RentalRepository extends Repository<RentalRepository, Long> {

    RentalEntity save(RentalEntity rentalEntity);

    RentalEntity findByItemAndReturnDate(ItemEntity itemEntity, Instant returnDate);

    Page<RentalEntity> findAll(Pageable pageable);

}
