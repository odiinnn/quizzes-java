package com.example.quiz.ui.login.ui.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiz.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewAdapter> {
    List<HistoryData> list;
    Context context;
    int i = 1;

    public HistoryAdapter(List<HistoryData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_list_item, viewGroup, false);
        return new HistoryAdapter.HistoryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewAdapter holder, int i) {
        HistoryData currentItem = list.get(i);
        holder.name.setText(currentItem.getName());
        holder.score.setText(String.valueOf(currentItem.getScore()));
        i++;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewAdapter extends RecyclerView.ViewHolder {
        TextView name, score;

        public HistoryViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_history_quiz_name);
            score = itemView.findViewById(R.id.user_history_score);
        }
    }
}
