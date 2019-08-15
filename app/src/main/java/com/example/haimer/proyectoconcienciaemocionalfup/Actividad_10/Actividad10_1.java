package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Actividad10_1 extends AppCompatActivity {


    Button finalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad10_1);



        finalizar=findViewById(R.id.btnsiguiente2);


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Actividad10_1.this, Actividad10_2.class);

                startActivity(intent);

            }
});
    }
}