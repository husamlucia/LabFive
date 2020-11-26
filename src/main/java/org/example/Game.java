package org.example;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(
            mappedBy="game",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = CustomerGame.class
    )
    @JoinTable(
            name="games_customers",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> owners;



    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    public Game(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public List<Customer> getOwners() {
        return owners;
    }

    public void setOwners(List<Customer> owners) {
        this.owners = owners;
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
