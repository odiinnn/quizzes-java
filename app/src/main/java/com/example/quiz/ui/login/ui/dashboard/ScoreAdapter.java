package com.example.quiz.ui.login.ui.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewAdapter> {

    List<ScoreData> list;
    Context context;
    int i = 1;

    public ScoreAdapter(List<ScoreData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_list_item, viewGroup, false);
        return new ScoreViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewAdapter holder, int i) {
        ScoreData currentItem = list.get(i);
        holder.name.setText(currentItem.getName());
        holder.score.setText(String.valueOf(currentItem.getScore()));
        holder.rank.setText(String.valueOf(i));
        i++;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScoreViewAdapter extends RecyclerView.ViewHolder {
        TextView name, score, rank;

        public ScoreViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.score_username);
            score = itemView.findViewById(R.id.score_user_rank);
            rank = itemView.findViewById(R.id.score_user_result);
        }
    }

}
