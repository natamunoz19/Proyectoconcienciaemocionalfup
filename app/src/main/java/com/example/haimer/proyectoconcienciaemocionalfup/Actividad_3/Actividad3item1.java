package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Actividad3item1 extends AppCompatActivity implements View.OnClickListener {



    private Button pause,adelante,atras,siguiente;
    private SeekBar barra;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;

    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3item1);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        pause=findViewById(R.id.pause);
        adelante=findViewById(R.id.adelante);
        atras=findViewById(R.id.atras);
        handler= new Handler();
        barra=findViewById(R.id.barra);
        mediaPlayer=MediaPlayer.create(this,R.raw.risa);
        pause.setOnClickListener(this);
       adelante.setOnClickListener(this);
        atras.setOnClickListener(this);
        siguiente = findViewById(R.id.btnsiguientenueva);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Actividad3item1.this,Actividad3item2.class);

                startActivity(inten);
                metodo();
                finish();


            }
        });


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                barra.setMax(mediaPlayer.getDuration());
                //  mediaPlayer.start();
                //changeSeekbar();

            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.r1) {

                    Toast.makeText(getApplicationContext(), "me gusta", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.r2) {

                    Toast.makeText(getApplicationContext(), "me enoja", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.r3) {

                    Toast.makeText(getApplicationContext(), "me divierte", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.r4) {

                    Toast.makeText(getApplicationContext(), "no me gusta", Toast.LENGTH_SHORT).show();
                }
            }


        });

        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        barra.setProgress(mediaPlayer.getCurrentPosition());
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

            case R.id.pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    pause.setText(">");
                }else {
                    mediaPlayer.start();
                    pause.setText("|| ");
                    changeSeekbar();
                }

                break;
            case R.id.adelante:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case R.id.atras:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
        }

    }

    public void metodo(){
        mediaPlayer.pause();                               //resets the media player

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            mediaPlayer.pause();
        }
        return super.onKeyDown(keyCode, event);
    }


}
