package com.anderson.servlets.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    @Override
    public void executeGet() throws ServletException, IOException {
        forward("unknown");
    }

    @Override
    public void executePost() throws ServletException, IOException {
        forward("unknown");
    }
}