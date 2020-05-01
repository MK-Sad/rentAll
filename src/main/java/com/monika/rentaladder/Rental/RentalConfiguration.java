package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalConfiguration {

    RentalFacade rentalFacade(ItemRepository itemRepository) {
        return new RentalFacade(new InMemoryRentalRepository(), itemRepository);
        }

    @Bean
    RentalFacade rentalFacade(RentalRepository rentalRepository, ItemRepository itemRepository){
        return new RentalFacade(rentalRepository, itemRepository);
    }
}
