package com.happyPockets.api.controller;

import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUserID(id);
        if (user.isPresent())
            return user.get();
        return null;
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUserList();
    }

    /**
     *
     * @param username
     * @param password
     * @param email
     * @param name
     * @param surname1
     * @param surname2
     * @param phone
     * @return true si se ha podido añadir corectamente, false en cualquier otro caso
     */
    @GetMapping("/addUser")
    public boolean addUser(@RequestParam String username, String password, String email, String name, String surname1, String surname2, int phone){
        return userService.addUser(username, password, email, name, surname1, surname2, phone);
    }
}
