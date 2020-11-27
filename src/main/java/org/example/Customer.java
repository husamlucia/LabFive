package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String fName;
    @Column(name = "lastname")
    private String lName;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy="customer")
    List<CustomerGame> owned;

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    private double totalPaid;

    public List<CustomerGame> getOwned() {
        return owned;
    }

    public void setOwned(List<CustomerGame> owned) {
        this.owned = owned;
    }

    public Customer(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.owned = new ArrayList<CustomerGame>();
        this.totalPaid = 0;
    }

    public void addToTotalPaid(double price){
        this.totalPaid += price;
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
