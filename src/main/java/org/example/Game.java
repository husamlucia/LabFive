package org.example;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    Set<Customer> owners;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    public Game(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public Set<Customer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Customer> owners) {
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
