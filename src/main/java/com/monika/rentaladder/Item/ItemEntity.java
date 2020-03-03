package com.monika.rentaladder.Item;

import com.monika.rentaladder.Rental.RentalEntity;
import com.monika.rentaladder.User.UserEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ItemEntity {

    @Id
    private Long id;

    private String name;

    private ItemCategory category;

    @ManyToOne
    private UserEntity user;

    @OneToMany
    private List<RentalEntity> rentals;

    private String description;

    private Boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
