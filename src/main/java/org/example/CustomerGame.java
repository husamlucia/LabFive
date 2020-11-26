package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer_games")
public class CustomerGame {

    public CustomerGame(Customer customer, Game game, int rating, Date date) {
        this.customer = customer;
        this.game = game;
        this.rating = rating;
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @Column(name="rating")
    int rating;

    @Column(name="purchase_date")
    Date date;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
