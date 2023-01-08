package com.example.tinytoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }
    public void SubmitFeedbackButton(View v){

        Toast.makeText(this, "Thank You for Your Feedback", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Feedback.this, MainMenu.class);
        startActivity(i);

    }
}