package com.monika.rentaladder.User.UserDTOs;

public class UserPoints {

    private String name;

    private Integer points;

    public UserPoints(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.points = userEntity.getPoints();
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

}
