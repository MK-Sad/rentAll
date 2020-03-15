package com.monika.rentaladder.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserFacade userFacade;


    public UserController(UserFacade userFacade){
        this.userFacade = userFacade;
    }

    @GetMapping("/user/{name}")
    UserEntity findByName(@PathVariable String name){
        return userFacade.getUserByName(name);
    }


    @PostMapping("/user")
    public void addUser(@RequestBody UserEntity user) {
        userFacade.addUser(user);

    }
}
