package com.kpit.vehicleavailability.dao;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.util.HibernateUtil;

import org.hibernate.Transaction;

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
    public Vehicle getVehicleById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Vehicle.class, id);
        }
    }
    public boolean addVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(vehicle);  // Save vehicle
            transaction.commit();  // Commit the transaction
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback on error
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateVehicle(Vehicle vehicle) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(vehicle);  // Update the vehicle in the database
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
