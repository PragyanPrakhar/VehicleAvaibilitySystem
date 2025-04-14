// package com.kpit.vehicleavailability.controller;

// import com.kpit.vehicleavailability.model.User;
// import com.kpit.vehicleavailability.service.UserService;

// // import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.*;
// import java.io.IOException;

// @WebServlet("/signup")
// public class SignupController extends HttpServlet {

//     private UserService userService;

//     @Override
//     public void init() {
//         userService = new UserService();
//     }

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         String firstName = request.getParameter("firstName");
//         String lastName = request.getParameter("lastName");
//         String email = request.getParameter("email");
//         String username = request.getParameter("username");
//         String password = request.getParameter("password");
//         boolean isDealer = Boolean.parseBoolean(request.getParameter("isDealer"));

//         User user = new User(firstName, lastName, email, username, password, isDealer);
//         userService.registerUser(user);

//         response.setContentType("text/plain");
//         response.getWriter().write("User registered successfully!");
//     }
// }

package com.kpit.vehicleavailability.controller;

import com.kpit.vehicleavailability.model.User;
import com.kpit.vehicleavailability.service.UserService;

// import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signup")
public class SignupController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService   ();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isDealer = Boolean.parseBoolean(request.getParameter("isDealer"));

        User user = new User(firstName, lastName, email, username, password, isDealer);

        response.setContentType("text/plain");

        try {
            userService.registerUser(user);
            response.getWriter().write("Signup successful!");
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
            response.getWriter().write(e.getMessage()); // e.g., "Username already taken"
        }
    }
}
