package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public ItemEntity updateItem(ItemEntity item){
        return itemRepository.save(item);
    }

    public List<ItemEntity> getItemByCategoryAndAvailableAndNotRented(String categoryName) {
        ItemCategory category = ItemCategory.getItemCategoryByName(categoryName);
        return itemRepository.findByCategoryAndAvailableAndRented(category, true, false);
    }

    public List<ItemEntity> getItemByOwner(String owner) {
        return itemRepository.findByOwner(owner);
    }

    public Page<ItemEntity> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public List<ItemEntity> getAllItemsByNameContaining(String namePart) {
        return itemRepository.findByNameContainingIgnoreCaseAndAvailableAndRented(namePart, true, false);
    }

    public List<String> getCategories(String languageCode) {
        switch (languageCode) {
            case "pl":
                return ItemCategory.getAllPolishCategories();
            default:
                return ItemCategory.getAllEnglishCategories();
        }
    }
}
