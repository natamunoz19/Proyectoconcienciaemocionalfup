package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Resultadoactividad7 extends AppCompatActivity {

    ListView listatodos ,listatodos7;
    EditText documentoestudiante ,documentoestudiante7;
    Button buscarestudiante,listatodosestudiantes ,buscarestudiante7 ,listatodosestudiantes7;

    Spinner spinerdias, spinerdias7 ;

    Ipconexion ipconexion;

    String diaselecion ="";
    String diaselecion7 ="";


    ArrayList<String> listainformacion ;
    ArrayList<Todosresultado>listausuario;
    ArrayList<String> listainformacion7 ;
    ArrayList<Todosresultado>listausuario7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoactividad7);

        listatodos= findViewById(R.id.listaresultactividad2);
        listatodosestudiantes= findViewById(R.id.btnlistartodosestudiantes);
        documentoestudiante= findViewById(R.id.buscarestuactividad2);
        buscarestudiante= findViewById(R.id.btnbuscaractividad2estudiante);
        spinerdias= findViewById(R.id.spinerdias);

        listatodos7= findViewById(R.id.listaresultactividad7);
        listatodosestudiantes7= findViewById(R.id.btnlistartodosestudiantes7);
        documentoestudiante7= findViewById(R.id.buscarestuactividad7);
        buscarestudiante7= findViewById(R.id.btnbuscaractividad7estudiante);
        spinerdias7= findViewById(R.id.spinerdias7);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipojuegos,android.R.layout.simple_spinner_item);
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

        adapter = ArrayAdapter.createFromResource(this, R.array.tipojuegos, android.R.layout.simple_spinner_item);
        spinerdias7.setAdapter(adapter);

        spinerdias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                diaselecion7 =adapterView.getItemAtPosition(i).toString();
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


        buscarestudiante7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){

                    @Override
                    public void run() {

                        final String respp=  buscarestudoc7(documentoestudiante7.getText().toString());
                        final String respss=  buscarestudocdia7(documentoestudiante.getText().toString(),diaselecion7);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(diaselecion7.equals("Seleccione")) {
                                    listarestdudoc7(respp);
                                } else {
                                    listarestdudoc7(respss);
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



        listatodosestudiantes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){

                    @Override
                    public void run() {
                        final String resp=  buscarestudiante7();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listarestidiandte7(resp);
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
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7.php");
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

    public String buscarestudiante7() {

        ipconexion = new Ipconexion();

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7_1.php");
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
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("tipojuego"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("emocion1"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario.add(u);


            }
            obtenerlista();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    public void listarestidiandte7 (String respuesta){

        listausuario7= new ArrayList<Todosresultado>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado u = new Todosresultado();
                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("tipojuego"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("emocion1"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario7.add(u);


            }
            obtenerlista7();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    private void obtenerlista() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() >= 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  tipo juego : "+listausuario.get(i).getEstadoactividad()+"  estado : "+listausuario.get(i).getDiasemana()+"  fecha :  "+listausuario.get(i).getFecha());

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listatodos.setAdapter(adaptador);


    }


    private void obtenerlista7() {

        listainformacion7= new ArrayList<String>();

        if (listausuario7.size() >= 1){
            for (int i =0; i<listausuario7.size();i++){
                listainformacion7.add(" Documento : "+listausuario7.get(i).getDocumento() + "  Nombre : "+listausuario7.get(i).getNombre()+"  tipo juego : "+listausuario7.get(i).getEstadoactividad()+"  estado : "+listausuario7.get(i).getDiasemana()+"  fecha :  "+listausuario7.get(i).getFecha());

            }


        }else {
            listainformacion7.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion7);
        listatodos7.setAdapter(adaptador);


    }


    // buscar por documento estudiante




    public String buscarestudoc7(String doc) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7_1documento.php");
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

    public String buscarestudoc(String doc) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7documento.php");
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



    public void listarestdudoc7 (String respuesta){
        listausuario= new ArrayList<Todosresultado>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado u = new Todosresultado();

                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("tipojuego"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("emocion1"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario.add(u);


            }
            obtenerlistadocumento();
        }catch (Exception e){ e.printStackTrace();
        }

    }
    public void listarestdudoc (String respuesta){
        listausuario7= new ArrayList<Todosresultado>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                Todosresultado u = new Todosresultado();

                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
                u.setEstadoactividad(jsonarreglo.getJSONObject(i).getString("tipojuego"));
                u.setDiasemana(jsonarreglo.getJSONObject(i).getString("emocion1"));
                u.setFecha(jsonarreglo.getJSONObject(i).getString("fecha"));
                listausuario7.add(u);


            }
            obtenerlistadocumento7();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    private void obtenerlistadocumento() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() >= 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento() + "  Nombre : "+listausuario.get(i).getNombre()+"  tipo juego : "+listausuario.get(i).getEstadoactividad()+"  estado : "+listausuario.get(i).getDiasemana()+"  fecha :  "+listausuario.get(i).getFecha());

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listatodos.setAdapter(adaptador);


    }

    private void obtenerlistadocumento7() {

        listainformacion7= new ArrayList<String>();

        if (listausuario7.size() >= 1){
            for (int i =0; i<listausuario7.size();i++){
                listainformacion7.add(" Documento : "+listausuario7.get(i).getDocumento() + "  Nombre : "+listausuario7.get(i).getNombre()+"  tipo juego : "+listausuario7.get(i).getEstadoactividad()+"  estado : "+listausuario7.get(i).getDiasemana()+"  fecha :  "+listausuario7.get(i).getFecha());

            }


        }else {
            listainformacion7.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion7);
        listatodos7.setAdapter(adaptador);


    }
    public String buscarestudocdia(String doc,String dia) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc+"&tipojuego="+dia+"";

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7documentojuego.php");
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




    public String buscarestudocdia7(String doc,String dia) {


        ipconexion = new Ipconexion();
        String parametros ="documento="+doc+"&tipojuego="+dia+"";

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/resultadoactividad7_1documentojuego.php");
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
