package com.kpit.vehicleavailability.controller;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addVehicle")
public class AddVehicleController extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type of the response
        response.setContentType("application/json");

        // Retrieve form data from the request
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String priceStr = request.getParameter("price");
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        // Validate input data
        if (name == null || name.isEmpty() || model == null || model.isEmpty() || priceStr == null || priceStr.isEmpty()
                || status == null || status.isEmpty() || description == null || description.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            response.getWriter().write("{\"message\": \"Missing or invalid data\"}");
            return;
        }

        // Convert price to double
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            response.getWriter().write("{\"message\": \"Invalid price format\"}");
            return;
        }

        // Create a new vehicle object
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setModel(model);
        vehicle.setPrice(price);
        vehicle.setStatus(status);
        vehicle.setDescription(description);

        // Call the service to save the vehicle
        boolean isVehicleAdded = vehicleService.addVehicle(vehicle);

        // Send appropriate response
        if (isVehicleAdded) {
            response.setStatus(HttpServletResponse.SC_CREATED); // 201
            response.getWriter().write("{\"message\": \"Vehicle added successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.getWriter().write("{\"message\": \"Failed to add vehicle\"}");
        }
    }
}
