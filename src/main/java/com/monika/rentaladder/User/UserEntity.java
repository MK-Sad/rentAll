package com.monika.rentaladder.User;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.Rental.RentalEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    private String name;

    @OneToMany
    private List<ItemEntity> items;

    @OneToMany
    private List<RentalEntity> rentals;

    public UserEntity() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public List<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalEntity> rentals) {
        this.rentals = rentals;
    }
}
