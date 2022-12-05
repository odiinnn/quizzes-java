package com.example.quiz.data;

import org.json.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuestionManager extends Thread {

    ArrayList<JSONObject> questionsJSON = new ArrayList<>();

    public QuestionManager() {
    }

    private void fetchJson() throws IOException, JSONException, InterruptedException {
        String data = "";
        try {
            URL url = new URL("https://my-json-server.typicode.com/odiinnn/mock-answers/db");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }

            if (!data.isEmpty()) {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray results = jsonObject.getJSONArray("questions");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject q = results.getJSONObject(i);
                    questionsJSON.add(q);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JSONObject> getQuestionsJSON() {
        return questionsJSON;
    }


    @Override
    public void run() {
        try {
            fetchJson();
        } catch (JSONException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

