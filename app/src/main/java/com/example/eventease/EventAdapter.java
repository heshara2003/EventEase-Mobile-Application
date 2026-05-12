package com.example.eventease;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;
    private FirebaseFirestore db;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
        this.db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvTitle.setText(event.getTitle());
        holder.tvDate.setText(event.getDate());
        holder.tvTime.setText(event.getTime());
        holder.tvLocation.setText(event.getLocation());
        holder.tvStatus.setText(event.getStatus());

        if ("Done".equals(event.getStatus())) {
            holder.tvStatus.setTextColor(Color.parseColor("#00AA44"));
            holder.btnAccept.setVisibility(View.GONE);
            holder.btnDecline.setVisibility(View.GONE);
        } else if ("Declined".equals(event.getStatus())) {
            holder.tvStatus.setTextColor(Color.parseColor("#E81C1C"));
            holder.btnAccept.setVisibility(View.GONE);
            holder.btnDecline.setVisibility(View.GONE);
        } else {
            holder.tvStatus.setTextColor(Color.parseColor("#757575"));
            holder.btnAccept.setVisibility(View.VISIBLE);
            holder.btnDecline.setVisibility(View.VISIBLE);
        }

        holder.btnAccept.setOnClickListener(v -> showAcceptDialog(v.getContext(), position));
        holder.btnDecline.setOnClickListener(v -> showDeclineDialog(v.getContext(), position));
        holder.btnRemove.setOnClickListener(v -> showRemoveDialog(v.getContext(), position));
    }

    private void showAcceptDialog(android.content.Context context, int position) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_accept_event, null);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        dialogView.findViewById(R.id.btnConfirmAccept).setOnClickListener(v -> {
            updateStatusInFirestore(position, "Done", dialog);
        });

        dialogView.findViewById(R.id.btnCancelAccept).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void showDeclineDialog(android.content.Context context, int position) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_decline_event, null);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        dialogView.findViewById(R.id.btnConfirmDecline).setOnClickListener(v -> {
            updateStatusInFirestore(position, "Declined", dialog);
        });

        dialogView.findViewById(R.id.btnCancelDecline).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void updateStatusInFirestore(int position, String newStatus, AlertDialog dialog) {
        String docId = eventList.get(position).getId();
        if (docId == null) return;

        db.collection("events").document(docId)
                .update("status", newStatus)
                .addOnSuccessListener(aVoid -> {
                    dialog.dismiss();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(dialog.getContext(), "Error updating status", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
    }

    private void showRemoveDialog(android.content.Context context, int position) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_remove_event, null);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        dialogView.findViewById(R.id.btnConfirmRemove).setOnClickListener(v -> {
            String docId = eventList.get(position).getId();
            if (docId != null) {
                db.collection("events").document(docId)
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            dialog.dismiss();
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, "Error deleting event", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        });
            }
        });

        dialogView.findViewById(R.id.btnCancelRemove).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvTime, tvLocation, tvStatus;
        Button btnAccept, btnDecline, btnRemove;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvEventTitle);
            tvDate = itemView.findViewById(R.id.tvEventDate);
            tvTime = itemView.findViewById(R.id.tvEventTime);
            tvLocation = itemView.findViewById(R.id.tvEventLocation);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnDecline = itemView.findViewById(R.id.btnDecline);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}