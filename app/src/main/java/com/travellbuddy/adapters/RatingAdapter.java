package com.travellbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.travellbuddy.R;
import com.travellbuddy.models.Rating;
import java.util.List;

/**
 * Adapter for displaying ratings
 */
public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {
    private Context context;
    private List<Rating> ratingsList;

    public RatingAdapter(Context context, List<Rating> ratingsList) {
        this.context = context;
        this.ratingsList = ratingsList;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rating, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        // TODO: Bind rating data
    }

    @Override
    public int getItemCount() {
        return ratingsList.size();
    }

    static class RatingViewHolder extends RecyclerView.ViewHolder {
        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
