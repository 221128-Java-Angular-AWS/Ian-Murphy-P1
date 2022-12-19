package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getInputStream(), User.class);
        try {
            User authenticatedUser = service.authenticateUser(user);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));
            Cookie authCookie = new Cookie("userId", user.getUserId().toString());

        } catch(UserNotFoundException e) {
            resp.getWriter().print("Username not recognized");
            resp.setStatus(401);
        } catch(PasswordIncorrectException e) {
            resp.getWriter().print("Wrong password");
            resp.setStatus(401);
        }

    }

}
