package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

import java.util.ArrayList;

/**
 * Created by Personal on 04/10/2018.
 */

public class Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<listaMusica> arrayList;
    private MediaPlayer mediaPlayer;
    private boolean flag = true;

    public Adapter(Context context, int layout, ArrayList<listaMusica> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtNombre, txtCantante;
        ImageView bPlay, bStop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtNombre = (TextView) view.findViewById(R.id.nombreMusica);
            viewHolder.txtCantante = (TextView) view.findViewById(R.id.cantante);
            viewHolder.bPlay = (ImageView) view.findViewById(R.id.botonPlay);
            viewHolder.bStop = (ImageView) view.findViewById(R.id.botonStop);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final listaMusica lm = arrayList.get(i);
        viewHolder.txtNombre.setText(lm.getNombre());
        viewHolder.txtCantante.setText(lm.getCantante());

        //TOCAR MUSICA
        viewHolder.bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    mediaPlayer = MediaPlayer.create(context, lm.getCancion());
                    flag = false;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.bPlay.setImageResource(R.drawable.boton_play);
                }else{
                    mediaPlayer.start();
                    viewHolder.bPlay.setImageResource(R.drawable.boton_pausa);
                }
            }
        });

        //DETENER MUSICA
        viewHolder.bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;
                }
                viewHolder.bPlay.setImageResource(R.drawable.boton_play);

            }
        });

        return view;
    }
}
