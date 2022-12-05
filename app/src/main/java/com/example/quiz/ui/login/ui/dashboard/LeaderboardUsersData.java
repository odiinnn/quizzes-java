package com.example.quiz.ui.login.ui.dashboard;

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

public class LeaderboardUsersData extends Thread {

    private List<ScoreData> data = new ArrayList<>();

    public List<ScoreData> getData() {
        return data;
    }

    public void fetchData() {
        String dataFetch = "";
        URL url = null;
        try {
            url = new URL("https://fc8b-37-45-145-132.eu.ngrok.io");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                dataFetch = dataFetch + line;
            }

            if (!dataFetch.isEmpty()) {
                JSONArray jsonArray = new JSONArray(dataFetch);
                for (int k = 0; k < jsonArray.length(); k++) {
                    JSONObject q = jsonArray.getJSONObject(k);
                    ScoreData user = new ScoreData(q);
                    data.add(user);
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        fetchData();
    }

}
