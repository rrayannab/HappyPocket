package com.happyPockets.service;

import com.happyPockets.api.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> userList;

    public UserService(List<User> userList) {
        userList = new ArrayList<>();
        User user1 = new User(1, "test", "test", "test@gmail.com", "nameTest", "surname1Test", "surname2Test", 651274927);
        userList.add(user1);

    }

    public Optional<User> getUserID(Integer id)
    {
        Optional<User> optional = Optional.empty();

        for (User user : userList)
        {
            if (user.getId() == id)
                optional = Optional.of(user);
        }
        return optional;
    }

    public List<User> getUserList()
    {
        return userList;
    }


}
