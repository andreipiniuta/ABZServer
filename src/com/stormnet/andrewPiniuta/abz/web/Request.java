package com.stormnet.andrewPiniuta.abz.web;

public interface Request {
    String getCommandName();

    Object getParameter(String paramName);
}
