package com.monika.rentaladder.User;

public class UserDTO {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO noPassword() {
        this.password = null;
        return this;
    }

}
