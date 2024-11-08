package com.example.CRUD.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.Year;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createProfile(UserEntity user) {
        return userRepo.save(user);
    }

    public UserEntity getUserById(int userId) {
    return userRepo.findById(userId).orElse(null);
    }


    public void updateUser(int userId, UserEntity userEntity) {
        UserEntity exist = getUserById(userId);
        exist.setImageUrl(userEntity.getImageUrl());
        exist.setName(userEntity.getName());
        exist.setMajor(userEntity.getMajor());
        exist.setEmail(userEntity.getEmail());
        exist.setRole(userEntity.getRole());
        userRepo.save(userEntity);
    }

    public UserEntity findByEmail(String username){
        return userRepo.findByUserName(username).orElseThrow(()
                -> new UsernameNotFoundException(username + "not found"));
    }


    }

