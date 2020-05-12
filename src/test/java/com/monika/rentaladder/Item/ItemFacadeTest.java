package com.monika.rentaladder.Item;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFacadeTest {

    private ItemFacade itemFacade = new ItemConfiguration().itemFacade();

    @Test
    void testAddItemAndGetItemByCategoryAndAvailableAndNotRented() {

        //given
        ItemCategory testCategory = ItemCategory.TOYS;
        ItemEntity testItemEntity = new ItemEntity();
        testItemEntity.setId(5L);
        testItemEntity.setCategory(testCategory);

        //when
        itemFacade.addItem(testItemEntity);
        List<ItemEntity> result = itemFacade.getItemByCategoryAndAvailableAndNotRented(testCategory);

        //then
        assertEquals(1, result.size());
        assertEquals(testItemEntity, result.get(0));
    }

}