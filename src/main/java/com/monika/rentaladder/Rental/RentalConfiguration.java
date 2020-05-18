package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemRepository;
import com.monika.rentaladder.User.MailSender.MailService;
import com.monika.rentaladder.User.UserFacade;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalConfiguration {

    RentalFacade rentalFacade(ItemRepository itemRepository, ApplicationEventPublisher publisher) {
        return new RentalFacade(new InMemoryRentalRepository(), itemRepository,  publisher);
        }

    @Bean
    RentalFacade rentalFacade(RentalRepository rentalRepository, ItemRepository itemRepository, ApplicationEventPublisher publisher){
        return new RentalFacade(rentalRepository, itemRepository, publisher);
    }
}
