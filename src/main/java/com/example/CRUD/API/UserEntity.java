package com.example.CRUD.API;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String major;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;


    /**
     * For the user login
     * @param email
     * @param password
     */
    public UserEntity(String email, String password){
        this.email =email;
        this.password =password;
    }

    /**
     * For the user signup
     * @param userName
     * @param email
     * @param password
     */
    public UserEntity(String userName, String email, String password){
        this.userName=userName;
        this.email =email;
        this.password =password;
}

    /**
     * Finding the user profile by there Id
     * @param userId
     * @param imageUrl
     * @param userName
     * @param major
     * @param email
     */
    public UserEntity(int userId, String imageUrl, String userName, String major, String email, String role){
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.major = major;
        this.email = email;
        this.role = role;
    }

    /**
     * For the profile
     * @param imageUrl
     * @param userName
     * @param major
     * @param email
     */
    public UserEntity(String imageUrl, String userName, String major, String email,String role){
        this.imageUrl = imageUrl;
        this.userName=userName;
        this.major=major;
        this.email=email;
        this.role=role;
    }

    public UserEntity(){}

    public int getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public String getImageUrl(){return imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
    public String getName(){return userName;}
    public void setName(String userName){this.userName=userName;}
    public String getMajor(){return major;}
    public void setMajor(String major){this.major = major;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getRole(){return role;}
    public void setRole(String role){this.role = role;}

    }

