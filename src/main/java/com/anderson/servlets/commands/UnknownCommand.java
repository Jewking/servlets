package com.anderson.servlets.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    @Override
    public void execute() throws ServletException, IOException {
        forward("unknown");
    }
}