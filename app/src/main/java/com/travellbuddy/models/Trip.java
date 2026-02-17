package com.travellbuddy.models;

/**
 * Model class representing a Trip/Excursion in the TravellBuddy application
 */
public class Trip {
    private String tripId;
    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private String description;
    private int maxParticipants;
    private int currentParticipants;
    private String price;
    private String category;
    private String imageUrl;
    private String organizerId;
    private String organizerName;
    private double averageRating;

    // Empty constructor required for Firebase
    public Trip() {
    }

    public Trip(String tripId, String title, String destination, String startDate, String endDate,
                String description, int maxParticipants, String price, String category,
                String organizerId, String organizerName) {
        this.tripId = tripId;
        this.title = title;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = 0;
        this.price = price;
        this.category = category;
        this.imageUrl = "";
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.averageRating = 0.0;
    }

    // Getters
    public String getTripId() {
        return tripId;
    }

    public String getTitle() {
        return title;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    // Setters
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    // Helper method to check if trip has available spots
    public boolean hasAvailableSpots() {
        return currentParticipants < maxParticipants;
    }

    // Helper method to get available spots count
    public int getAvailableSpots() {
        return maxParticipants - currentParticipants;
    }
}
