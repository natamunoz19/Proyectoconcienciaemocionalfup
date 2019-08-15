package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Actividad15 extends AppCompatActivity {


    ImageButton alegria, ira,tristeza, preocupacion, deleite,odio,satisfacion,contento,gozo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad15);


        alegria = (ImageButton) findViewById(R.id.imageButton1);
        ira = (ImageButton) findViewById(R.id.imageButton2);
        tristeza = (ImageButton) findViewById(R.id.imageButton3);
        preocupacion = (ImageButton) findViewById(R.id.imageButton4);

        deleite = (ImageButton) findViewById(R.id.imageButton5);
        odio = (ImageButton) findViewById(R.id.imageButton6);
        satisfacion= (ImageButton) findViewById(R.id.imageButton7);
        contento= (ImageButton) findViewById(R.id.imageButton8);
        gozo= (ImageButton) findViewById(R.id.imageButton9);

        alegria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);

                Bundle bundle = new Bundle();
                bundle.putString("docu","imageButton1");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        ira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });

        tristeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        preocupacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        deleite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        odio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        satisfacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        contento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
        gozo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad15.this, Actividad15_1.class);
                startActivity(intent);

            }
        });
    }
}
