package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemRepository implements ItemRepository {

    private Map<Long,ItemEntity> map = new HashMap();

    @Override
    public ItemEntity save(ItemEntity itemEntity) {
        map.put(itemEntity.getId(), itemEntity);
        return itemEntity;
    }

    @Override
    public ItemEntity findById(Long id) {
        return map.get(id);
    }

    @Override
    public List<ItemEntity> findByCategoryAndAvailableAndRented(ItemCategory itemCategory, boolean available, boolean rented) {
        List<ItemEntity> result = new ArrayList<>();
        map.forEach((k,v) -> {
            if (v.getCategory().equals(itemCategory)) {
                result.add(v);
            }
        });
        return result;
    }

    @Override
    public List<ItemEntity> findByOwner(String owner) {
        List<ItemEntity> result = new ArrayList<>();
        map.forEach((k,v) -> {
            if (v.getOwner().equals(owner)) {
                result.add(v);
            }
        });
        return result;
    }

    @Override
    public Page<ItemEntity> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public List<ItemEntity> findAllByNameContaining(String name) {
        List<ItemEntity> result = new ArrayList<>();
        map.forEach((k,v) -> {
            if (v.getOwner().equals(name)) {
                result.add(v);
            }
        });
        return result;
    }

}
