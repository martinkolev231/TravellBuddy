package com.travellbuddy.models;

/**
 * Model class representing a Rating/Review in the TravellBuddy application
 */
public class Rating {
    private String ratingId;
    private String tripId;
    private String userId;
    private String userName;
    private float rating; // 1-5 stars
    private String review;
    private long timestamp;

    // Empty constructor required for Firebase
    public Rating() {
    }

    public Rating(String ratingId, String tripId, String userId, String userName,
                  float rating, String review, long timestamp) {
        this.ratingId = ratingId;
        this.tripId = tripId;
        this.userId = userId;
        this.userName = userName;
        this.rating = rating;
        this.review = review;
        this.timestamp = timestamp;
    }

    // Getters
    public String getRatingId() {
        return ratingId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public float getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
