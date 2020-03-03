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
    Page<ItemEntity> getItems(Pageable pageable) {
        return itemFacade.findAll(pageable);
    }

    @GetMapping("item/{id}")
    ItemEntity getItem(@PathVariable Long id) {
        return itemFacade.show(id);
    }

    @GetMapping("item/{category}")
    List<ItemEntity> searchByCategory(@PathVariable ItemCategory category) {
        return itemFacade.searchByCategory(category);
    }

    @PostMapping("/item")
    void addItem(@RequestBody ItemEntity itemEntity) {
        itemFacade.add(itemEntity);
    }

}
