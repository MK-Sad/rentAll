package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.*;

public class InMemoryRentalRepository implements RentalRepository{

    private Map<Long,RentalEntity> map = new HashMap();

    @Override
    public RentalEntity save(RentalEntity rentalEntity) {
        map.put(rentalEntity.getId(), rentalEntity);
        return rentalEntity;
    }

    @Override
    public RentalEntity findByItemAndReturnDate(ItemEntity itemEntity, Instant returnDate){
        return map.entrySet().stream()
                .map(map -> map.getValue())
                .filter(v -> ((v.getItem().equals(itemEntity)) && (v.getReturnDate().equals(returnDate))))
                .findAny().get();
    }


    public Page<RentalEntity> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
}
