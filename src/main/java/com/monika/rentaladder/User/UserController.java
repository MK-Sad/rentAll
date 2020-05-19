package com.monika.rentaladder.User;

import com.monika.rentaladder.User.UserDTOs.UserCredentials;
import com.monika.rentaladder.User.UserDTOs.UserEntity;
import com.monika.rentaladder.User.UserDTOs.UserPoints;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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

    @GetMapping("/points/{name}")
    UserPoints getPointsByNamee(@PathVariable String name){
        return userFacade.getPointsByName(name);
    }

    @PostMapping("/user")
    public UserEntity addUser(@RequestBody UserEntity user) {
        try {
            return userFacade.addUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Name already exist", e);
        }
    }

    @PutMapping("/user")
    UserEntity updateUser(@RequestBody UserEntity userEntity) {
        return userFacade.updateUser(userEntity);
    }

    @PostMapping("/authenticate")
    public UserPoints authenticateUser (@RequestBody UserCredentials user) {
        try {
            return userFacade.authenticateUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Jupi", e);
        }
    }

}
