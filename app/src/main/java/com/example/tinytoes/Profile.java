package com.example.tinytoes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = findViewById(R.id.Profile_Pic);
    }
    public void UploadButtonPressed(View v){

        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent,10);
    }
    public void CameraButtonPressed(View v){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if(requestCode == 10){
                imageView.setImageURI(data.getData());
            }
            if(requestCode == 100){
                Bitmap img = (Bitmap) (data.getExtras().get("data"));
                imageView.setImageBitmap(img);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}