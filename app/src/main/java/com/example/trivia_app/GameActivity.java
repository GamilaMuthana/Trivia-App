package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {

    int selectedAnswerIndex = -1; //to keep track of the selected answer
    int correctAnswersCount = 0; //to keep a count of the questions answered correctly
    int currentQuestionIndex = 0; //the index of the question being displayed

    ArrayList<TriviaQuestion> questionsList = new ArrayList<>();
    ArrayList<TriviaQuestion> allQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

//        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
        String category = getIntent().getStringExtra("category");

        Scanner inFile = null;
        assert category != null;
        if (category.equals("sports")) {
            inFile = new Scanner(getResources().openRawResource(R.raw.sports));
        } else if (category.equals("movies")) {
            inFile = new Scanner(getResources().openRawResource(R.raw.entertainment));
        } /*else if (category.equals("arts")) {
            Scanner inFile = new Scanner(getResources().openRawResource(R.raw.arts));
        } else if (category.equals("music")) {
            Scanner inFile = new Scanner(getResources().openRawResource(R.raw.music));
        }*/
        assert inFile != null;
        while (inFile.hasNext()) {
            String question = inFile.nextLine();

            String choice1 = inFile.nextLine();
            String choice2 = inFile.nextLine();
            String choice3 = inFile.nextLine();
            String choice4 = inFile.nextLine();
            int answer = Integer.parseInt(inFile.nextLine());
            TriviaQuestion tq = new TriviaQuestion(question, choice1, choice2, choice3, choice4, answer);
            allQuestions.add(tq);
        }

        HashSet<Integer> randomNumbers = new HashSet<>();
        while (randomNumbers.size() < 10) {
            Random random = new Random();
            randomNumbers.add(random.nextInt(allQuestions.size()));
        }

        for (int i : randomNumbers) {
            questionsList.add(allQuestions.get(i));
        }

        TextView tv = findViewById(R.id.questionDisplay);
        RadioButton button0 = findViewById(R.id.radioButton0);
        RadioButton button1 = findViewById(R.id.radioButton1);
        RadioButton button2 = findViewById(R.id.radioButton2);
        RadioButton button3 = findViewById(R.id.radioButton3);
        tv.setText(questionsList.get(currentQuestionIndex).getQuestion());
        button0.setText(questionsList.get(currentQuestionIndex).getOption(0));
        button1.setText(questionsList.get(currentQuestionIndex).getOption(1));
        button2.setText(questionsList.get(currentQuestionIndex).getOption(2));
        button3.setText(questionsList.get(currentQuestionIndex).getOption(3));

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
            // clear radio button selection
            selectedAnswerIndex = -1;
            RadioGroup rg = findViewById(R.id.radioGroup);
            rg.clearCheck();
            //display the next question
            displayQuestion();
        }
        //ELSE
        //start activity that displays results; send the value of correctAnswersCount
         else{
             Intent intent = new Intent(this, ResultActivity.class);
             intent.putExtra("correctAnswersCount", String.valueOf(correctAnswersCount));
             startActivity(intent);
         }
    }

    public void previousQuestion(View v){
        if (currentQuestionIndex > 0){
            currentQuestionIndex--;
            selectedAnswerIndex = -1;
            RadioGroup rg = findViewById(R.id.radioGroup);
            rg.clearCheck();
            displayQuestion();
        }
    }
}