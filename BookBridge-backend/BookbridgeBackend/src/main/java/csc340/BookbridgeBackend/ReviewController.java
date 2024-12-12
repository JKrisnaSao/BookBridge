package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        System.out.println("Reviews: " + reviews); // Debug log to check reviews
        model.addAttribute("reviews", reviews);
        return "reviews"; // Maps to "reviews.html"
    }


//    @GetMapping("/all")
//    public String getAllReviews(Model model) {
//        model.addAttribute("reviews", reviewService.getAllReviews());
//        return "reviews"; // Maps to "reviews.html"
//    }

    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id) // Utilize service method
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + id));
        model.addAttribute("review", review);
        return "review-details"; // Maps to "review-details.html"
    }

    @GetMapping("/new")
    public String showCreateReviewForm(Model model) {
        model.addAttribute("reviewRequest", new ReviewRequest());
        model.addAttribute("books", bookService.getAllBooks()); // Fetch books for the dropdown
        model.addAttribute("customers", customerService.getAllCustomers()); // Fetch customers for the dropdown
        return "review-form"; // Maps to "review-form.html"
    }

    @PostMapping("/new")
    public String createReview(@ModelAttribute ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
        return "redirect:/reviews/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews/all";
    }

    @GetMapping("/review-form")
    public String reviewForm(@RequestParam(required = false) Long reviewId, Model model) {
        // Initialize review to a new Review object by default
        Review review = new Review();

        // If reviewId is provided, try to fetch the review from the database
        if (reviewId != null) {
            review = reviewService.getReviewById(reviewId)
                    .orElse(new Review()); // If no review found, initialize a new one
        }

        // Add the review object to the model
        model.addAttribute("review", review);

        return "review-form"; // Return the name of the Thymeleaf template
    }


}
