package com.travellbuddy.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Helper class for Firebase operations in TravellBuddy application
 * Provides centralized access to Firebase services
 */
public class FirebaseHelper {
    
    private static FirebaseHelper instance;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    
    // Private constructor for Singleton pattern
    private FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();
        
        // Enable offline persistence for Firebase Realtime Database
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (Exception e) {
            // Persistence already enabled or not available
        }
    }
    
    // Get singleton instance
    public static synchronized FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }
    
    // Get Firebase Authentication instance
    public FirebaseAuth getAuth() {
        return mAuth;
    }
    
    // Get current Firebase user
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
    
    // Get current user ID
    public String getCurrentUserId() {
        FirebaseUser user = getCurrentUser();
        return user != null ? user.getUid() : null;
    }
    
    // Check if user is logged in
    public boolean isUserLoggedIn() {
        return getCurrentUser() != null;
    }
    
    // Get Database reference
    public DatabaseReference getDatabase() {
        return mDatabase;
    }
    
    // Get Users reference
    public DatabaseReference getUsersRef() {
        return mDatabase.child(Constants.USERS_REF);
    }
    
    // Get specific user reference
    public DatabaseReference getUserRef(String userId) {
        return getUsersRef().child(userId);
    }
    
    // Get Trips reference
    public DatabaseReference getTripsRef() {
        return mDatabase.child(Constants.TRIPS_REF);
    }
    
    // Get specific trip reference
    public DatabaseReference getTripRef(String tripId) {
        return getTripsRef().child(tripId);
    }
    
    // Get Participation Requests reference
    public DatabaseReference getParticipationRequestsRef() {
        return mDatabase.child(Constants.PARTICIPATION_REQUESTS_REF);
    }
    
    // Get specific participation request reference
    public DatabaseReference getParticipationRequestRef(String requestId) {
        return getParticipationRequestsRef().child(requestId);
    }
    
    // Get Chats reference
    public DatabaseReference getChatsRef() {
        return mDatabase.child(Constants.CHATS_REF);
    }
    
    // Get specific chat reference
    public DatabaseReference getChatRef(String chatId) {
        return getChatsRef().child(chatId);
    }
    
    // Get Messages reference for a specific chat
    public DatabaseReference getMessagesRef(String chatId) {
        return getChatRef(chatId).child(Constants.MESSAGES_REF);
    }
    
    // Get Ratings reference
    public DatabaseReference getRatingsRef() {
        return mDatabase.child(Constants.RATINGS_REF);
    }
    
    // Get specific rating reference
    public DatabaseReference getRatingRef(String ratingId) {
        return getRatingsRef().child(ratingId);
    }
    
    // Get Storage reference
    public StorageReference getStorage() {
        return mStorage;
    }
    
    // Get profile images storage reference
    public StorageReference getProfileImagesRef() {
        return mStorage.child("profile_images");
    }
    
    // Get trip images storage reference
    public StorageReference getTripImagesRef() {
        return mStorage.child("trip_images");
    }
    
    // Generate unique ID
    public String generateUniqueId() {
        return mDatabase.push().getKey();
    }
    
    // Sign out
    public void signOut() {
        mAuth.signOut();
    }
}
