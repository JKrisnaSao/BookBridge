package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // Maps to "users.html"
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-details"; // Maps to "user-details.html"
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form"; // Maps to "user-form.html"
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form"; // Reuses "user-form.html"
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.getUserById(id); // Ensure user exists, throw if not.
        userService.updateAccountStatus(id, "Deleted"); // Change status instead of permanent deletion.
        return "redirect:/users/all";
    }
}
