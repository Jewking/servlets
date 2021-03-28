package com.anderson.servlets.commands;

import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditAgeCommand extends FrontCommand {

    @Override
    public void execute() throws ServletException, IOException {
        String strId = (String) request.getParameter("id");
        String strAge = (String) request.getParameter("age");

        Long id = Long.parseLong(strId);
        int age = Integer.parseInt(strAge);

        UserDAO.updateAge(id, age);

        UserModel user = UserDAO.select(id);
        request.setAttribute("user", user);
        request.setAttribute("error", null);
        forward("edit");
    }
}


