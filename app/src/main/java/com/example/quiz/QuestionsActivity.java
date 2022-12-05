package com.example.quiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quiz.data.Question;
import com.example.quiz.data.QuestionManager;
import com.example.quiz.databinding.ActivityQuestionsBinding;
import com.example.quiz.ui.login.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuestionsBinding binding;
    private int currRound = 0;

    ArrayList<JSONObject> questions;
    Question currQuestion;
    QuestionManager questionManager;

    TextView questionTitle;
    Button button_answer_1;
    Button button_answer_2;
    Button button_answer_3;
    Button button_answer_4;

    Button button_correct_answer;

    Button continueButton;

    TextView pointsText;

    private int points;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questionTitle = findViewById(R.id.question_text);
        button_answer_1 = findViewById(R.id.answer_1);
        button_answer_2 = findViewById(R.id.answer_2);
        button_answer_3 = findViewById(R.id.answer_3);
        button_answer_4 = findViewById(R.id.answer_4);

        continueButton = findViewById(R.id.continue_button);
        continueButton.setVisibility(View.GONE);

        pointsText = findViewById(R.id.points_text);


        questionManager = new QuestionManager();
        questionManager.start();
        try {
            questionManager.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        updatePointsText();
        generateQuestion();
        randomizeAnswers();

        button_answer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_1);
            }
        });

        button_answer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_2);
            }
        });

        button_answer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_3);
            }
        });

        button_answer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_4);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {nextQuestion();}
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkForCorrectAnswer(Button button) {
        showButtonColors();
        if (button.getText().equals(currQuestion.getCorrectAnswer())) {
            win();
        } else {
            lose();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateQuestion() {
        questions = questionManager.getQuestionsJSON();
        Log.d("data", questions.toString());
        try {

            currQuestion = new Question(questions.get(currRound));
            questionTitle.setText(currQuestion.getTitleQuestion());

            Log.d("Q1", currQuestion.getTitleQuestion());
            Log.d("Q1", "Correct: " + currQuestion.getCorrectAnswer());
            Log.d("Q1", currQuestion.getIncorrectAnswer0());
            Log.d("Q1", currQuestion.getIncorrectAnswer1());
            Log.d("Q1", currQuestion.getIncorrectAnswer2());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void lose() {
        continueButton.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void win() {
        points++;
        continueButton.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextQuestion() {
        updatePointsText();
        if (currRound == 9) {
            gameFinished();
        } else {
            continueButton.setVisibility(View.GONE);
            currRound++;
            hideButtonColors();
            generateQuestion();
            randomizeAnswers();
        }
    }

    private void updatePointsText() {
        pointsText.setText("Очки: " + points + "/" + 9);
    }

    private void gameFinished() {
        Intent i = new Intent(this, PointsScreenActivity.class);
        i.putExtra("points", points);
        startActivity(i);
    }

    private void randomizeAnswers() {
        int max = 3;
        int min = 0;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        ArrayList<Button> answers = new ArrayList<>();
        answers.add(button_answer_1);
        answers.add(button_answer_2);
        answers.add(button_answer_3);
        answers.add(button_answer_4);

        answers.get(randomNum).setText(currQuestion.getCorrectAnswer());
        button_correct_answer = answers.get(randomNum);
        answers.remove(randomNum);

        answers.get(0).setText(currQuestion.getIncorrectAnswer0());
        answers.get(1).setText(currQuestion.getIncorrectAnswer1());
        answers.get(2).setText(currQuestion.getIncorrectAnswer2());
    }

    private void showButtonColors() {
        button_answer_1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_correct_answer.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.myGreen));
    }


    private void hideButtonColors() {
        button_answer_1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_blue_400));
        button_answer_2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_blue_400));
        button_answer_3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_blue_400));
        button_answer_4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_blue_400));
    }


}