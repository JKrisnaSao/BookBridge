package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sysadmin")
public class SysAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private OrderService orderService;

    // Manage Users
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // Maps to "users.html"
    }

    @GetMapping("/users/ban/{userId}")
    public String banUser(@PathVariable Long userId) {
        userService.updateAccountStatus(userId, "banned");
        return "redirect:/sysadmin/users";
    }

    @GetMapping("/users/unban/{userId}")
    public String unbanUser(@PathVariable Long userId) {
        userService.updateAccountStatus(userId, "active");
        return "redirect:/sysadmin/users";
    }

    // Manage Books
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // Reuses "books.html"
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/sysadmin/books";
    }

    // Manage Reviews
    @GetMapping("/reviews")
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews"; // Reuses "reviews.html"
    }

    @GetMapping("/reviews/delete/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/sysadmin/reviews";
    }

    // Manage Replies
    @GetMapping("/replies")
    public String getAllReplies(Model model) {
        model.addAttribute("replies", replyService.getAllReplies());
        return "replies"; // Reuses "replies.html"
    }

    @GetMapping("/replies/delete/{replyId}")
    public String deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/sysadmin/replies";
    }
}
