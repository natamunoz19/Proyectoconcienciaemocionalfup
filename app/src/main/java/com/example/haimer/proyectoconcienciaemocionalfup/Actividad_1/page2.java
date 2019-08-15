package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haimer.proyectoconcienciaemocionalfup.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class page2 extends android.support.v4.app.Fragment {

    TextView titulo;
    ImageView foto;

    public page2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the l00000000000ayout for this fragment
        View view = inflater.inflate(R.layout.fragment_page2, container, false);
        titulo = (TextView) view.findViewById(R.id.texto);
        foto = (ImageView) view.findViewById(R.id.imagen);
        Bundle bundle = getArguments();
        String mensaje = Integer.toString(bundle.getInt("count"));
        if(bundle.getInt("count") == 1){
            titulo.setText("PENSATIVO");
            foto.setImageResource(R.drawable.imagen1);
        } else if(bundle.getInt("count") == 2){
            titulo.setText("FELIZ");
            foto.setImageResource(R.drawable.imagen2);
        }else if(bundle.getInt("count") == 3){
            titulo.setText("CONFIADO");
            foto.setImageResource(R.drawable.imagen3);
        }
        return view;
    }

}

