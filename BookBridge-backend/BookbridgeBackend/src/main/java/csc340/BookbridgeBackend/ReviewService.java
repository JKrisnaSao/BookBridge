package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    public Review createReview(ReviewRequest reviewRequest) {
        Customer customer = customerRepository.findById(reviewRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Book book = bookRepository.findById(reviewRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setContent(reviewRequest.getContent());
        review.setRating(reviewRequest.getRating());
        review.setCustomer(customer);
        review.setBook(book);

        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

