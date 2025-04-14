package com.kpit.vehicleavailability.controller;

import com.kpit.vehicleavailability.model.User;
import com.kpit.vehicleavailability.service.UserService;

// import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.loginUser(username, password);

        response.setContentType("text/plain");
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.getWriter().write("Login successful! Welcome " + user.getFirstName());
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid username or password.");
        }
    }
}
