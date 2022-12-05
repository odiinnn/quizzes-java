package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.ui.login.MainActivity;


public class PointsScreenActivity extends AppCompatActivity {

    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_screen);

        Bundle extras = getIntent().getExtras();
        points = extras.getInt("points");

        pointsText();
        returnToMenuButton();
    }

    private void pointsText() {
        TextView pointsTxt = findViewById(R.id.points_text2);
        pointsTxt.setText("Points: " + points);
    }

    private void returnToMenuButton() {
        Button returnToMenuBtn = findViewById(R.id.return_to_menu_btn);
        returnToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PointsScreenActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}