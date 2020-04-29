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

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("item/category/{category}")
    List<ItemEntity> getItemByCategory(@PathVariable ItemCategory category) {
        return itemFacade.getItemByCategoryAndAvailable(category);
    }

    @GetMapping("item/owner/{owner}")
    List<ItemEntity> getItemByOwner(@PathVariable String owner) {
        return itemFacade.getItemByOwner(owner);
    }

    @PostMapping("/item")
    ItemEntity addItem(@RequestBody ItemEntity itemEntity) {
        return itemFacade.addItem(itemEntity);
    }

}
