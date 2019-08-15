package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_8;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

import java.util.UUID;

public class Actividad8 extends AppCompatActivity   implements   View.OnClickListener{



    ImageButton negro;
    ImageButton blanco;
    ImageButton rojo;
    ImageButton verde;
    ImageButton azul , guardar;
    Lienzo lienzo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad8);


        negro = (ImageButton)findViewById(R.id.colornegro);
        blanco = (ImageButton)findViewById(R.id.colorblanco);
        rojo = (ImageButton)findViewById(R.id.colorrojo);
        verde = (ImageButton)findViewById(R.id.colorverde);
        azul = (ImageButton)findViewById(R.id.colorazul);
        guardar = findViewById(R.id.guardar);



        negro.setOnClickListener(this);
        blanco.setOnClickListener(this);
        rojo.setOnClickListener(this);
        verde.setOnClickListener(this);
        azul.setOnClickListener(this);

        lienzo = (Lienzo)findViewById(R.id.lienzo);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(getApplicationContext());
                salvarDibujo.setTitle("Salvar dibujo");
                salvarDibujo.setMessage("¿Salvar Dibujo a la galeria?");
                salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        //Salvar dibujo
                        lienzo.setDrawingCacheEnabled(true);
                        //attempt to save
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        //Mensaje de todo correcto
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "¡Dibujo salvado en la galeria!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "¡Error! La imagen no ha podido ser salvada.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        lienzo.destroyDrawingCache();


                    }
                });
                salvarDibujo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                salvarDibujo.show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        String color = null;


        switch (v.getId()) {
            case R.id.colornegro:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorblanco:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorazul:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorverde:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorrojo:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;

                default:

                    break;


        }

    }
}

