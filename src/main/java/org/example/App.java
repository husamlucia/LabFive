package org.example;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

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
        ;
        configuration.addAnnotatedClass(CustomerGame.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static void generateData() throws Exception {

        Customer[] customers = new Customer[6];
        Game[] games = new Game[5];
        CustomerGame[] orders = new CustomerGame[5];


        customers[0] = new Customer("zHey", "Samer2", "fusamer@gmail.com");
        customers[5] = new Customer("zHey", "Samer1", "fusamer@gmail.com");

        session.save(customers[0]);
        session.save(customers[5]);
        games[0] = new Game("Game",   50.0);
        session.save(games[0]);
        for (int i = 1; i < 5; i++) {
            customers[i] = new Customer("first" + i, "last" + i, i + "@gmail.com");
            games[i] = new Game("Game" + i, i * 50.0);
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
        for (CustomerGame purchase : orders) {
            session.save(purchase);
        }


        session.flush();
    }


    private static List<Customer> getAllCustomers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        query.orderBy(builder.asc(root.get("fName")),builder.asc(root.get("lName")));
        List<Customer> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void printAllCustomers() throws Exception {
        List<Customer> customers = getAllCustomers();
        for (Customer customer : customers) {
            System.out.print("Id: ");
            System.out.print(customer.getId());
            System.out.print(", First name: ");
            System.out.print(customer.getfName());
            System.out.print(", Last name: ");
            System.out.print(customer.getlName());
            System.out.print(", Mail: ");
            System.out.print(customer.getEmail());
            System.out.print('\n');
        }
    }

    private static List<Game> getAllGames() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        query.from(Game.class);
        List<Game> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void printAllGames() throws Exception {
        List<Game> games = getAllGames();
        for (Game game : games) {
            System.out.print("Id: ");
            System.out.print(game.getId());
            System.out.print(", Game name: ");
            System.out.print(game.getName());
            System.out.print(", Price: ");
            System.out.print(game.getPrice());
            System.out.print(", Average rating: ");
            Integer rating = game.getAverageRating();
            System.out.print(rating==null?"null":rating);
            System.out.print('\n');
        }
    }


    private static Customer maxTotalPaid() throws Exception{
        double max=-1;
        Customer maxPaidCustomer=null;
        List<Customer> customers = getAllCustomers();
        for(Customer customer: customers){
            double current = customer.getTotalPaid();
            if(current > max) {
                max = current;
                maxPaidCustomer = customer;
            }
        }
        return maxPaidCustomer;
    }

    private static void printTotalPaidCustomer() throws Exception{
        Customer customer = maxTotalPaid();
        System.out.print("Customer with maximum sum of purchases");
        System.out.print(", First name: ");
        System.out.print(customer.getfName());
        System.out.print(", Last name: ");
        System.out.print(customer.getlName());
    }
    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            generateData();

            printAllCustomers();
            printAllGames();
            printTotalPaidCustomer();
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


