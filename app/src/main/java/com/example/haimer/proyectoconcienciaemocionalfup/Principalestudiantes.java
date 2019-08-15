package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_11.Iniciar11;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_12.Iniciar12;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_15.Actividad15;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1.iniciarmod1;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_14.Actividad14;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_4.Actividad4;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Actividad2;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_3.Actividad3;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_6.Actividad_6;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_5.Iniciar5;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_8.Actividad8;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_10.Actividad10;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_7.Activity_7;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_13.MainActivity;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_9.Iniciar9;


public class Principalestudiantes extends AppCompatActivity {

    Button actividad1, actividad2, actividad3,actividad4,actividad5,actividad6 ,actividad7,actividad8,actividad9,actividad10,actividad11,actividad12,actividad13,actividad14,actividad15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalestudiantes);

        actividad1 = findViewById(R.id.btnactividad1);
        actividad2 = findViewById(R.id.btnactividad2);
        actividad3=findViewById(R.id.btn_actividad3);
        actividad4=findViewById(R.id.btnactividad4);
        actividad5=findViewById(R.id.btnactividad5);
       actividad6= findViewById(R.id.btnactividad6);
       actividad8=findViewById(R.id.btnactividad8);
        actividad9=findViewById(R.id.btnactividad9);
       actividad7 = findViewById(R.id.btnactividad7);
       actividad10=findViewById(R.id.btnactividad10);
        actividad11=findViewById(R.id.btnactividad11);
        actividad12=findViewById(R.id.btnactividad12);
        actividad13=findViewById(R.id.btnactividad13);
        actividad14=findViewById(R.id.btnactividad14);
        actividad15=findViewById(R.id.btnactividad15);




        actividad12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Iniciar12.class);
                startActivity(intent);
            }
        });


        actividad11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Iniciar11.class);
                startActivity(intent);
            }
        });



        actividad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,iniciarmod1.class);
                startActivity(intent);
            }
        });


        actividad15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad15.class);
                startActivity(intent);
            }
        });

        actividad14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad14.class);
                startActivity(intent);
            }
        });
        actividad5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Iniciar5.class);
                startActivity(intent);
            }
        });


        actividad13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,MainActivity.class);
                startActivity(intent);
            }
        });


        actividad10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad10.class);
                startActivity(intent);
            }
        });

        actividad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Activity_7.class);
                startActivity(intent);
            }
        });

       actividad9.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Principalestudiantes.this,Iniciar9.class);
               startActivity(intent);
           }
       });


        actividad8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad8.class);
                startActivity(intent);

            }
        });


        actividad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad_6.class);
                startActivity(intent);
            }
        });


        actividad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad4.class);
                startActivity(intent);
            }
        });

        actividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principalestudiantes.this,Actividad2.class);
                startActivity(intent);

            }
        });

        actividad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Principalestudiantes.this,Actividad3.class);
                startActivity(intent);

            }
        });

    }


    @Override public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_activity_estu,mimenu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id =opcion_menu.getItemId();
        if(id==R.id.cerrarsesionestu){
            Loginestudiante.cambiarestado(Principalestudiantes.this,false);
            Intent intent = new Intent(Principalestudiantes.this,Loginprofesor.class);
            startActivity(intent);
            finish();
        }
        return   super.onOptionsItemSelected(opcion_menu);
    }

}
