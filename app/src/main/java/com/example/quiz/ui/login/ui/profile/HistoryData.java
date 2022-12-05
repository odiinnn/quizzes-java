package com.example.quiz.ui.login.ui.profile;

public class HistoryData {

    String name;
    long score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public HistoryData(String name, long score) {
        this.name = name;
        this.score = score;
    }
}
