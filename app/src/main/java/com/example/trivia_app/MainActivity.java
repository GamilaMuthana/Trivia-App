package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    int selectedAnswerIndex = -1; //to keep track of the selected answer
    int correctAnswersCount = 0; //to keep a count of the questions answered correctly
    int currentQuestionIndex = 0; //the index of the question being displayed

    ArrayList<TriviaQuestion> questionsList = new ArrayList<TriviaQuestion>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);





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
        TextView tv = findViewById(R.id.questionDisplay);
        tv.setText(questionsList.get(currentQuestionIndex).getQuestion()); //tv.setText(questionsList.get(currentQuestionIndex).getQuestion());
        RadioButton rb = findViewById(R.id.radioButton0);
        rb.setText(questionsList.get(currentQuestionIndex).getOption(0));
        rb = findViewById(R.id.radioButton1);
        rb.setText(questionsList.get(currentQuestionIndex).getOption(1));
        rb = findViewById(R.id.radioButton2);
        rb.setText(questionsList.get(currentQuestionIndex).getOption(2));
        rb = findViewById(R.id.radioButton3);
        rb.setText(questionsList.get(currentQuestionIndex).getOption(3));

    }
    public void nextQuestion(View v){
        //TO DO: if selectedAnswerIndex is the correct answer: correctAnswersCount++;
        if (selectedAnswerIndex == questionsList.get(currentQuestionIndex).getCorrectAnswerIndex()){
            correctAnswersCount++;
        }
        currentQuestionIndex++;

        //TO DO:
        //IF currentQuestionIndex < size of the ArrayListQuestions
        if (currentQuestionIndex < questionsList.size()){

            displayQuestion();

        }
        //ELSE
        //start activity that displays results; send the value of correctAnswersCount
    }

    public void sportsGame(View v){

        try {
            questionsList = new ArrayList<TriviaQuestion>();

            Scanner inFile = new Scanner(new FileReader("sports.txt"));

            while (inFile.hasNext()) {
                String question = inFile.nextLine();
                String choice1 = inFile.nextLine();
                String choice2 = inFile.nextLine();
                String choice3 = inFile.nextLine();
                String choice4 = inFile.nextLine();
                int answer = Integer.parseInt(inFile.nextLine());
                TriviaQuestion tq = new TriviaQuestion(question, choice1, choice2, choice3, choice4, answer);
                questionsList.add(tq);

            }


        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
        }


        Intent intent = new Intent(this, Game.class);
        intent.putExtra("question", questionsList.get(currentQuestionIndex).getQuestion());
        intent.putExtra("choice0", questionsList.get(currentQuestionIndex).getOption(0));
        intent.putExtra("choice1", questionsList.get(currentQuestionIndex).getOption(1));
        intent.putExtra("choice2", questionsList.get(currentQuestionIndex).getOption(2));
        intent.putExtra("choice3", questionsList.get(currentQuestionIndex).getOption(3));
        startActivity(intent);
    }

}