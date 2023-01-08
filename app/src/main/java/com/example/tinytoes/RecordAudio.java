package com.example.tinytoes;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.tinytoes.ml.TfCryClassifier;
import com.jlibrosa.audio.wavFile.WavFile;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.media.AudioFormat;
import android.media.AudioRecord;

import org.apache.commons.math3.geometry.partitioning.Transform;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.mfcc.MFCC;




public class RecordAudio extends AppCompatActivity {



    private static int MICROPHONE_PERMISSION_CODE = 200;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    TextView resultbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_record_audio);

        if(isMicrophoneAvailable()){
            getMicroPhonePermission();
        }

        resultbox = findViewById(R.id.ResultBox);



    }
    public void RecordbuttonPressed(View v){
       try {
           mediaRecorder = new MediaRecorder();
           mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
           mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
           mediaRecorder.setOutputFile(getRecordingPath());
           mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
           mediaRecorder.prepare();
           mediaRecorder.start();
           Toast.makeText(this, "Recording Has Started", Toast.LENGTH_SHORT).show();
       }
       catch (Exception e){
           e.printStackTrace();
       }

    }
    public void StopbuttonPressed(View v){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        Toast.makeText(this, "Recording Has Stopped", Toast.LENGTH_SHORT).show();
    }
    public void PlaybuttonPressed(View v){

        try {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Playing the Recording", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void PredictButtonPressed(View v){

        try {
            Toast.makeText(this, "Waiting for the Prediction Result", Toast.LENGTH_SHORT).show();

//            int mNumFrames = 0,mSampleRate = 0;

//            File sourceFile = new File("D:/Related to FYP/Dataset/donateacry-corpus/donateacry_corpus_cleaned_and_updated_data/hungry/0a983cd2-0078-4698-a048-99ac01eb167a-1433917038889-1.7-f-04-hu.wav");
//            MFCC mfccConvert = new MFCC(mNumFrames,mSampleRate);

//            Transform t = new Transform(new FastWaveletTransform(new Daubechies4()));
//
//            WaveFile waveFile = new WaveFile("path/to/your/wav/file.wav");
//            double[] audioData = waveFile.getSampleData();





            TfCryClassifier model = TfCryClassifier.newInstance(RecordAudio.this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 40}, DataType.FLOAT32);
//            inputFeature0.loadBuffer(byteBuffer);


            // Runs model inference and gets result.
            TfCryClassifier.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


            resultbox.setText("Hungry");

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
//            // TODO Handle the exception
            e.printStackTrace();
        }
    }
    private boolean isMicrophoneAvailable(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else{
            return false;
        }
    }
    private void getMicroPhonePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                ==PackageManager.PERMISSION_DENIED ){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE);
        }
    }
    private String getRecordingPath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "RecordingFile"+ ".mp3");
        return file.getPath();

    }

}
