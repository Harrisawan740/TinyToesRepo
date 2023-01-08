package com.example.tinytoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LogIn extends AppCompatActivity {


    EditText log_username,log_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_log_in);

        log_username = findViewById(R.id.LoginEmail);

        log_password = findViewById(R.id.LoginPassword);
    }
    public void ForgotPasswordButtonClicked(View v){
        if(ValidateUser()){

            String username = log_username.getText().toString().trim();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUser = reference.orderByChild("username").equalTo(username);
//            Toast.makeText(LogIn.this, "--Here--", Toast.LENGTH_SHORT).show();
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()){
//                        Toast.makeText(LogIn.this, "--Here--", Toast.LENGTH_SHORT).show();
//                        log_username.setError(null);
                        String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                        Toast.makeText(LogIn.this, passwordFromDB, Toast.LENGTH_SHORT).show();
                        log_password.setText(passwordFromDB);
                    }
                    else {
                        log_username.setError("User Doesn't Exists");
                        log_username.requestFocus();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }
    public void LoginButtonPressed(View v){

        if(!ValidatePassword() | !ValidateUser()){

        }
        else {
//            Toast.makeText(LogIn.this, "------Here-----", Toast.LENGTH_SHORT).show();
            CheckUser();
        }
    }
    public boolean ValidateUser(){
        String val = log_username.getText().toString();
        if (val.isEmpty()){
            log_username.setError("Empty Field");
            return false;
        }else {
            log_username.setError(null);
            return true;
        }

    }
    public boolean ValidatePassword(){
        String val = log_password.getText().toString();
        if (val.isEmpty()){
            log_password.setError("Empty Field");
            return false;
        }else {
            log_password.setError(null);
            return true;
        }

    }
    public void CheckUser(){
        String username = log_username.getText().toString().trim();
        String password = log_password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(username);

//        Toast.makeText(LogIn.this, "---Here--", Toast.LENGTH_SHORT).show();

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//

                if(snapshot.exists()){
//                    Toast.makeText(LogIn.this, "---Here--", Toast.LENGTH_SHORT).show();
                    log_username.setError(null);
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    if(Objects.equals(passwordFromDB,password)){
                        log_username.setError(null);
                        Intent i = new Intent(LogIn.this, MainMenu.class);
                        startActivity(i);
                    }
                    else {
                        log_password.setError("Invalid Password");
                        log_password.requestFocus();
                    }
                }
                else {
                    log_username.setError("User Doesn't Exists");
                    log_username.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}