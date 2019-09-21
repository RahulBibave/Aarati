package com.rahul.aarati;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Aarati extends AppCompatActivity implements View.OnClickListener {
    Button btnBack,btnFor,btnPlay;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Runnable runnable;
    Handler handler;
    TextView textView;
    private StringBuilder text = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        btnBack=findViewById(R.id.btnBack);
        btnFor=findViewById(R.id.btnFor);
        btnPlay=findViewById(R.id.btnPlay);
        seekBar=findViewById(R.id.seekbar);
        textView=findViewById(R.id.webview);

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("adhyashtak_stotra_sanskrit.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

            // TextView output= (TextView) findViewById(R.id.summtext);
            textView.setText((CharSequence) text);
            textView.setMovementMethod(new ScrollingMovementMethod());


        }


        handler= new Handler();
        mediaPlayer=MediaPlayer.create(this,R.raw.manglacharan);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                changeSeekbar();
            }
        });
       // mediaPlayer.setLooping(true);






        btnPlay.setOnClickListener(this);
        btnFor.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (b){
                    mediaPlayer.seekTo(i);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekbar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()){
            runnable=new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setText("||");
                }else {
                    mediaPlayer.start();

                    btnPlay.setText("|>");
                    changeSeekbar();
                }
                break;

            case R.id.btnFor:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;

            case R.id.btnBack:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }


}
