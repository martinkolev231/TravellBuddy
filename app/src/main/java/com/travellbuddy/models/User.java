package com.travellbuddy.models;

/**
 * Model class representing a User in the TravellBuddy application
 * Supports both Administrator and Regular User profiles
 */
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String userType; // "admin" or "user"
    private String bio;
    private String profileImageUrl;
    private String interests;
    private double averageRating;

    // Empty constructor required for Firebase
    public User() {
    }

    public User(String userId, String name, String email, String userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.phone = "";
        this.bio = "";
        this.profileImageUrl = "";
        this.interests = "";
        this.averageRating = 0.0;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserType() {
        return userType;
    }

    public String getBio() {
        return bio;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getInterests() {
        return interests;
    }

    public double getAverageRating() {
        return averageRating;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    // Helper method to check if user is admin
    public boolean isAdmin() {
        return "admin".equals(userType);
    }
}
