package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Actividad10 extends AppCompatActivity {




    Button siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad10);

        siguiente=findViewById(R.id.btnsiguiente);


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Actividad10.this, Actividad10_1.class);

                startActivity(intent);


            }
        });


    }
}
