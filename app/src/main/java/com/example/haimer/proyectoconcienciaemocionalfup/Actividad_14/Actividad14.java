package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_14;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;


import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_10.Actividad10;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import java.util.Arrays;
import java.util.Collections;


public class Actividad14 extends AppCompatActivity {

    private    final int[] CARTA_RESOURSES= new int[]{
            R.drawable.imagen11,
            R.drawable.imagen22,
            R.drawable.imagen33,
            R.drawable.imagen44,
            R.drawable.imagen55,
            R.drawable.imagen66,
            R.drawable.imagen77,
            R.drawable.imagen88,
            R.drawable.imagen99,
            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3,
            R.drawable.imagen_disco

    };
    private  final Handler handler=new Handler();
    private  Carta[] cartas;
    private  boolean touchActivo=true;
    private Carta visible=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad14);

        final TableLayout tabla=new TableLayout(this);
        final int tam=4;
        cartas=crearCeldas(tam*tam);
        Collections.shuffle(Arrays.asList(cartas));
        for (int y=0;y<tam;y++){

            final TableRow fila=new TableRow(this);
            for(int x=0;x<tam;x++){

                fila.addView(cartas[(y*tam)+x].boton);
            }
            tabla.addView(fila);

        }
        setContentView(tabla);


    }



    private Carta[] crearCeldas(final  int cont) {
        final  Carta[] array= new Carta[cont];
        for (int i=0; i<array.length; i++){
            array[i]=new Carta(CARTA_RESOURSES[i/2]);
        }



        return array;
    }

    private  class  Carta implements View.OnClickListener {

        private final ImageButton boton;
        private  final int imagen;
        private  boolean caraVisible=false;
        Carta (final  int imagen){

            this.imagen=imagen;
            this.boton=new ImageButton(Actividad14.this);
            this.boton.setLayoutParams(new TableRow.LayoutParams(255,350));
            this.boton.setScaleType(ImageView.ScaleType.FIT_XY);
            this.boton.setImageResource(R.drawable.linea);
            this.boton.setOnClickListener(this);

        }
        void setCaraVisible(final  boolean caraVisible){

            this.caraVisible=caraVisible;
            boton.setImageResource(caraVisible? imagen: R.drawable.linea);
        }



        @Override
        public void onClick(View v) {

            if (!caraVisible&&touchActivo);
            onCartaDescubierta(this);
        }

        public void onCartaDescubierta(final Carta celda) {

            if(visible==null){
                visible=celda;
                visible.setCaraVisible(true);

            }
            else if (visible.imagen==celda.imagen){
                celda.setCaraVisible(true);
                celda.boton.setEnabled(false);
                visible.boton.setEnabled(false);
                visible=null;
            }else {

                celda.setCaraVisible(true);
                touchActivo=false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        celda.setCaraVisible(false);
                        visible.setCaraVisible(false);
                        visible=null;
                        touchActivo=true;

                    }
                },1000);
            }
        }
    }
}
