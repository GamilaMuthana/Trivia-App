package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



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



    public void sportsGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("category", "sports");
        startActivity(intent);
    }

    public void artGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("category", "arts");
        startActivity(intent);
    }

    public void moviesGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("category", "movies");
        startActivity(intent);
    }

    public void musicGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("category", "music");
        startActivity(intent);
    }

}