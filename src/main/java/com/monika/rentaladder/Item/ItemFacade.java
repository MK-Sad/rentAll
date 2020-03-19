package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ItemFacade {

    private ItemRepository itemRepository;

    public ItemFacade(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

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
