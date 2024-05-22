package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
//    int correctAnswersCount = 0; //to keep a count of the questions answered correctly
    int currentQuestionIndex = 0; //the index of the question being displayed

    ArrayList<TriviaQuestion> questionsList = new ArrayList<>();
    ArrayList<TriviaQuestion> allQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        selectedAnswerIndex = -1; //to keep track of the selected answer
//        correctAnswersCount = 0; //to keep a count of the questions answered correctly
        currentQuestionIndex = 0; //the index of the question being displayed

        questionsList = new ArrayList<>();
        allQuestions = new ArrayList<>();

        String category = getIntent().getStringExtra("category");

        Scanner inFile = null;
        assert category != null;
        switch (category) {
            case "sports":
                inFile = new Scanner(getResources().openRawResource(R.raw.sports));
                break;
            case "movies":
                inFile = new Scanner(getResources().openRawResource(R.raw.entertainment));
                break;
            case "arts":
                inFile = new Scanner(getResources().openRawResource(R.raw.art));
                break;
            case "music":
                inFile = new Scanner(getResources().openRawResource(R.raw.music));
                break;
        }
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

        displayQuestion();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void radioButtonClick(View view){
        if (view.getId() == R.id.radioButton0) questionsList.get(currentQuestionIndex).setSavedAnswerIndex(0);
        if (view.getId() == R.id.radioButton1) questionsList.get(currentQuestionIndex).setSavedAnswerIndex(1);
        if (view.getId() == R.id.radioButton2) questionsList.get(currentQuestionIndex).setSavedAnswerIndex(2);
        if (view.getId() == R.id.radioButton3) questionsList.get(currentQuestionIndex).setSavedAnswerIndex(3);
        selectedAnswerIndex = questionsList.get(currentQuestionIndex).getSavedAnswerIndex();
    }

    public void displayQuestion(){
        Toast.makeText(getApplicationContext(), String.valueOf(questionsList.get(currentQuestionIndex).getCorrectAnswerIndex()), Toast.LENGTH_SHORT).show();

        TextView tv = findViewById(R.id.questionDisplay);
        tv.setText(questionsList.get(currentQuestionIndex).getQuestion());

        TextView tv2 = findViewById(R.id.questionNumber);
        String phrase = "Question " + (currentQuestionIndex + 1) + " of 10";
        tv2.setText(phrase);

        RadioButton rb0 = findViewById(R.id.radioButton0);
        RadioButton rb1 = findViewById(R.id.radioButton1);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        RadioButton rb3 = findViewById(R.id.radioButton3);

        rb0.setText(questionsList.get(currentQuestionIndex).getOption(0));
        rb1.setText(questionsList.get(currentQuestionIndex).getOption(1));
        rb2.setText(questionsList.get(currentQuestionIndex).getOption(2));
        rb3.setText(questionsList.get(currentQuestionIndex).getOption(3));

        RadioGroup rg = findViewById(R.id.radioGroup);
        rg.clearCheck();

        selectedAnswerIndex = questionsList.get(currentQuestionIndex).getSavedAnswerIndex();

        if (selectedAnswerIndex != -1) {
            switch (selectedAnswerIndex) {
                case 0:
                    rb0.setChecked(true);
                    break;
                case 1:
                    rb1.setChecked(true);
                    break;
                case 2:
                    rb2.setChecked(true);
                    break;
                case 3:
                    rb3.setChecked(true);
                    break;
            }
        }
    }

    private int calculateCorrectAnswers() {
        int count = 0;
        for (TriviaQuestion tq : questionsList){
            if (tq.getCorrectAnswerIndex() == tq.getSavedAnswerIndex()){
                count++;
            }
        }
        return count;
    }

    public void nextQuestion(View v){
        if (selectedAnswerIndex == -1) {
            return;
        }

        currentQuestionIndex++;

        //TO DO:
        //IF currentQuestionIndex < size of the ArrayListQuestions
        if (currentQuestionIndex < questionsList.size()){
            displayQuestion();
        } else{
             int correctAnswersCount = calculateCorrectAnswers();
             Intent intent = new Intent(this, ResultActivity.class);
             intent.putExtra("correctAnswersCount", String.valueOf(correctAnswersCount));
             startActivity(intent);
         }
    }

    public void previousQuestion(View v){
        if (currentQuestionIndex > 0){
            currentQuestionIndex--;
            displayQuestion();
        }
    }
}