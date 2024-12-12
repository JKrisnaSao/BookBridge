package csc340.BookbridgeBackend;


public class ReviewRequest {

    private String content; // Make sure this matches the Review entity
    private Integer rating; // Match the entity field
    private Long customerId;
    private Long bookId;

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "content='" + content + '\'' +
                ", rating=" + rating +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                '}';
    }
}
