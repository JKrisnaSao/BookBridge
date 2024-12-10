package csc340.BookbridgeBackend;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider extends User {

    private String businessName;

    @OneToMany(mappedBy = "provider")
    private List<Book> books;

    public Provider() {
        super("", "", "","Provider"); // Set default role as Provider
    }

    public Provider(String name, String email, String accountStatus, String businessName, List<Book> books) {
        super(name, email, accountStatus,"Provider"); // Set role as Provider
        this.businessName = businessName;
        this.books = books;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}


