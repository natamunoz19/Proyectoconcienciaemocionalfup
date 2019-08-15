package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;

public class Actividad3 extends AppCompatActivity implements View.OnClickListener , Response.Listener<JSONObject>,Response.ErrorListener {



    private Button pause,adelante,atras,siguiente;
    private SeekBar barra;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;

    RadioGroup radioGroup;
    RadioButton r1,r2,r3,r4;

    RequestQueue requestQueue;

    ArrayList<Sesionestudiante> listausuario;
    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);


        ipconexion = new Ipconexion();

        conn = new DatabaseHelper(this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        pause=findViewById(R.id.pause);
        adelante=findViewById(R.id.adelante);
        atras=findViewById(R.id.atras);
        handler= new Handler();
        barra=findViewById(R.id.barra);
        mediaPlayer=MediaPlayer.create(this,R.raw.emocion);
        pause.setOnClickListener(this);
        adelante.setOnClickListener(this);
        atras.setOnClickListener(this);
        siguiente = findViewById(R.id.btnsiguientenueva);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (r1.isChecked()==true){
                          String ducues = consultarsesion();
                     String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                     guardaractiviada3( "http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividadestudiante3.php?documento="+ducues+"&sonido=piedra&estado=enoja&fecha="+fecha+"");

               }

                 else if(r2.isChecked()== true){

                     String ducues = consultarsesion();
                     String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                     guardaractiviada3( "http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividadestudiante3.php?documento="+ducues+"&sonido=piedra&estado=megusta&fecha="+fecha+"");


                 }else if(r3.isChecked()== true ){

                     String ducues = consultarsesion();
                     String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                     guardaractiviada3( "http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividadestudiante3.php?documento="+ducues+"&sonido=piedra&estado=triste&fecha="+fecha+"");

                 }else if (r4.isChecked()==true ) {
                     String ducues = consultarsesion();
                     String fecha = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                     guardaractiviada3("http://" + ipconexion.getIp() + "/dbandroid/WS/insertaractividadestudiante3.php?documento=" + ducues + "&sonido=piedra&estado=enoja&divierte=" + fecha + "");

                 }


                Intent inten = new Intent(Actividad3.this,Actividad3item1.class);

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


    private  void guardaractiviada3(String urll){


        ipconexion= new Ipconexion();

        String url =urll;

           //    "http://"+ipconexion.getIp()+"/dbandroid/WS/insetar.php?documento=";
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }



    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    private String consultarsesion( ) {

        SQLiteDatabase db = conn.getReadableDatabase();
        Sesionestudiante sesionestudiant = null;
        listausuario= new ArrayList<Sesionestudiante>();
        String doc ="";

        Cursor cursor =db.rawQuery("select * from iniciaronsesionestudiante  ORDER BY numero DESC LIMIT 1 ",null);
        while (cursor.moveToNext()){
            sesionestudiant= new Sesionestudiante();
            sesionestudiant.setPocision(cursor.getInt(0));
            sesionestudiant.setDocumento(cursor.getString(1));

            listausuario.add(sesionestudiant);
        }
        doc =listausuario.get(0).getDocumento();
        return  doc;
    }

}
