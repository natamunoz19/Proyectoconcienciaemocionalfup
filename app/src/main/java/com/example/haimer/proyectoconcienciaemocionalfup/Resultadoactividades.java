package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_6.Resultadoactividad6;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_13.Resultadoactividad13;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.*;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_4.*;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_3.*;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_7.Resultadoactividad7;


public class Resultadoactividades extends AppCompatActivity {

     Button resactividad2 , resactividad4,resactividad3,resactividad6,resactividad7,resactividad13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoactividades);


        resactividad2 = findViewById(R.id.resactividad2);
        resactividad3= findViewById(R.id.resactividad3);

        resactividad4= findViewById(R.id.resactividad4);

        resactividad6= findViewById(R.id.resactividad6);


        resactividad7= findViewById(R.id.resactividad7);
        resactividad13= findViewById(R.id.resactividad13);


        resactividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad2.class);
              startActivity(intent);
            }
        });

        resactividad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad3.class);
                startActivity(intent);
            }
        });

        resactividad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad4.class);
                startActivity(intent);
            }
        });
        resactividad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad6.class);
                startActivity(intent);
            }
        });
        resactividad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad7.class);
                startActivity(intent);
            }
        });

        resactividad13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resultadoactividades.this,Resultadoactividad13.class);
                startActivity(intent);
            }
        });
    }
}
