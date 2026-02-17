package com.travellbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.travellbuddy.R;
import com.travellbuddy.models.Trip;

import java.util.List;

/**
 * Adapter for displaying Trip items in RecyclerView
 */
public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {

    private Context context;
    private List<Trip> tripsList;
    private OnTripClickListener listener;

    public interface OnTripClickListener {
        void onTripClick(Trip trip);
    }

    public TripAdapter(Context context, List<Trip> tripsList, OnTripClickListener listener) {
        this.context = context;
        this.tripsList = tripsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = tripsList.get(position);
        
        holder.tripTitleTextView.setText(trip.getTitle());
        holder.destinationTextView.setText(trip.getDestination());
        holder.datesTextView.setText(trip.getStartDate() + " - " + trip.getEndDate());
        holder.priceTextView.setText(trip.getPrice());
        holder.spotsTextView.setText(trip.getAvailableSpots() + " " + 
            context.getString(R.string.available_spots));
        
        // Set rating
        holder.ratingBar.setRating((float) trip.getAverageRating());
        holder.ratingTextView.setText(String.format("%.1f", trip.getAverageRating()));
        
        // Load image with Glide
        if (trip.getImageUrl() != null && !trip.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(trip.getImageUrl())
                    .placeholder(R.color.backgroundSecondary)
                    .error(R.color.backgroundSecondary)
                    .into(holder.tripImageView);
        } else {
            holder.tripImageView.setImageResource(R.color.backgroundSecondary);
        }
        
        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTripClick(trip);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }

    static class TripViewHolder extends RecyclerView.ViewHolder {
        ImageView tripImageView;
        TextView tripTitleTextView, destinationTextView, datesTextView;
        TextView priceTextView, spotsTextView, ratingTextView;
        RatingBar ratingBar;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripImageView = itemView.findViewById(R.id.tripImageView);
            tripTitleTextView = itemView.findViewById(R.id.tripTitleTextView);
            destinationTextView = itemView.findViewById(R.id.destinationTextView);
            datesTextView = itemView.findViewById(R.id.datesTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            spotsTextView = itemView.findViewById(R.id.spotsTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
        }
    }
}
