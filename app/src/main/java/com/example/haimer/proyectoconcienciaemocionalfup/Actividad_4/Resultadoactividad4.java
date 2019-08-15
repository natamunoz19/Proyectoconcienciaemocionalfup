package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Resultadoactividad4 extends AppCompatActivity {
    ListView listatodos;
    EditText documentoestudiante;
    Button buscarestudiante,listatodosestudiantes ;

    Spinner spinerdias ;

    Ipconexion ipconexion;

    String diaselecion ="";


    ArrayList<String> listainformacion ;
    ArrayList<Todosresultado4>listausuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulatdoactividad4);


        listatodos= findViewById(R.id.listaresultactividad2);
        listatodosestudiantes= findViewById(R.id.btnlistartodosestudiantes);
        documentoestudiante= findViewById(R.id.buscarestuactividad2);
        buscarestudiante= findViewById(R.id.btnbuscaractividad2estudiante);
        spinerdias= findViewById(R.id.spinerdias);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combotipapel,android.R.layout.simple_spinner_item);
        spinerdias.setAdapter(adapter);

        spinerdias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                diaselecion =adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        buscarestudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){

                    @Override
                    public void run() {

                        final String resp=  buscarestudoc(documentoestudiante.getText().toString());
                        diaselecion=diaselecion.replace(" ","%20");
                        final String resps=  buscarestudocdia(documentoestudiante.getText().toString(),diaselecion);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(diaselecion.equals("Seleccione")){
                                    listarestdudoc(resp);
                                }else {
                                    listarestdudoc(resps);
                                }


                            }
                        });
                    }
                };
                tr.start();


            }
        });

        listatodosestudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){

                    @Override
                    public void run() {
                        final String resp=  buscarestudiante();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listarestidiandte(resp);
                            }
                        });
                    }
                };
                tr.start();
            }
        });

    }



    public String buscarestudiante() {

        ipconexion = new Ipconexion();

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad4.php");
            connction = (HttpURLConnection) url.openConnection();

            connction.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connction.getOutputStream());

            wr.close();
            Scanner instream = new Scanner(connction.getInputStream());
            while (instream.hasNextLine()) {
                respuesta += (instream.nextLine());

            }


        } catch (Exception e) {
            respuesta=" no existe";
        }

        return respuesta.toString();


    }


    public void listarestidiandte (String respuesta){

        listausuario= new ArrayList<Todosresultado4>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado4 u = new Todosresultado4();
                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setTipoimagen(jsonarreglo.getJSONObject(i).getString("tipoimagen"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("estadoactividad"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario.add(u);


            }
            obtenerlista();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    private void obtenerlista() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() >= 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  tipo de material : "+listausuario.get(i).tipoimagen+" estado actividad : "+listausuario.get(i).estadoactividad+"  fecha :  "+listausuario.get(i).fecha);

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listatodos.setAdapter(adaptador);


    }


    // buscar por documento estudiante




    public String buscarestudoc(String doc) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad4documento.php");
            connction = (HttpURLConnection) url.openConnection();
            connction.setRequestMethod("POST");
            connction.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            connction.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connction.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner instream = new Scanner(connction.getInputStream());
            while (instream.hasNextLine()) {
                respuesta += (instream.nextLine());

            }


        } catch (Exception e) {
            respuesta=" no existe";
        }

        return respuesta.toString();


    }

    public void listarestdudoc (String respuesta){
        listausuario= new ArrayList<Todosresultado4>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado4 u = new Todosresultado4();

                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setTipoimagen(jsonarreglo.getJSONObject(i).getString("tipoimagen"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("estadoactividad"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario.add(u);


            }
            obtenerlistadocumento();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    private void obtenerlistadocumento() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() >= 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add("  Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  tipo de material : "+listausuario.get(i).tipoimagen+" estado actividad : "+listausuario.get(i).estadoactividad+"  fecha :  "+listausuario.get(i).fecha);

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listatodos.setAdapter(adaptador);


    }

    public String buscarestudocdia(String doc,String dia) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc+"&tipoimagen="+dia+"";

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad4documentotipoimagen.php");

            connction = (HttpURLConnection) url.openConnection();
            connction.setRequestMethod("POST");
            connction.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            connction.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connction.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner instream = new Scanner(connction.getInputStream());
            while (instream.hasNextLine()) {
                respuesta += (instream.nextLine());

            }


        } catch (Exception e) {
            respuesta=" no existe";
        }

        return respuesta.toString();

    }
}
