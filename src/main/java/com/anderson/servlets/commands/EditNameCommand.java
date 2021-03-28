package com.anderson.servlets.commands;

import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditNameCommand extends FrontCommand {

    @Override
    public void execute() throws ServletException, IOException {
        String strId = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");

        Long id = Long.parseLong(strId);
        UserDAO.updateName(id, name);

        UserModel user = UserDAO.select(id);
        request.setAttribute("user", user);
        request.setAttribute("error", null);
        forward("edit");
    }
}
