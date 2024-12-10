package csc340.BookbridgeBackend;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;


    public Customer() {
        super("", "", "","Customer");
    }

    // Constructor w fields
    public Customer(String name, String email, String accountStatus, String role) {
        super(name, email, accountStatus, role);  // call to parent constructor
        this.orders = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

