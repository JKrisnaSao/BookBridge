package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user (Customer or Provider)
    @PostMapping("/new")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update user details
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

//    // Update user details
//    @PutMapping("/update/{userId}")
//    public User updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
//        return userService.updateUser(userId, userDetails);
//    }

    // View all users (for SysAdmin)
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    @PutMapping("/update/status/{userId}")
    public User updateAccountStatus(@PathVariable Long userId, @RequestBody Map<String, String> status) {
        String accountStatus = status.get("accountStatus");
        return userService.updateAccountStatus(userId, accountStatus);
    }

//    // Update account status (for SysAdmin)
//    @PutMapping("/update/status/{userId}")
//    public User updateAccountStatus(@PathVariable Long userId, @RequestBody String accountStatus) {
//        return userService.updateAccountStatus(userId, accountStatus);
//    }



    // help class to wrap account status in JSON format
    public static class AccountStatusRequest {
        private String accountStatus;

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }
    }



}
