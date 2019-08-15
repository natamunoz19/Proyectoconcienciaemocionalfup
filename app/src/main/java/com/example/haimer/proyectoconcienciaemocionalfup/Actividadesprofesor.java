package com.example.haimer.proyectoconcienciaemocionalfup;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1.*;

public class Actividadesprofesor extends AppCompatActivity {


    Button actividadprofesor1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividadesprofesor);
         actividadprofesor1= findViewById(R.id.actividadprofesor1);


         actividadprofesor1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Actividadesprofesor.this,iniciarmod1.class);
                 startActivity(intent);

             }
         });

    }
}
