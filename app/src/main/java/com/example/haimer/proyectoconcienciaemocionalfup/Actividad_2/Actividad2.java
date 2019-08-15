package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.haimer.proyectoconcienciaemocionalfup.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Actividad2 extends AppCompatActivity {


    Button btnlunes,martes ,miercorles,jueves ,viernes  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);



        btnlunes = findViewById(R.id.btnlunes);
        martes=findViewById(R.id.btnmartes);
        miercorles= findViewById(R.id.btnmiercoles);
        jueves=findViewById(R.id.btnjueves);
        viernes=findViewById(R.id.btnviernes);


        btnlunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this,Escucharimagenes.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","lunes");

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


        martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this,Escucharimagenes.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","martes");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        miercorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this,Escucharimagenes.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","miercoles");

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this,Escucharimagenes.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","jueves");

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this,Escucharimagenes.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","viernes");

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });



    }
}
