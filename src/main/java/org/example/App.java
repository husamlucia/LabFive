package org.example;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Customer.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static void generateGames() throws Exception{

        for(int i=0;i<5;i++){
         Game game = new Game("Game"+i,i*50.0);
         session.save(game);
        }
        session.flush();
    }

    private static void generateCustomers() throws Exception{

        for(int i=0;i<5;i++){
            Customer cust = new Customer("first"+i,"last"+i,i+"@gmail.com");
            session.save(cust);
        }
        session.flush();
    }


    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            generateCustomers();
            generateGames();

            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}

