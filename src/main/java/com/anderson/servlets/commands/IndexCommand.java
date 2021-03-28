package com.anderson.servlets.commands;

import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class IndexCommand extends FrontCommand {

    @Override
    public void execute() throws ServletException, IOException {
        List<UserModel> users = UserDAO.selectAll();
        request.setAttribute("users", users);
        request.setAttribute("error", null);
        forward("index");
    }

//    @Override
//    public void executePost() throws ServletException, IOException {
//        String name = (String) request.getParameter("name");
//        String strAge = (String) request.getParameter("age");
//        int age = Integer.parseInt(strAge);
//
//        UserDAO.insert(new UserModel(name, age, true));
//        List<UserModel> users = UserDAO.selectAll();
//
//        request.setAttribute("error", null);
//        request.setAttribute("users", users);
//        forward("index");
//    }
}
