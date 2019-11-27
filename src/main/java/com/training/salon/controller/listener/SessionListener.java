package com.training.salon.controller.listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    private static final Logger log = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String email = (String) httpSessionEvent.getSession().getServletContext()
                .getAttribute("email");
        log.info("User log out: " + email);
        loggedUsers.remove(email);
        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
