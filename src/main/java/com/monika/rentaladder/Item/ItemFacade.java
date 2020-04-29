package com.monika.rentaladder.Item;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.Arrays;
import java.util.List;

public class ItemFacade {

    private ItemRepository itemRepository;

    public ItemFacade(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemEntity addItem(ItemEntity item){
        return itemRepository.save(item);
    }

    public ItemEntity getItemById(Long id){
        return itemRepository.findById(id);

    }

    public List<ItemEntity> getItemByCategoryAndAvailable(ItemCategory category) {
        return itemRepository.findByCategoryAndAvailable(category, true);
    }

    public List<ItemEntity> getItemByOwner(String owner) {
        return itemRepository.findByOwner(owner);
    }

    public Page<ItemEntity> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public List<ItemCategory> getCategories(){
        return Arrays.asList(ItemCategory.values());
    }

    @EventListener
    @Async("rentalTaskExecutor")
    public void filmsWereRented(Long id) {
    }
}
