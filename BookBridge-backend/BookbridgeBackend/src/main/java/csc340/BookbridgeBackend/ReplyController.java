package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/all")
    public String getAllReplies(Model model) {
        model.addAttribute("replies", replyService.getAllReplies());
        return "replies"; // Maps to "replies.html"
    }

    @GetMapping("/{id}")
    public String getReplyById(@PathVariable Long id, Model model) {
        Reply reply = replyService.getAllReplies().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Reply not found with ID: " + id));
        model.addAttribute("reply", reply);
        return "reply-details"; // Maps to "reply-details.html"
    }

    @GetMapping("/new")
    public String showCreateReplyForm(Model model) {
        model.addAttribute("replyRequest", new ReplyRequest());
        return "reply-form"; // Maps to "reply-form.html"
    }

    @PostMapping("/new")
    public String createReply(@ModelAttribute ReplyRequest replyRequest) {
        replyService.createReply(replyRequest);
        return "redirect:/replies/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return "redirect:/replies/all";
    }
}
