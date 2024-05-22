package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        String score = getIntent().getStringExtra("correctAnswersCount");
        int scoreInt = Integer.parseInt(score);
        score = score + "/10";
        TextView tv = findViewById(R.id.scoreDisplay);
        tv.setText(score);
        String face;
        if(scoreInt < 4){
            face = "☹️";

        }
        else if (scoreInt < 7){
            face = "🫤";
        }
        else if (scoreInt < 10){
            face = "😃";
        }
        else {
            face = "🥳";
        }
        TextView tv2 = findViewById(R.id.smileyFace);
        tv2.setText(face);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void goHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToCredits(View v) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }
}