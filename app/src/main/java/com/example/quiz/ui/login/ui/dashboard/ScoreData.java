package com.example.quiz.ui.login.ui.dashboard;

import org.json.JSONException;
import org.json.JSONObject;

public class ScoreData {

    String name;
    int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ScoreData(JSONObject userJSON) throws JSONException {
        setName((String) userJSON.get("userName"));
        setScore((Integer) userJSON.get("score"));
    }
}
