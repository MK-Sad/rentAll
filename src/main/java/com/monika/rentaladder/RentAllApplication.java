package com.monika.rentaladder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages= {"com.monika.rentaladder.Item","com.monika.rentaladder.Rental","com.monika.rentaladder.User"})
@EnableJpaRepositories(basePackages= {"com.monika.rentaladder.Item","com.monika.rentaladder.Rental","com.monika.rentaladder.User"})
@ComponentScan(basePackages= {"com.monika.rentaladder.Item","com.monika.rentaladder.Rental","com.monika.rentaladder.User"})
public class RentAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentAllApplication.class, args);
	}

}
