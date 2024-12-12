package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Creates a new review with the provided review request.
     *
     * @param reviewRequest contains review content, rating, customer ID, and book ID.
     * @return the saved Review object.
     */
    public Review createReview(ReviewRequest reviewRequest) {
        // Validate and retrieve customer
        Customer customer = customerRepository.findById(reviewRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + reviewRequest.getCustomerId()));

        // Validate and retrieve book
        Book book = bookRepository.findById(reviewRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + reviewRequest.getBookId()));

        // Create and save the review
        Review review = new Review();
        review.setContent(reviewRequest.getContent());
        review.setRating(reviewRequest.getRating());
        review.setCustomer(customer);
        review.setBook(book);

        return reviewRepository.save(review);
    }

    /**
     * Retrieves all reviews from the database.
     *
     * @return a list of Review objects.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId the ID of the review.
     * @return an Optional containing the Review if found, or empty if not.
     */
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId the ID of the review to be deleted.
     */
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}

