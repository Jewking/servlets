package com.anderson.controllers;

import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("testapp/api")
class UsersRestController {
    @GetMapping("user/list")
    public List<UserModel> listUsers () {
        return UserDAO.selectAll();
    }

    @GetMapping("user/show/{id}")
    public UserModel getUser(@PathVariable Long id) {
        return UserDAO.select(id);
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        UserDAO.delete(id);
    }

    @GetMapping("user/new/{name}/{age}")
    public void add(@PathVariable String name, @PathVariable int age) {
        UserDAO.insert(new UserModel(name, age, true));
    }

}