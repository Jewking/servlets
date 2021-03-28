package com.anderson.controllers;


import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("testapp/api")
class UsersRestController {
    @GetMapping("users")
    public List<UserModel> listUsers () {
        return UserDAO.selectAll();
    }

    @GetMapping("user/{id}")
    public UserModel getUser(@PathVariable Long id) {
        return UserDAO.select(id);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable Long id) {
        UserDAO.delete(id);
    }


//    @PostMapping("new/user")
//    public boolean add(@RequestBody String name, ) {
//        return users.add(new Users(name));
//    }

    /*@PutMapping("{id}")
    public Users update(@PathVariable int id, @RequestBody String name) {
        Users userFound = getUserID(id);
        userFound.setName(name);
        return userFound;
    }*/
}