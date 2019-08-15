package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

public class imagenes extends FragmentActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        vp = (ViewPager) findViewById(R.id.vista);
        Adaptador adaptador = new Adaptador(getSupportFragmentManager());
        vp.setAdapter(adaptador);
    }
}
