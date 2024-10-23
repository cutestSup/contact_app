package com.example.de1_2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<String> historyList;

    public HistoryAdapter(List<String> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        String history = historyList.get(position);
        holder.tvHistory.setText(history);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, historyList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvHistory;
        Button btnDelete;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHistory = itemView.findViewById(R.id.tvHistory);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
