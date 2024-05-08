package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        String choice0 = intent.getStringExtra("choice0");
        String choice1 = intent.getStringExtra("choice1");
        String choice2 = intent.getStringExtra("choice2");
        String choice3 = intent.getStringExtra("choice3");
        TextView tv = findViewById(R.id.questionDisplay);
        RadioButton button0 = findViewById(R.id.radioButton0);
        RadioButton button1 = findViewById(R.id.radioButton1);
        RadioButton button2 = findViewById(R.id.radioButton2);
        RadioButton button3 = findViewById(R.id.radioButton3);
        tv.setText(question);
        button0.setText(choice0);
        button1.setText(choice1);
        button2.setText(choice2);
        button3.setText(choice3);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}