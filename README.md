# TravellBuddy - Android Application for Shared Travel

## Project Description
TravellBuddy is a comprehensive Android application that connects people who want to find travel companions for trips and excursions. The app provides a platform for users to organize trips, find travel buddies, communicate, and share experiences.

This is a diploma/thesis project built with Java and Firebase, showcasing modern Android development practices.

## Features

### Authentication System
- ✅ User registration with email and password
- ✅ User login with email and password
- ✅ Password reset functionality
- ✅ Logout functionality
- ✅ Persistent user session

### User Profile System
**Two Types of Users:**

#### Administrator Profile
- Can create travel trips/excursions
- Can edit and delete their own trips
- Can view all participants who applied for their trips
- Can approve or reject participation requests
- Can view and manage ratings/reviews
- Profile includes: name, email, phone, bio, profile picture

#### Regular User Profile
- Can browse available trips
- Can apply to join trips
- Can chat with trip organizers and other participants
- Can rate trips they participated in
- Profile includes: name, email, phone, bio, profile picture, interests

### Trip Management
- Create, edit, and delete trips (Admin only)
- View all available trips
- Trip details include: title, destination, dates, description, max participants, price, category, image
- Categories: Adventure, Cultural, Beach, Mountain, City, Nature

### Search and Filter System
- Search trips by destination, date range, category, price range
- Sort by date, price, popularity
- View available spots for each trip

### Participation Request System
- Users can apply to join trips
- Organizers can approve or reject requests
- Track request status (pending, approved, rejected)
- View list of approved participants

### Chat/Messaging System
- One-to-one chat between users
- Real-time messaging using Firebase Realtime Database
- Message timestamps
- Unread message indicators

### Rating System
- Rate trips (1-5 stars)
- Write reviews/comments
- View average ratings
- Rate trip organizers

## Technologies Used

- **Android Studio** - IDE
- **Java** - Programming language
- **XML** - Layout design
- **Firebase Authentication** - User authentication
- **Firebase Realtime Database** - Data storage
- **Firebase Storage** - Image storage
- **Material Design Components** - UI components
- **Glide** - Image loading
- **RecyclerView** - List displays

## Project Structure

```
TravellBuddy/
├── app/
│   ├── src/main/
│   │   ├── java/com/travellbuddy/
│   │   │   ├── activities/        # All activity classes
│   │   │   ├── adapters/          # RecyclerView adapters
│   │   │   ├── models/            # Data models
│   │   │   └── utils/             # Utility classes
│   │   ├── res/
│   │   │   ├── layout/            # XML layouts
│   │   │   ├── values/            # Strings, colors, styles
│   │   │   ├── drawable/          # Images and drawables
│   │   │   └── menu/              # Menu resources
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Setup Instructions

### Prerequisites
1. **Android Studio** (latest version recommended)
2. **JDK 8 or higher**
3. **Android SDK** (API Level 24 or higher)
4. **Firebase Account**

### Firebase Configuration

#### Step 1: Create a Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Add project" and follow the setup wizard
3. Name your project "TravellBuddy" or any preferred name

#### Step 2: Add Android App to Firebase
1. In Firebase Console, click "Add app" and select Android
2. Register your app with package name: `com.travellbuddy`
3. Download the `google-services.json` file
4. Place `google-services.json` in the `app/` directory of your project

#### Step 3: Enable Firebase Authentication
1. In Firebase Console, navigate to **Authentication**
2. Click "Get Started"
3. Enable **Email/Password** sign-in method
4. Click "Save"

#### Step 4: Setup Firebase Realtime Database
1. In Firebase Console, navigate to **Realtime Database**
2. Click "Create Database"
3. Choose a location (preferably closest to your users)
4. Start in **Test Mode** for development (remember to secure in production!)
5. The database structure will be automatically created when the app runs

#### Step 5: Setup Firebase Storage
1. In Firebase Console, navigate to **Storage**
2. Click "Get Started"
3. Start in **Test Mode** for development
4. This will be used for storing profile pictures and trip images

#### Step 6: Configure Security Rules (Important for Production!)

**Realtime Database Rules:**
```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "auth != null",
        ".write": "auth != null && auth.uid == $uid"
      }
    },
    "trips": {
      ".read": "auth != null",
      "$tripId": {
        ".write": "auth != null"
      }
    },
    "participationRequests": {
      ".read": "auth != null",
      ".write": "auth != null"
    },
    "chats": {
      "$chatId": {
        ".read": "auth != null",
        ".write": "auth != null"
      }
    },
    "ratings": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
