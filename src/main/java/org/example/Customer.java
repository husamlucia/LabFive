package org.example;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "customers",
            targetEntity = Game.class
    )
    @JoinTable(name = "customer_games",
            joinColumns = {@JoinColumn(name="time"),@JoinColumn(name="rating")},
            inverseJoinColumns = @JoinColumn(name = "price", referencedColumnName = "price")
    )
    Set<Game> ownedGames;


    @Column(name = "firstname")
    private String fName;
    @Column(name = "lastname")
    private String lName;
    @Column(name = "email")
    private String email;

    public Customer(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
