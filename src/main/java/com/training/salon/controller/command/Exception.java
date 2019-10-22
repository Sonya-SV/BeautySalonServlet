package com.training.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Exception implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
