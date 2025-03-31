package com.kpit.vehicleavailability.dao;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class VehicleDAO {

    // Fetch all vehicles from the database
    public List<Vehicle> getAllVehicles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetching all vehicles using Hibernate Query Language (HQL)
            Query<Vehicle> query = session.createQuery("FROM Vehicle", Vehicle.class);
            return query.list();
        }
    }
}
