package csc340.BookbridgeBackend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHomepage(Model model) {
        model.addAttribute("title", "Admin Homepage");
        return "admin-homepage"; // View name (admin-homepage.html)
    }
}
