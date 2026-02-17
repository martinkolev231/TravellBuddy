package com.travellbuddy.models;

/**
 * Model class representing a Participation Request in the TravellBuddy application
 */
public class ParticipationRequest {
    private String requestId;
    private String tripId;
    private String userId;
    private String userName;
    private String message;
    private String status; // "pending", "approved", "rejected"
    private long timestamp;

    // Empty constructor required for Firebase
    public ParticipationRequest() {
    }

    public ParticipationRequest(String requestId, String tripId, String userId, String userName,
                                String message, String status, long timestamp) {
        this.requestId = requestId;
        this.tripId = tripId;
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters
    public String getRequestId() {
        return requestId;
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

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Helper methods
    public boolean isPending() {
        return "pending".equals(status);
    }

    public boolean isApproved() {
        return "approved".equals(status);
    }

    public boolean isRejected() {
        return "rejected".equals(status);
    }
}
