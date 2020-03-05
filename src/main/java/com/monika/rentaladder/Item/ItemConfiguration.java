package com.monika.rentaladder.Item;

import org.springframework.context.annotation.Bean;

public class ItemConfiguration {

    ItemFacade itemFacade() {
        return new ItemFacade(new InMemoryItemRepository());
    }

    @Bean
    ItemFacade itemFacade(ItemRepository itemRepository) {
        return new ItemFacade(itemRepository);
    }
}
