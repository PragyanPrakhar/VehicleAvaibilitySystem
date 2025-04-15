package com.kpit.vehicleavailability.controller;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleController extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        // Initialize VehicleService
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all vehicles from the database
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        // Set the response content type
        response.setContentType("application/json");

        // Write the list of vehicles as JSON response
        response.getWriter().write(new org.json.JSONArray(vehicles).toString());
    }
}
