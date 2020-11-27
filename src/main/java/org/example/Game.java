package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "game")
    List<CustomerGame> owners;


    public List<CustomerGame> getOwners() {
        return owners;
    }

    public void setOwners(List<CustomerGame> owners) {
        this.owners = owners;
    }


    public Game(String name, double price) {
        this.name = name;
        this.price = price;
        this.owners = new ArrayList<CustomerGame>();
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
