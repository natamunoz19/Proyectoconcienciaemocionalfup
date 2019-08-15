package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Todosresultado;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Resultadoactividad13 extends AppCompatActivity {



    ListView listatodos;
    EditText documentoestudiante;
    Button buscarestudiante,listatodosestudiantes ;



    Ipconexion ipconexion;

    String diaselecion ="";


    ArrayList<String> listainformacion ;
    ArrayList<Todosresultado>listausuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoactividad13);

        listatodos= findViewById(R.id.listaresultactividad2);
        listatodosestudiantes= findViewById(R.id.btnlistartodosestudiantes);
        documentoestudiante= findViewById(R.id.buscarestuactividad2);
        buscarestudiante= findViewById(R.id.btnbuscaractividad2estudiante);

        buscarestudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){

                    @Override
                    public void run() {

                        final String resp=  buscarestudoc(documentoestudiante.getText().toString());


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                    listarestdudoc(resp);
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
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad13.php");
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

        listausuario= new ArrayList<Todosresultado>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado u = new Todosresultado();
                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("horainicio"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("horafin"));
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
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  hora inicion  : "+listausuario.get(i).getEstadoactividad()+"  hora fin : "+listausuario.get(i).getDiasemana()+"  fecha :  "+listausuario.get(i).getFecha());

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
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad13documento.php");
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
        listausuario= new ArrayList<Todosresultado>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado u = new Todosresultado();

                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("horainicio"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("horafin"));
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
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  hora inicion  : "+listausuario.get(i).getEstadoactividad()+"  hora fin : "+listausuario.get(i).getDiasemana()+"  fecha :  "+listausuario.get(i).getFecha());

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listatodos.setAdapter(adaptador);


    }







}
