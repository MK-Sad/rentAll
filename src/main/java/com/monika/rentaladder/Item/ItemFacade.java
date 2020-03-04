package com.monika.rentaladder.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ItemFacade {

    @Autowired
    private ItemRepository itemRepository;

    public void addItem(ItemEntity item){
        itemRepository.save(item);

    }
    public ItemEntity getItemById(Long id){
        return itemRepository.findById(id);

    }
    public List<ItemEntity> getItemByCategory(ItemCategory category) {
        // todo dodac weryf isAval...
        return itemRepository.findByCategory(category);
    }

    public Page<ItemEntity> getAllItems(Pageable pageable) {
        // todo dodac weryf isAval...
        return itemRepository.findAll(pageable);
    }
}
