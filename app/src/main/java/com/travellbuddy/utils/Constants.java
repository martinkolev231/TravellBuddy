package com.travellbuddy.utils;

/**
 * Constants class for TravellBuddy application
 * Contains all constant values used throughout the app
 */
public class Constants {
    
    // Firebase Database References
    public static final String USERS_REF = "users";
    public static final String TRIPS_REF = "trips";
    public static final String PARTICIPATION_REQUESTS_REF = "participationRequests";
    public static final String CHATS_REF = "chats";
    public static final String MESSAGES_REF = "messages";
    public static final String RATINGS_REF = "ratings";
    
    // User Types
    public static final String USER_TYPE_ADMIN = "admin";
    public static final String USER_TYPE_USER = "user";
    
    // Request Status
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_APPROVED = "approved";
    public static final String STATUS_REJECTED = "rejected";
    
    // Trip Categories
    public static final String CATEGORY_ADVENTURE = "adventure";
    public static final String CATEGORY_CULTURAL = "cultural";
    public static final String CATEGORY_BEACH = "beach";
    public static final String CATEGORY_MOUNTAIN = "mountain";
    public static final String CATEGORY_CITY = "city";
    public static final String CATEGORY_NATURE = "nature";
    
    // Intent Extra Keys
    public static final String EXTRA_TRIP_ID = "trip_id";
    public static final String EXTRA_USER_ID = "user_id";
    public static final String EXTRA_CHAT_ID = "chat_id";
    public static final String EXTRA_REQUEST_ID = "request_id";
    
    // Shared Preferences
    public static final String PREFS_NAME = "TravellBuddyPrefs";
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_USER_TYPE = "user_type";
    public static final String PREF_USER_NAME = "user_name";
    
    // Date Format
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    
    // Validation
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_TRIP_TITLE_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    
    // Image Upload
    public static final int IMAGE_PICK_REQUEST = 1001;
    public static final int CAMERA_REQUEST = 1002;
    public static final int PERMISSION_REQUEST = 1003;
    
    // Private constructor to prevent instantiation
    private Constants() {
    }
}
