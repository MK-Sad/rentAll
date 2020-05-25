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
        ItemEntity testItemEntity1 = new ItemEntity();
        testItemEntity1.setId(5L);
        testItemEntity1.setCategory(testCategory);
        testItemEntity1.setRented(false);
        testItemEntity1.setAvailable(true);

        ItemEntity testItemEntity2 = new ItemEntity();
        testItemEntity2.setId(5L);
        testItemEntity2.setCategory(testCategory);
        testItemEntity2.setRented(true);
        testItemEntity2.setAvailable(true);

        ItemEntity testItemEntity3 = new ItemEntity();
        testItemEntity3.setId(5L);
        testItemEntity3.setCategory(testCategory);
        testItemEntity3.setRented(false);
        testItemEntity3.setAvailable(true);

        //when
        itemFacade.addItem(testItemEntity1);
        itemFacade.addItem(testItemEntity2);
        itemFacade.addItem(testItemEntity3);
        List<ItemEntity> result = itemFacade.getItemByCategoryAndAvailableAndNotRented(categoryName);

        //then
        assertEquals(1, result.size());
        assertEquals(testItemEntity1.getId(), result.get(0).getId());
    }

}