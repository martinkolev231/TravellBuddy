package com.travellbuddy.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.travellbuddy.R;
import com.travellbuddy.adapters.TripAdapter;
import com.travellbuddy.models.Trip;
import com.travellbuddy.models.User;
import com.travellbuddy.utils.Constants;
import com.travellbuddy.utils.FirebaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity is the main screen of the application
 * Displays list of available trips and provides navigation
 */
public class MainActivity extends AppCompatActivity 
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private MaterialToolbar toolbar;
    private NavigationView navigationView;
    private RecyclerView tripsRecyclerView;
    private FloatingActionButton fabCreateTrip;
    private ProgressBar progressBar;
    private View emptyStateLayout;
    
    private TripAdapter tripAdapter;
    private List<Trip> tripsList;
    private FirebaseHelper firebaseHelper;
    private SharedPreferences sharedPreferences;
    private String currentUserId;
    private boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        firebaseHelper = FirebaseHelper.getInstance();
        sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        currentUserId = sharedPreferences.getString(Constants.PREF_USER_ID, "");

        // Check if user is logged in
        if (!firebaseHelper.isUserLoggedIn() || currentUserId.isEmpty()) {
            navigateToLogin();
            return;
        }

        initializeViews();
        setupToolbar();
        setupNavigationDrawer();
        setupRecyclerView();
        loadUserData();
        loadTrips();
    }

    private void initializeViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        tripsRecyclerView = findViewById(R.id.tripsRecyclerView);
        fabCreateTrip = findViewById(R.id.fabCreateTrip);
        progressBar = findViewById(R.id.progressBar);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupRecyclerView() {
        tripsList = new ArrayList<>();
        tripAdapter = new TripAdapter(this, tripsList, trip -> {
            // Navigate to trip details
            Intent intent = new Intent(MainActivity.this, TripDetailsActivity.class);
            intent.putExtra(Constants.EXTRA_TRIP_ID, trip.getTripId());
            startActivity(intent);
        });

        tripsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripsRecyclerView.setAdapter(tripAdapter);
    }

    private void loadUserData() {
        firebaseHelper.getUserRef(currentUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            User user = snapshot.getValue(User.class);
                            if (user != null) {
                                isAdmin = user.isAdmin();
                                
                                // Save user type
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Constants.PREF_USER_TYPE, user.getUserType());
                                editor.putString(Constants.PREF_USER_NAME, user.getName());
                                editor.apply();
                                
                                // Show FAB for admin users
                                if (isAdmin) {
                                    fabCreateTrip.setVisibility(View.VISIBLE);
                                    fabCreateTrip.setOnClickListener(v -> {
                                        Intent intent = new Intent(MainActivity.this, 
                                            CreateTripActivity.class);
                                        startActivity(intent);
                                    });
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,
                            getString(R.string.error_load_data) + ": " + error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadTrips() {
        showProgress(true);

        firebaseHelper.getTripsRef()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tripsList.clear();
                        
                        for (DataSnapshot tripSnapshot : snapshot.getChildren()) {
                            Trip trip = tripSnapshot.getValue(Trip.class);
                            if (trip != null) {
                                tripsList.add(trip);
                            }
                        }
                        
                        tripAdapter.notifyDataSetChanged();
                        showProgress(false);
                        
                        // Show empty state if no trips
                        if (tripsList.isEmpty()) {
                            emptyStateLayout.setVisibility(View.VISIBLE);
                            tripsRecyclerView.setVisibility(View.GONE);
                        } else {
                            emptyStateLayout.setVisibility(View.GONE);
                            tripsRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showProgress(false);
                        Toast.makeText(MainActivity.this,
                            getString(R.string.error_load_data) + ": " + error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.menu_my_trips) {
            // TODO: Implement My Trips activity
            Toast.makeText(this, "My Trips - Coming soon", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_search) {
            startActivity(new Intent(this, SearchTripsActivity.class));
        } else if (id == R.id.menu_chat) {
            startActivity(new Intent(this, ChatListActivity.class));
        } else if (id == R.id.menu_logout) {
            showLogoutConfirmation();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLogoutConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout)
                .setMessage(R.string.confirm_logout)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    logout();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void logout() {
        // Sign out from Firebase
        firebaseHelper.signOut();
        
        // Clear SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        
        // Navigate to login
        navigateToLogin();
    }

    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
