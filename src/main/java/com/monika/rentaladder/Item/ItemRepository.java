package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ItemRepository extends Repository<ItemEntity, Long> {

    ItemEntity save(ItemEntity itemEntity);

    ItemEntity findById(Long id);

    List<ItemEntity> findByCategoryAndAvailable(ItemCategory itemCategory, boolean isAvailable);

    List<ItemEntity> findByUserName(String userName);

    Page<ItemEntity> findAll(Pageable pageable);

}
