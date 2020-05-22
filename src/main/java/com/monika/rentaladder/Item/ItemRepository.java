package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ItemRepository extends Repository<ItemEntity, Long> {

    ItemEntity save(ItemEntity itemEntity);

    ItemEntity findById(Long id);

    List<ItemEntity> findByCategoryAndAvailableAndRented(ItemCategory itemCategory, boolean available, boolean rented);

    List<ItemEntity> findByOwner(String owner);

    List<ItemEntity> findByNameContainingIgnoreCaseAndAvailableAndRented(String name, boolean available, boolean rented);

    Page<ItemEntity> findAll(Pageable pageable);

}
