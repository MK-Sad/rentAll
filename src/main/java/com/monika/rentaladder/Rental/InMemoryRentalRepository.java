package com.monika.rentaladder.Rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRentalRepository implements RentalRepository {

    private Map<Long,RentalEntity> map = new HashMap();

    @Override
    public RentalEntity save(RentalEntity rentalEntity) {
        map.put(rentalEntity.getId(), rentalEntity);
        return rentalEntity;
    }

    @Override
    public RentalEntity findByItemIdAndReturnDate(Long itemId, Instant returnDate){
        return map.entrySet().stream()
                .map(map -> map.getValue())
                .filter(v -> v.getItemId().equals(itemId))
                .filter( (returnDate != null) ? (v -> returnDate.equals(v.getReturnDate())) : (v -> v.getReturnDate() == null) )
                .findAny()
                .orElse(null);
    }

    @Override
    public Page<RentalEntity> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public List<RentalEntity> findByUserNameAndReturnDate(String userName, Instant returnDate) {
        return map.entrySet().stream()
                .map(map -> map.getValue())
                .filter(v -> v.getUserName().equals(userName))
                .filter( (returnDate != null) ? (v -> returnDate.equals(v.getReturnDate())) : (v -> v.getReturnDate() == null) )
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalEntity> findByOwnerNameAndReturnDate(String ownerName, Instant returnDate) {
        return map.entrySet().stream()
                .map(map -> map.getValue())
                .filter(v -> v.getUserName().equals(ownerName))
                .filter( (returnDate != null) ? (v -> returnDate.equals(v.getReturnDate())) : (v -> v.getReturnDate() == null) )
                .collect(Collectors.toList());
    }

    @Override
    public RentalEntity findById(Long itemId) {
        return map.get(itemId);
    }

}
