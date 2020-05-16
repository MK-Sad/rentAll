package com.monika.rentaladder.User;

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

    @PostMapping("/user")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userFacade.addUser(user);
    }

    @PostMapping("/authenticate")
    public UserDTO authenticateUser (@RequestBody UserDTO user) {
        try {
            return userFacade.authenticateUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Jupi", e);
        }
    }

}
