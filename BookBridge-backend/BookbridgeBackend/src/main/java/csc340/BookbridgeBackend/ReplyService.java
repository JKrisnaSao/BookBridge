package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Reply createReply(ReplyRequest replyRequest) {
        Provider provider = providerRepository.findById(replyRequest.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Review review = reviewRepository.findById(replyRequest.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Reply reply = new Reply();
        reply.setContent(replyRequest.getContent());
        reply.setProvider(provider);
        reply.setReview(review);

        // reply in the review entity
        review.setReply(reply);

        return replyRepository.save(reply);
    }

    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}




