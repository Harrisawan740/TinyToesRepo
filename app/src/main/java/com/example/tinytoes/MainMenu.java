package com.example.tinytoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void ClassifyAudioButtonPressed(View v){

        Intent i = new Intent(MainMenu.this, RecordAudio.class);
        startActivity(i);
    }
    public void ViewStatsButtonPressed(View v){

        Intent i = new Intent(MainMenu.this, ViewStats.class);
        startActivity(i);
    }
    public void GiveFeedbackButtonPressed(View v){

        Intent i = new Intent(MainMenu.this, Feedback.class);
        startActivity(i);
    }
    public void LogoutButtonPressed(View v){

        Intent i = new Intent(MainMenu.this, LogIn.class);
        startActivity(i);
    }
    public void ProfileButtonPressed(View v){

        Intent i = new Intent(MainMenu.this, Profile.class);
        startActivity(i);
    }
    public void MicbuttonPressed(View v){

        Toast.makeText(this, "Recording Has Stopped", Toast.LENGTH_SHORT).show();
    }
}