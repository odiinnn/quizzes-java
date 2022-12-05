package com.example.quiz.ui.login.ui.dashboard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    MutableLiveData<List<ScoreData>> list;
    private List<ScoreData> data = new ArrayList<>();

    public void setData(List<ScoreData> data) {
        this.data = data;
    }

    public DashboardViewModel(Application application) {
        super(application);
        LeaderboardUsersData leaderboardUsersData = new LeaderboardUsersData();
        leaderboardUsersData.start();
        try {
            leaderboardUsersData.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Check", leaderboardUsersData.getData().toString());
        setData(leaderboardUsersData.getData());
        list = new MutableLiveData<>();
        list.setValue(data);
    }

    public List<ScoreData> getUsers() {
        return data;
    }

}