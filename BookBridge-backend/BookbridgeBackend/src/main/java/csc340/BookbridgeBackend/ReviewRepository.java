package csc340.BookbridgeBackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReviewRepository extends JpaRepository<Review, Long> { }

//public interface CustomerRepository extends JpaRepository<Customer, Long> { }
//
//public interface BookRepository extends JpaRepository<Book, Long> { }
