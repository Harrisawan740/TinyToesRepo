package com.example.tinytoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {


    EditText username,email,password;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user);

        username = findViewById(R.id.RegisterName);
        email = findViewById(R.id.RegisterEmail);
        password = findViewById(R.id.RegisterPassword);
    }
    public void MoveToLoginPage(View v){

        Intent i = new Intent(RegisterUser.this, LogIn.class);
        startActivity(i);
    }
    public void registerUser(View v){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        String u_name = username.getText().toString();
        String eml = email.getText().toString();
        String pwd = password.getText().toString();

//        Toast.makeText(RegisterUser.this, u_name, Toast.LENGTH_SHORT).show();

        HelperClass helper = new HelperClass(u_name,eml,pwd);

        reference.child(u_name).setValue(helper);

        Toast.makeText(RegisterUser.this, "Successfully Registered User", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(RegisterUser.this, MainMenu.class);
        startActivity(i);
    }
}