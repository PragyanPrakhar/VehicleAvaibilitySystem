// package com.kpit;

// import com.kpit.vehicleavailability.model.Vehicle;
// // import com.kpit.vehicleavailability.dao.VehicleDAO;
// import com.kpit.vehicleavailability.util.HibernateUtil;
// import org.hibernate.Session;
// import org.hibernate.Transaction;

// public class App {
//     public static void main(String[] args) {
//         // Open Hibernate session
//         Session session = HibernateUtil.getSessionFactory().openSession();
//         Transaction transaction = session.beginTransaction();

//         try {
//             // Adding sample vehicles
//             Vehicle v1 = new Vehicle("Toyota Camry", "Sedan", 2023, "Available", "Comfortable midsize sedan");
//             Vehicle v2 = new Vehicle("Ford Mustang", "Sports", 2022, "Sold", "High-performance muscle car");
//             Vehicle v3 = new Vehicle("Tesla Model 3", "Electric", 2024, "Available", "Modern electric sedan");

//             // Save to database
//             session.persist(v1);
//             session.persist(v2);
//             session.persist(v3);

//             // Commit transaction
//             transaction.commit();
//             System.out.println("Sample vehicles added successfully!");

//         } catch (Exception e) {
//             if (transaction != null) transaction.rollback();
//             e.printStackTrace();
//         } finally {
//             session.close();
//             // HibernateUtil.shutdown();
//         }
//     }
// }

package com.kpit;

import com.kpit.vehicleavailability.model.Vehicle;
import com.kpit.vehicleavailability.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        // Open Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Adding sample vehicles (with full 9 parameters)
            Vehicle v1 = new Vehicle(
                    "Toyota Camry",
                    "Sedan",
                    25000.0,
                    "Comfortable midsize sedan",
                    "Available",
                    "https://example.com/camry.jpg",
                    "White",
                    15.5,
                    2023);

            Vehicle v2 = new Vehicle(
                    "Ford Mustang",
                    "Sports",
                    35000.0,
                    "High-performance muscle car",
                    "Sold",
                    "https://example.com/mustang.jpg",
                    "Red",
                    10.2,
                    2022);

            Vehicle v3 = new Vehicle(
                    "Tesla Model 3",
                    "Electric",
                    40000.0,
                    "Modern electric sedan",
                    "Available",
                    "https://example.com/tesla.jpg",
                    "Black",
                    20.5,
                    2024);

            // Save to database
            session.persist(v1);
            session.persist(v2);
            session.persist(v3);

            // Commit transaction
            transaction.commit();
            System.out.println("Sample vehicles added successfully!");

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
