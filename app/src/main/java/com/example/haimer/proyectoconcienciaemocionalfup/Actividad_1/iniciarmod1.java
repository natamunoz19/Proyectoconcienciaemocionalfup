package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.haimer.proyectoconcienciaemocionalfup.R;

import java.util.ArrayList;

public class iniciarmod1 extends AppCompatActivity {

    MediaPlayer mp;
    int pausa;
    Button cambiar;
    private ArrayList<listaMusica> arrayList;
    private Adapter adapter;
    private ListView lv;
    String actividad = "";
    String codigo = "";

    String[] values = new String[]{"Musica1","Musica2","Musica3","Musica4","Musica5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarmod1);

     /*   lv = (ListView) findViewById(R.id.lista);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, values);
        lv.setAdapter(adapter);
       */

        lv = (ListView) findViewById(R.id.vista);
        arrayList = new ArrayList<>();
        arrayList.add(new listaMusica("Cancion Feliz", "--anoni--", R.raw.musica1));
        arrayList.add(new listaMusica("Cancion Triste", "--anoni--", R.raw.musica2));
        arrayList.add(new listaMusica("Cancion Pensativa", "--anoni--", R.raw.musica2));
        arrayList.add(new listaMusica("Cancion Aburrida", "--anoni--", R.raw.musica1));
        arrayList.add(new listaMusica("Cancion Motivadora", "--anoni--", R.raw.musica2));

        adapter = new Adapter(this, R.layout.lista_musica, arrayList);
        lv.setAdapter(adapter);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            actividad = bundle.getString("act1");
            codigo = bundle.getString("id");

        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listaMusica cancion = arrayList.get(i);
                String idCancion = cancion.getNombre();
                Intent intent = new Intent(iniciarmod1.this, lista_alumnos.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("act1", actividad);
                bundle1.putString("id", codigo);
                bundle1.putString("ncancion", idCancion);
                intent.putExtras(bundle1);
                Toast.makeText(getApplicationContext(),actividad+"---"+codigo+"---"+idCancion,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });



        cambiar = (Button) findViewById(R.id.botonGaleria);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(getApplicationContext(), imagenes.class);
                startActivity(c);
            }
        });

    }
/*
    public void play(View view) {
        if(mp == null){
            mp = MediaPlayer.create(this, R.raw.musica1);
            mp.start();
            Toast.makeText(MainActivity.this, "tocar", Toast.LENGTH_SHORT).show();
        }else if(!mp.isPlaying()){
            mp.seekTo(pausa);
            mp.start();
            Toast.makeText(MainActivity.this, "reanudar", Toast.LENGTH_SHORT).show();
        }

    }

    public void pausar(View view) {
        if(mp!= null) {
            mp.pause();
            pausa = mp.getCurrentPosition();
            Toast.makeText(MainActivity.this, "pausa", Toast.LENGTH_SHORT).show();
        }
    }

    public void reinicia(View view) {
        mp.stop();
        mp = null;
        Toast.makeText(MainActivity.this, "detener", Toast.LENGTH_SHORT).show();
    }

   */
}

