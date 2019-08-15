package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicioapp extends AppCompatActivity {


    Button profesor ,estudiante;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicioapp);


        profesor = findViewById(R.id.inicioprofesor);
        estudiante= findViewById(R.id.inicoestudiante);


        profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicioapp.this,Loginprofesor.class);
                startActivity(intent);
                finish();
            }
        });



        estudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicioapp.this,Loginestudiante.class);
                startActivity(intent);
            }
        });



    }
}
