package com.anderson.servlets.commands;

import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class EditCommand extends FrontCommand {

    @Override
    public void execute() throws ServletException, IOException {
        String strId = (String) request.getParameter("id");
        Long id = Long.parseLong(strId);

        UserModel user = UserDAO.select(id);

        request.setAttribute("user", user);
        request.setAttribute("error", null);
        forward("edit");
    }
}

