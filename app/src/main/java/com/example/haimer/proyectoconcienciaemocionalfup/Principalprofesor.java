package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Principalprofesor extends AppCompatActivity {

    Button btnAcceso ,btnresultados ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalprofesor);


      btnAcceso = (Button) findViewById(R.id.btnactividadprofesor);
      btnresultados = findViewById(R.id.btnresultadoactividadesestudiantes);

      btnAcceso.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Principalprofesor.this, Actividadesprofesor.class);
              startActivity(intent);
          }
      });

      btnresultados.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Principalprofesor.this, Resultadoactividades.class);
              startActivity(intent);
          }
      });


    }


    @Override public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_activity,mimenu);
         return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
         int id =opcion_menu.getItemId();
         if(id==R.id.cerrarsesion){
             Loginprofesor.cambiarestado(Principalprofesor.this,false);
             Intent intent = new Intent(Principalprofesor.this,Loginprofesor.class);
             startActivity(intent);
              finish();
         }
       return   super.onOptionsItemSelected(opcion_menu);
    }




}
