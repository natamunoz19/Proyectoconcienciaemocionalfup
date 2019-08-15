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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Loginprofesor extends AppCompatActivity  {

     Ipconexion ipconexion;
    TextView registrarus;
    EditText documentolo , passwordlo ;
    Button iniciarlo ;
    DatabaseHelper bd ;

    boolean activarradiobtn ;

    RadioButton radioButton;

    static final String STRING_PREFERENCES ="proyectoconcienciaemocionalfup";
    static final String PREFERENCE_ESTADO_BUTTON_SESION="estado.button.sesion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginprofesor);


        radioButton=findViewById(R.id.rbtnsesionprofe);
        registrarus= findViewById(R.id.registrarloginprofesor);
        documentolo= findViewById(R.id.documentologinprofesor);
        passwordlo = findViewById(R.id.passwordloginprofesor);
        iniciarlo = findViewById(R.id.iniciarloginprofesor);
        bd = new DatabaseHelper(this);

            activarradiobtn=radioButton.isChecked();
        ipconexion = new Ipconexion();

          if(obtenerestadobtn()){
              Intent intent = new Intent(Loginprofesor.this,Principalprofesor.class);
              startActivity(intent);
              finish();
          }

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(activarradiobtn){
                    radioButton.setChecked(false);
                }else {
                    activarradiobtn = radioButton.isChecked();
                }
                }
        });



        registrarus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(Loginprofesor.this,Registroprofesor.class);
                startActivity(inten);
            }
        });

        iniciarlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread tr =  new Thread(){

                    @Override
                    public void run() {
                        final String res = enviarget(documentolo.getText().toString());
                        final String resdocpass = enviardocupass(documentolo.getText().toString(), passwordlo.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {



                                int re=objjson(res);
                                int resp=objdocupass(resdocpass);

                                if(re>0){
                                   if(resp>0){
                                       guardarestado();
                                       documentolo.setText("");
                                       passwordlo.setText("");
                                       Toast.makeText(getApplicationContext(),"inicio sesion",Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(Loginprofesor.this,Principalprofesor.class);
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




    public String enviarget(String doc) {
        ipconexion = new Ipconexion();
        String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/buscarprofesor.php");
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

    public int objjson (String respuesta){

        int  res =0;
        try {
            JSONArray json = new JSONArray(respuesta);
            if(json.length()>0){
                res=1;
            }

        }catch (Exception e){}

        return res;
    }



    // metodo para buscar documento y password

    public String enviardocupass(String doc,String pass) {
        ipconexion = new Ipconexion();
        String parametros ="documento="+doc+"&passwordp="+pass+"";

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/buscarprofesorlogin.php");
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

    public int objdocupass (String respuesta){

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