```

**Storage Rules:**
```
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read: if request.auth != null;
      allow write: if request.auth != null;
    }
  }
}
```

### Running the Application

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/martinkolev231/TravellBuddy.git
   cd TravellBuddy
   ```

2. **Open in Android Studio:**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the TravellBuddy folder and select it

3. **Add google-services.json:**
   - Place your `google-services.json` file in the `app/` directory
   - This file is required for Firebase to work

4. **Sync Project:**
   - Click "File" → "Sync Project with Gradle Files"
   - Wait for dependencies to download

5. **Run the App:**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon) in Android Studio
   - Select your device and wait for the app to install

### First Run

1. The app will open to the Login screen
2. Click "Don't have an account? Register"
3. Create a new account:
   - Enter your name, email, and password
   - Select user type (Regular User or Administrator)
   - Click Register
4. You'll be automatically logged in and taken to the main screen

### Testing Features

**As an Administrator:**
1. Register as Admin user type
2. Use the FAB (Floating Action Button) to create trips
3. View participation requests for your trips
4. Approve or reject participants

**As a Regular User:**
1. Register as Regular User type
2. Browse available trips
3. Apply to join trips
4. Chat with organizers
5. Rate trips you've participated in

## Database Structure

The Firebase Realtime Database follows this structure:

```
TravellBuddy/
├── users/
│   └── {userId}/
│       ├── name
│       ├── email
│       ├── phone
│       ├── userType (admin/user)
│       ├── bio
│       ├── profileImageUrl
│       ├── interests
│       └── averageRating
├── trips/
│   └── {tripId}/
│       ├── title
│       ├── destination
│       ├── startDate
│       ├── endDate
│       ├── description
│       ├── maxParticipants
│       ├── currentParticipants
│       ├── price
│       ├── category
│       ├── imageUrl
│       ├── organizerId
│       ├── organizerName
│       └── averageRating
├── participationRequests/
│   └── {requestId}/
│       ├── tripId
│       ├── userId
│       ├── userName
│       ├── message
│       ├── status (pending/approved/rejected)
│       └── timestamp
├── chats/
│   └── {chatId}/
│       └── messages/
│           └── {messageId}/
│               ├── senderId
│               ├── receiverId
│               ├── text
│               └── timestamp
└── ratings/
    └── {ratingId}/
        ├── tripId
        ├── userId
        ├── userName
        ├── rating (1-5)
        ├── review
        └── timestamp
```

## Building for Release

1. Generate a keystore (if you don't have one):
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-alias
   ```

2. Update `app/build.gradle` with signing config
3. Build release APK:
   ```bash
   ./gradlew assembleRelease
   ```

## Troubleshooting

### Common Issues

**Issue:** "google-services.json not found"
- **Solution:** Ensure the file is in the `app/` directory, not the project root

**Issue:** Firebase authentication fails
- **Solution:** Check that Email/Password authentication is enabled in Firebase Console

**Issue:** App crashes on startup
- **Solution:** Verify that `google-services.json` matches your Firebase project and package name is correct

**Issue:** Images not loading
- **Solution:** Check Firebase Storage rules and ensure internet permission is in AndroidManifest.xml

**Issue:** Build errors
- **Solution:** 
  - Clean project: Build → Clean Project
  - Rebuild: Build → Rebuild Project
  - Invalidate caches: File → Invalidate Caches / Restart

## Screenshots
*Screenshots will be added here after implementation*

## Future Enhancements
- Push notifications for new messages and participation requests
- Google Maps integration for trip locations
- Social media sharing
- In-app payment integration
- Multi-language support
- Dark mode theme
- Advanced search filters
- User verification system

## Contributing
This is a thesis/diploma project. Contributions are welcome for educational purposes.

## License
This project is created for educational purposes as part of a diploma/thesis project.

## Author
Martin Kolev

## Contact
For questions or support, please contact through GitHub issues.

## Acknowledgments
- Firebase Documentation
- Android Developers Documentation
- Material Design Guidelines
- Stack Overflow Community

---

**Note:** This application requires a valid Firebase configuration to run. Make sure to follow the Firebase setup instructions carefully.
