package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/new")
    public Reply createReply(@RequestBody ReplyRequest replyRequest) {
        return replyService.createReply(replyRequest);
    }
}
