package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    // Manage User Access

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{userId}/ban")
    public User banUser(@PathVariable Long userId) {
        return userService.updateAccountStatus(userId, "banned");
    }

    @PutMapping("/users/{userId}/unban")
    public User unbanUser(@PathVariable Long userId) {
        return userService.updateAccountStatus(userId, "active");
    }

    // Moderate Books

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
    }

    // Moderate Reviews

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    // Moderate Replies

    @GetMapping("/replies")
    public List<Reply> getAllReplies() {
        return replyService.getAllReplies();
    }

    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
}
