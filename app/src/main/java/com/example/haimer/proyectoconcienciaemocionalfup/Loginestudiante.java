package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Loginestudiante extends AppCompatActivity {


    TextView registrarus;
    EditText documentolo , passwordlo ;
    Button iniciarlo ;
    DatabaseHelper bd ;
    Ipconexion ipconexion;
    RadioButton radioButton;
    boolean activarradio;
    static final String STRING_PREFERENCES ="proyectoconcienciaemocionalfup";
    static final String PREFERENCE_ESTADO_BUTTON_SESION="estado.button.sesion";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginestudiante);



     radioButton= findViewById(R.id.rbtnestudiante);
        registrarus= findViewById(R.id.registrarlogin);
        documentolo= findViewById(R.id.documentologin);
        passwordlo = findViewById(R.id.passwordlogin);
        iniciarlo = findViewById(R.id.iniciarlogin);
        bd = new DatabaseHelper(this);
        activarradio=radioButton.isChecked();

        if(obtenerestadobtn()){
            Intent intent = new Intent(Loginestudiante.this,Principalestudiantes.class);
            startActivity(intent);
            finish();
        }


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activarradio){
                    radioButton.setChecked(false);
                }else {
                    activarradio = radioButton.isChecked();
                }
            }
        });


        iniciarlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Thread tr = new Thread(){
                    @Override
                    public void run() {
                     final   String doces= buscarestudiante(documentolo.getText().toString());
                     final   String docpass = buscarestudiantedocpass(documentolo.getText().toString(),passwordlo.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                              int resdoc = objestu(doces);
                              int resdopas =objbuscardocpas(docpass);

                                if(resdoc>0){
                                    if(resdopas>0){
                                        guardarestado();
                                        boolean insert =bd.insertsesionestudiante(documentolo.getText().toString());
                                        if (insert== true){
                                            Toast.makeText(getApplicationContext(),"se guardo sesion  ",Toast.LENGTH_SHORT).show();

                                        }else{
                                            Toast.makeText(getApplicationContext(),"no se guardo sesion ",Toast.LENGTH_SHORT).show();

                                        }

                                        documentolo.setText("");
                                        passwordlo.setText("");

                                        Toast.makeText(getApplicationContext(),"inicio sesion",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Loginestudiante.this,Principalestudiantes.class);
                                        startActivity(intent);


                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"contraseña incorrecta ",Toast.LENGTH_SHORT).show();
                                        passwordlo.setText("");
                                    }


                                }else{
                                    documentolo.setText("");
                                    passwordlo.setText("");
                                    Toast.makeText(getApplicationContext(),"usuario no existe",Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    }
                };
                tr.start();

            }
        });


        registrarus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Loginestudiante.this,Listaprofesor.class);
                startActivity(intent);
            }
        });

    }


    public  static void cambiarestado(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,b).apply();

    }

    public  void guardarestado(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,radioButton.isChecked()).apply();



    }

    public  boolean obtenerestadobtn(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        return  preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);

    }




    public String buscarestudiante(String doc) {
        ipconexion = new Ipconexion();
        String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/buscarestudiantes.php");
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

        } catch (Exception e) {}
        return respuesta.toString();
    }

    public int objestu (String respuesta){

        int  res =0;
        try {
            JSONArray json = new JSONArray(respuesta);
            if(json.length()>0){
                res=1;
            }

        }catch (Exception e){}

        return res;
    }





    // buscar estudiante mas docmuento + password


    public String buscarestudiantedocpass(String doc,String pass) {
        ipconexion = new Ipconexion();
        String parametros ="documento="+doc+"&passworde="+pass+"";

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/buscarloginestudiante.php");
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

        } catch (Exception e) {}
        return respuesta.toString();
    }

    public int objbuscardocpas (String respuesta){

        int  res =0;
        try {
            JSONArray json = new JSONArray(respuesta);
            if(json.length()>0){
                res=1;
            }

        }catch (Exception e){}

        return res;
    }


}
