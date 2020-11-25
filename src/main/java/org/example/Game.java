package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game {
    @Column(name = "game_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinColumn(name = "owner_id")

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    public Game(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
