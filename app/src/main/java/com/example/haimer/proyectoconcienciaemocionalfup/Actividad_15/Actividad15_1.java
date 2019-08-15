package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class Actividad15_1 extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rojo,naranja,blanco,violeta,azul,verde;
    ImageView imagen15;

String nombreimagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad15_1);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            nombreimagen = bundle.getString("docu");

        }


          imagen15= findViewById(R.id.imagen15);




        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        rojo= findViewById(R.id.radioButton1);
        naranja= findViewById(R.id.radioButton2);
        blanco= findViewById(R.id.radioButton3);
        violeta= findViewById(R.id.radioButton4);
        azul= findViewById(R.id.radioButton5);
        verde= findViewById(R.id.radioButton6);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1) {

                    Toast.makeText(getApplicationContext(), "rojo", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton2) {

                    Toast.makeText(getApplicationContext(), "naranja", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton3) {

                    Toast.makeText(getApplicationContext(), "blanco", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton4) {

                    Toast.makeText(getApplicationContext(), "violeta", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton5) {

                    Toast.makeText(getApplicationContext(), "azul", Toast.LENGTH_SHORT).show();
                }
                else if (i == R.id.radioButton6) {

                    Toast.makeText(getApplicationContext(), "verde", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
