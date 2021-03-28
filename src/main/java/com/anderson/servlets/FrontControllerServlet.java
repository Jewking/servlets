package com.anderson.servlets;

import java.io.PrintWriter;

import com.anderson.servlets.commands.FrontCommand;
import com.anderson.servlets.commands.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String cmd = request.getParameter("command");

        try {
            writer.println("<h2>COMMAND: " + cmd + "</h2><br><p>response: " + response + "  </p>");
        } finally {
            writer.close();
        }
//        FrontCommand command = getCommand(request);
//        command.init(getServletContext(), request, response);
//        command.execute();
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FrontCommand command = getCommand(request);
//        command.init(getServletContext(), request, response);
//        command.executePost();
//    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format("com.anderson.servlets.commands.%sCommand", request.getParameter("command")));
            request.setAttribute("error", request.getParameter("command"));
            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}