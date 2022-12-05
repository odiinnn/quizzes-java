package com.example.quiz.ui.login.ui.profile;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private List<HistoryData> data = new ArrayList<>();

    public ProfileViewModel(Application application) {
        super(application);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);
        String name = preferences.getString("Name", "");
        mText = new MutableLiveData<>();
        mText.setValue(name);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public List<HistoryData> getHistory() {
        data.add(new HistoryData("QUiz 1", 6));
        return data;
    }

}