package org.example;

import javax.persistence.*;

@Entity
@Table(name="customer_games")
public class CustomerGame {

    @ManyToOne
    @JoinColumn(name="user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @Column(name="rating")
    int rating;

}
