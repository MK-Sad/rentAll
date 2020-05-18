package com.monika.rentaladder.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ItemController {

    private ItemFacade itemFacade;

    public ItemController(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @GetMapping("categories")
    List<ItemCategory> getCategories() {
        return itemFacade.getCategories();
    }

    @GetMapping("items")
    Page<ItemEntity> getAllItems(Pageable pageable) {
        return itemFacade.getAllItems(pageable);
    }

    @GetMapping("item/{id}")
    ItemEntity getItemById(@PathVariable Long id) {
        return itemFacade.getItemById(id);
    }

    @GetMapping("item/category/{category}")
    List<ItemEntity> getItemByCategory(@PathVariable ItemCategory category) {
        return itemFacade.getItemByCategoryAndAvailableAndNotRented(category);
    }

    @GetMapping("item/namePart/{namePart}")
    List<ItemEntity> getAllItemsByNamePart(@PathVariable String namePart) {
        return itemFacade.getAllItemsByNameContaining(namePart);
    }

    @GetMapping("item/owner/{owner}")
    List<ItemEntity> getItemByOwner(@PathVariable String owner) {
        return itemFacade.getItemByOwner(owner);
    }

    @PutMapping("/item")
    ItemEntity updateItem(@RequestBody ItemEntity itemEntity) {
        return itemFacade.updateItem(itemEntity);
    }

    @PostMapping("/item")
    ItemEntity addItem(@RequestBody ItemEntity itemEntity) {
        return itemFacade.addItem(itemEntity);
    }

}
