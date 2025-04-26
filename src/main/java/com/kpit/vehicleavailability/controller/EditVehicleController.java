// package com.kpit.vehicleavailability.controller;

// import com.kpit.vehicleavailability.model.Vehicle;
// import com.kpit.vehicleavailability.service.VehicleService;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.*;
// import java.io.IOException;

// @WebServlet("/editVehicle")
// public class EditVehicleController extends HttpServlet {

//     private VehicleService vehicleService;

//     @Override
//     public void init() throws ServletException {
//         vehicleService = new VehicleService();
//     }

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         response.setContentType("application/json");

//         // Check if the user is logged in and is a dealer
//         HttpSession session = request.getSession(false);
//         if (session == null || session.getAttribute("isDealer") == null
//                 || !(Boolean) session.getAttribute("isDealer")) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
//             response.getWriter().write("{\"message\": \"You must be logged in as a dealer to edit a vehicle\"}");
//             return;
//         }

//         // Retrieve the vehicle ID from the URL
//         String vehicleIdStr = request.getParameter("id");

//         // Validate and parse the input data
//         String name = request.getParameter("name");
//         String model = request.getParameter("model");
//         String priceStr = request.getParameter("price");
//         String status = request.getParameter("status");
//         String description = request.getParameter("description");

//         if (vehicleIdStr == null || vehicleIdStr.isEmpty() || name == null || name.isEmpty() || model == null
//                 || model.isEmpty()
//                 || priceStr == null || priceStr.isEmpty() || status == null || status.isEmpty() || description == null
//                 || description.isEmpty()) {
//             response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
//             response.getWriter().write("{\"message\": \"Missing or invalid data\"}");
//             return;
//         }

//         // Convert the vehicle ID
//         Long vehicleId;
//         try {
//             vehicleId = Long.parseLong(vehicleIdStr);
//         } catch (NumberFormatException e) {
//             response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
//             response.getWriter().write("{\"message\": \"Invalid vehicle ID format\"}");
//             return;
//         }

//         // Convert price to double (support both integers and decimals)
//         double price;
//         try {
//             price = Double.parseDouble(priceStr);
//         } catch (NumberFormatException e) {
//             response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
//             response.getWriter().write("{\"message\": \"Invalid price format\"}");
//             return;
//         }

//         // Fetch the existing vehicle
//         Vehicle existingVehicle = vehicleService.getVehicleById(vehicleId);
//         if (existingVehicle == null) {
//             response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
//             response.getWriter().write("{\"message\": \"Vehicle not found\"}");
//             return;
//         }

//         // Update the vehicle details (vehicle ID should not change)
//         existingVehicle.setName(name);
//         existingVehicle.setModel(model);
//         existingVehicle.setPrice(price);
//         existingVehicle.setStatus(status);
//         existingVehicle.setDescription(description);

//         // Update the vehicle in the database
//         boolean isUpdated = vehicleService.updateVehicle(existingVehicle);
//         if (isUpdated) {
//             response.setStatus(HttpServletResponse.SC_OK); // 200
//             response.getWriter().write("{\"message\": \"Vehicle updated successfully\"}");
//         } else {
//             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
//             response.getWriter().write("{\"message\": \"Failed to update vehicle\"}");
//         }
//     }
// }

package com.kpit.vehicleavailability.controller;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editVehicle")
public class EditVehicleController extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        // Check if the user is logged in and is a dealer
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isDealer") == null
                || !(Boolean) session.getAttribute("isDealer")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"message\": \"You must be logged in as a dealer to edit a vehicle\"}");
            return;
        }

        // Retrieve form parameters
        String vehicleIdStr = request.getParameter("id");
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String priceStr = request.getParameter("price");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String color = request.getParameter("color");
        String mileageStr = request.getParameter("mileage");
        String yearStr = request.getParameter("year");

        // Basic validation
        if (vehicleIdStr == null || vehicleIdStr.isEmpty() ||
                name == null || name.isEmpty() ||
                model == null || model.isEmpty() ||
                priceStr == null || priceStr.isEmpty() ||
                status == null || status.isEmpty() ||
                description == null || description.isEmpty() ||
                imageUrl == null || imageUrl.isEmpty() ||
                color == null || color.isEmpty() ||
                mileageStr == null || mileageStr.isEmpty() ||
                yearStr == null || yearStr.isEmpty()) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"Missing or invalid data\"}");
            return;
        }

        // Parse values
        Long vehicleId;
        double price;
        double mileage;
        int year;

        try {
            vehicleId = Long.parseLong(vehicleIdStr);
            price = Double.parseDouble(priceStr);
            mileage = Double.parseDouble(mileageStr);
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"Invalid numeric format in ID, price, mileage, or year\"}");
            return;
        }

        // Fetch existing vehicle
        Vehicle existingVehicle = vehicleService.getVehicleById(vehicleId);
        if (existingVehicle == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\": \"Vehicle not found\"}");
            return;
        }

        // Update fields
        existingVehicle.setName(name);
        existingVehicle.setModel(model);
        existingVehicle.setPrice(price);
        existingVehicle.setStatus(status);
        existingVehicle.setDescription(description);
        existingVehicle.setImageUrl(imageUrl);
        existingVehicle.setColor(color);
        existingVehicle.setMileage(mileage);
        existingVehicle.setYear(year);

        // Update in DB
        boolean isUpdated = vehicleService.updateVehicle(existingVehicle);
        if (isUpdated) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Vehicle updated successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Failed to update vehicle\"}");
        }
    }
}
