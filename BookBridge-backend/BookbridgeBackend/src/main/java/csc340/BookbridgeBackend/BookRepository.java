package csc340.BookbridgeBackend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByProviderId(Long providerId); // Fetch books by provider
}
