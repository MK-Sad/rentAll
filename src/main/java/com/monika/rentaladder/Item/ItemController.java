package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private ItemFacade itemFacade;

    public ItemController(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @GetMapping("items")
    Page<ItemEntity> getAllItems(Pageable pageable) {
        return itemFacade.getAllItems(pageable);
    }

    @GetMapping("item/{id}")
    ItemEntity getItemById(@PathVariable Long id) {
        return itemFacade.getItemById(id);
    }

    @GetMapping("item/{category}")
    List<ItemEntity> getItemByCategory(@PathVariable ItemCategory category) {
        return itemFacade.getItemByCategory(category);
    }

    @PostMapping("/item")
    void addItem(@RequestBody ItemEntity itemEntity) {
        itemFacade.addItem(itemEntity);
    }

}
