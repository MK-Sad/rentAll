package com.monika.rentaladder.Rental;

import com.monika.rentaladder.Item.ItemEntity;
import com.monika.rentaladder.User.UserEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;

@Entity
public class RentalEntity {

    @Id
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ItemEntity item;

    private Instant rentalDate;

    private Instant returnDate;

    private Integer rentalPeriod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Instant getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Instant rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }




}
