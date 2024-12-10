package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/all")
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews"; // Maps to "reviews.html"
    }

    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        Review review = reviewService.getAllReviews().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + id));
        model.addAttribute("review", review);
        return "review-details"; // Maps to "review-details.html"
    }

    @GetMapping("/new")
    public String showCreateReviewForm(Model model) {
        model.addAttribute("reviewRequest", new ReviewRequest());
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
}
