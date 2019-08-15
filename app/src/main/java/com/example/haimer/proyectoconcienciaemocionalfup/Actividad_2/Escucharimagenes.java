package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class Escucharimagenes extends AppCompatActivity {



    Button btnalegria,btntriste,btnira ,btnsiguiente;
    MediaPlayer npa ,npt,npi;
    String dia="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucharimagen);




        btnalegria = findViewById(R.id.btnalegria);
        btntriste= findViewById(R.id.btntriste);
        btnira=findViewById(R.id.btnira);
        btnsiguiente=findViewById(R.id.btnsiguiente);

        npa=MediaPlayer.create(this,R.raw.alegria);
        npt=MediaPlayer.create(this,R.raw.triste);
        npi=MediaPlayer.create(this,R.raw.ira);


        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
             dia = bundle.getString("docu");

                   }




        btnalegria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                npa.start();


            }
        });


        btntriste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                npt.start();



            }
        });


        btnira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                npi.start();

            }
        });


        btnsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Escucharimagenes.this,Seleccionarimagen.class);
                Bundle bundle = new Bundle();
                bundle.putString("docu",dia);

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


    }
}
