package org.example;

import java.util.Date;
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
        configuration.addAnnotatedClass(Customer.class);;
        configuration.addAnnotatedClass(CustomerGame.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }



    private static void generateData() throws Exception{

        Customer[] customers = new Customer[5];
        Game[] games = new Game[5];
        CustomerGame[] orders = new CustomerGame[5];

        for(int i=0;i<5;i++){
            customers[i] = new Customer("first"+i,"last"+i,i+"@gmail.com");
            games[i] = new Game("Game"+i,i*50.0);
            session.save(customers[i]);
            session.save(games[i]);
        }

        orders[0] = new CustomerGame(customers[0], games[1]);
        orders[1] = new CustomerGame(customers[0], games[3]);
        orders[2] = new CustomerGame(customers[1], games[0]);
        orders[3] = new CustomerGame(customers[1], games[2]);
        orders[4] = new CustomerGame(customers[2], games[3]);


        orders[0].setRating(1);
        orders[1].setRating(2);
        orders[2].setRating(5);
        orders[3].setRating(3);
        orders[4].setRating(4);
        for(CustomerGame purchase: orders){
            session.save(purchase);
        }


        session.flush();
    }


    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            generateData();

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

