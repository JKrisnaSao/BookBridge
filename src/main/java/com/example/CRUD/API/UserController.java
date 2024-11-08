package com.example.CRUD.API;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;


    /**
     * Creating the user profile
     * @param userEntity
     * @return
     */
    @PostMapping("/create")
    public UserEntity createUserProfile(@RequestBody UserEntity userEntity){
        return service.createProfile(userEntity);
    }


    /**
    View profile is basically viewing one user
    */
    @GetMapping("/profile/{userId}")
    public UserEntity viewProfile(@PathVariable int userId){
        return service.getUserById(userId);
    }


    /**
     * updating the user profile
     * @param userId
     * @param userEntity
     * @return
     */
    @PutMapping("/update/{userId}")
    public UserEntity updateUserProfile(@PathVariable int userId, @RequestBody UserEntity userEntity){
      service.updateUser(userId, userEntity);
      return service.getUserById(userId);
    }



}
