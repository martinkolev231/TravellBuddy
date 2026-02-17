package com.travellbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.travellbuddy.R;
import com.travellbuddy.models.ParticipationRequest;
import java.util.List;

/**
 * Adapter for displaying participants
 */
public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {
    private Context context;
    private List<ParticipationRequest> participantsList;

    public ParticipantAdapter(Context context, List<ParticipationRequest> participantsList) {
        this.context = context;
        this.participantsList = participantsList;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_participant, parent, false);
        return new ParticipantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        // TODO: Bind participant data
    }

    @Override
    public int getItemCount() {
        return participantsList.size();
    }

    static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
