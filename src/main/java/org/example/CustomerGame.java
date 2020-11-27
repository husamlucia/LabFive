package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer_games")
public class CustomerGame {

    public CustomerGame(Customer customer, Game game) {
        this.customer = customer;
        customer.getOwned().add(this);
        this.game = game;
        game.getOwners().add(this);
        this.price = game.getPrice();
        customer.addToTotalPaid(game.getPrice());
        this.date = new Date(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @Column(name="rating")
    Integer rating;

    @Column(name="purchase_date")
    Date date;

    @Column(name="price")
    double price;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
