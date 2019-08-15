package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Activity_7 extends AppCompatActivity {
    Button futbol, baloncesto, voleibol, avion, lleva, trompo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);


        futbol = (Button) findViewById(R.id.futbol);
        baloncesto = (Button) findViewById(R.id.baloncesto);
        voleibol = (Button) findViewById(R.id.voleibol);
        avion = (Button) findViewById(R.id.avion);
        lleva = (Button) findViewById(R.id.lleva);
        trompo = (Button) findViewById(R.id.trompo);


        futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","futbol");

                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
        baloncesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","baloncesto");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        voleibol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","voleibol");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        avion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","avion");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        lleva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","lleva");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        trompo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_7.this, Actividad7_1.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu","trompo");

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}
