package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page extends Fragment {
    TextView titulo;
    ImageView foto;

    public Page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_page, container, false);

        titulo = (TextView) view.findViewById(R.id.texto);
        foto = (ImageView) view.findViewById(R.id.imagen);
        Bundle bundle = getArguments();
        String mensaje = Integer.toString(bundle.getInt("count"));
        if(bundle.getInt("count") == 1){
            titulo.setText("PENSATIVO");
            foto.setImageResource(R.drawable.imagen1c);
        } else if(bundle.getInt("count") == 2){
            titulo.setText("FELIZ");
            foto.setImageResource(R.drawable.imagen2c);
        }else if(bundle.getInt("count") == 3){
            titulo.setText("CONFIADO");
            foto.setImageResource(R.drawable.imagen3c);
        }
        return view;
    }
    }





