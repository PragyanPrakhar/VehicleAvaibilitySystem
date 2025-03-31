package com.kpit.vehicleavailability.service;

import com.kpit.vehicleavailability.dao.VehicleDAO;
import com.kpit.vehicleavailability.model.Vehicle;

import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO;

    public VehicleService() {
        vehicleDAO = new VehicleDAO();
    }

    public List<Vehicle> getAllVehicles() {
        // Fetch all vehicles from the DAO
        return vehicleDAO.getAllVehicles();
    }
}
