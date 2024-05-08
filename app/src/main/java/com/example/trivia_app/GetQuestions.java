package com.example.trivia_app;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GetQuestions {
    public ArrayList<TriviaQuestion> questions;

    public ArrayList<TriviaQuestion> getQuestions() {
        try {
            questions = new ArrayList<TriviaQuestion>();

            Scanner inFile = new Scanner(new FileReader("sports.txt"));

            while (inFile.hasNext()) {
                String question = inFile.nextLine();
                String choice1 = inFile.nextLine();
                String choice2 = inFile.nextLine();
                String choice3 = inFile.nextLine();
                String choice4 = inFile.nextLine();
                int answer = Integer.parseInt(inFile.nextLine());
                TriviaQuestion tq = new TriviaQuestion(question, choice1, choice2, choice3, choice4, answer);
                questions.add(tq);

            }


        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
        }

        return questions;
    }

    public static void main(String[] args) {
        GetQuestions gq = new GetQuestions();
        gq.questions.forEach(q -> {
            System.out.println(q.getQuestion());
            System.out.println(q.getOption(0));
            System.out.println(q.getOption(1));
            System.out.println(q.getOption(2));
            System.out.println(q.getOption(3));
            System.out.println(q.getCorrectAnswerIndex());
        });
    }


}
