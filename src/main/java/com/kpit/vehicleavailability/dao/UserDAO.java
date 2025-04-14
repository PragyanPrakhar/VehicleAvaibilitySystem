package com.kpit.vehicleavailability.dao;

import com.kpit.vehicleavailability.model.User;
import com.kpit.vehicleavailability.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {

    public void saveUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username AND password = :password",
                    User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResult();
        }
    }
    public boolean userExists(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult() != null;
        }
    }
    
}
