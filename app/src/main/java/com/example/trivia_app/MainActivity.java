package com.example.trivia_app;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int selectedAnswerIndex = -1; //to keep track of the selected answer
    int correctAnswersCount = 0; //to keep a count of the questions answered correctly
    int currentQuestionIndex = 0; //the index of the question being displayed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ArrayList<TriviaQuestion> questionsList = new ArrayList<>();
        
       


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void radioButtonClick(View view){
        if (view.getId() == R.id.radioButton0) selectedAnswerIndex = 0;
        if (view.getId() == R.id.radioButton1) selectedAnswerIndex = 1;
        if (view.getId() == R.id.radioButton2) selectedAnswerIndex = 2;
        if (view.getId() == R.id.radioButton3) selectedAnswerIndex = 3;
    }

    public void displayQuestion(){
        TextView tv = findViewById(R.id.questionDisplay); tv.setText("QUESTION " + currentQuestionIndex + " TEXT GOES HERE." ); //tv.setText(questionsList.get(currentQuestionIndex).getQuestion());
        RadioButton rb = findViewById(R.id.radioButton0); rb.setText("Option 0, question number " + currentQuestionIndex);
        rb = findViewById(R.id.radioButton1);
        rb.setText("Option 1, question number " + currentQuestionIndex);
        rb = findViewById(R.id.radioButton2);
        rb.setText("Option 2, question number " + currentQuestionIndex);
        rb = findViewById(R.id.radioButton3);
        rb.setText("Option 3, question number " + currentQuestionIndex);

    }
    public void nextQuestion(View v){
//TO DO: if selectedAnswerIndex is the correct answer: correctAnswersCount++;
currentQuestionIndex++;
//TO DO:
//IF currentQuestionIndex < size of the ArrayListQuestions
        displayQuestion();
//ELSE
//start activity that displays results; send the value of correctAnswersCount
    }
}