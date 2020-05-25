package com.monika.rentaladder.Item;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFacadeTest {

    private ItemFacade itemFacade = new ItemConfiguration().itemFacade();

    @Test
    void testGetCategories() {
        List<String> result = ItemCategory.getAllPolishCategories();
        String resultToString = String.join(",", result);
        assertEquals("NARZĘDZIA,DOM,ELEKTRONIKA,ZDROWIE I URODA,SPORT,ZABAWKI",
                resultToString);
    }

    @Test
    void testGetCategoryByName() {
        ItemCategory result = ItemCategory.getItemCategoryByName("NARZĘDZIA");
        assertEquals(ItemCategory.TOOLS, result);
        result = ItemCategory.getItemCategoryByName("TOYS");
        assertEquals(ItemCategory.TOYS, result);
    }

    @Test
    void testAddItemAndGetItemByCategoryAndAvailableAndNotRented() {

        //given
        String categoryName = "TOYS";
        ItemCategory testCategory = ItemCategory.getItemCategoryByName(categoryName);
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(5L);
        testItemEntity.setCategory(testCategory);

        //when
        itemFacade.addItem(testItemEntity);
        List<ItemEntity> result = itemFacade.getItemByCategoryAndAvailableAndNotRented(categoryName);

        //then
        assertEquals(1, result.size());
        assertEquals(testItemEntity, result.get(0));
    }

}