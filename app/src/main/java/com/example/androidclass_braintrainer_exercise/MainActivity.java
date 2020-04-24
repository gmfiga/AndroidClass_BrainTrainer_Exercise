package com.example.androidclass_braintrainer_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    androidx.gridlayout.widget.GridLayout gridLayout;
    TextView timer;
    TextView score;
    TextView question;
    TextView answerText;
    Button goButton;
    Button playAgain;
    Button numberUL;
    Button numberUR;
    Button numberLL;
    Button numberLR;
    double questionLeft;
    double questionRight;
    double answer1;
    double answer2;
    double answer3;
    double rightAnswerNumber;
    int scoreCounter;
    int questionsCounter;

    public void startGame(View view) {

        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.INVISIBLE);

        assignNumbers();

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText((int) millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                numberUL.setEnabled(false);
                numberUR.setEnabled(false);
                numberLL.setEnabled(false);
                numberLR.setEnabled(false);
                answerText.setText("TIME'S UP!");
            }
        }.start();

    }


    private void assignNumbers() {

        questionLeft = getRandomIntegerBetweenRange(0, 100);
        questionRight = getRandomIntegerBetweenRange(0, 100);
        answer1 = getRandomIntegerBetweenRange(0, 100);
        answer2 = getRandomIntegerBetweenRange(0, 100);
        answer3 = getRandomIntegerBetweenRange(0, 100);
        rightAnswerNumber = questionLeft + questionRight;

        question.setText(questionLeft+ " + " +questionRight);

        int tag = 3;

        switch (tag){

            case 1:
                numberUR.setText(Double.toString(rightAnswerNumber));
                numberUL.setText(Double.toString(answer1));
                numberLL.setText(Double.toString(answer2));
                numberLR.setText(Double.toString(answer3));
            case 2:
                numberUL.setText(Double.toString(rightAnswerNumber));
                numberUR.setText(Double.toString(answer1));
                numberLL.setText(Double.toString(answer2));
                numberLR.setText(Double.toString(answer3));
            case 3:
                numberLL.setText(Double.toString(rightAnswerNumber));
                numberUL.setText(Double.toString(answer1));
                numberUR.setText(Double.toString(answer2));
                numberLR.setText(Double.toString(answer3));
            case 4:
                numberLR.setText(Double.toString(rightAnswerNumber));
                numberUL.setText(Double.toString(answer1));
                numberLL.setText(Double.toString(answer2));
                numberUR.setText(Double.toString(answer3));
        }
    }

    public static double getRandomIntegerBetweenRange(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public void checkAnswer(View view) {

        questionsCounter++;
        Button button = (Button) view;

        if (button.getText().equals(Double.toString(rightAnswerNumber))) {
            scoreCounter++;
            answerText.setVisibility(View.VISIBLE);
            answerText.setText("Correct!");
        }
        else {
            answerText.setVisibility(View.VISIBLE);
            answerText.setText("Wrong!");
        }
        score.setText(scoreCounter+"/"+questionsCounter);
        assignNumbers();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        timer = findViewById(R.id.timer_textView);
        score = findViewById(R.id.score_textView);
        question = findViewById(R.id.question_texView);
        answerText = findViewById(R.id.answer_textView);
        goButton = findViewById(R.id.go_button);
        numberUL = findViewById(R.id.numberUL_button);
        numberUR = findViewById(R.id.numberUR_button);
        numberLL = findViewById(R.id.numberLL_button);
        numberLR = findViewById(R.id.numberLR_button);
        playAgain = findViewById(R.id.playAgain_button);

    }

    public void playAgain(View view) {

        score.setText("0/0");
        scoreCounter = 0;
        questionsCounter = 0;
        assignNumbers();

        playAgain.setVisibility(View.INVISIBLE);
        numberUL.setEnabled(true);
        numberUR.setEnabled(true);
        numberLL.setEnabled(true);
        numberLR.setEnabled(true);

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText((int) millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                numberUL.setEnabled(false);
                numberUR.setEnabled(false);
                numberLL.setEnabled(false);
                numberLR.setEnabled(false);
                answerText.setText("TIME'S UP!");
            }
        }.start();
    }
}
