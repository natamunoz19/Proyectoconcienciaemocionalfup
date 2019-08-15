package com.example.haimer.proyectoconcienciaemocionalfup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Registroprofesor extends AppCompatActivity  implements  Response.Listener<JSONObject>,Response.ErrorListener {


    DatabaseHelper db;
    Button btnregistrarprofesor;
    EditText documento, nombre, apellido, correo, telefono, password, password2;
    RequestQueue requestQueue;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroprofesor);

        ipconexion = new Ipconexion();


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        documento = findViewById(R.id.documentoprofesor);
        nombre = findViewById(R.id.nombreprofesor);
        apellido = findViewById(R.id.apellidoprofesor);
        correo = findViewById(R.id.correoprofesor);
        telefono = findViewById(R.id.telefonoprofesor);
        password = findViewById(R.id.passwordprofesor);
        password2 = findViewById(R.id.passwordprofesor2);

        db = new DatabaseHelper(this);

        btnregistrarprofesor = findViewById(R.id.btnregistrarprofesor);

        btnregistrarprofesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doc = documento.getText().toString();
                String nom = nombre.getText().toString();
                String apell = apellido.getText().toString();
                String corr = correo.getText().toString();
                String tele = telefono.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();

                if (doc.equals("") || nom.equals("") || apell.equals("") || corr.equals("") || tele.equals("") || pass.equals("") || pass2.equals("")) {

                    Toast.makeText(getApplicationContext(), "Inserte Datos", Toast.LENGTH_SHORT).show();

                } else {

                    if (pass.equals(pass2)) {

                      Thread tr =  new Thread(){
                          @Override
                          public void run() {
                             final String res = enviarget(documento.getText().toString());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int r=objjson(res);

                                    if (r>0){
                                        Toast.makeText(getApplicationContext(), "usuario ya existe ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        String doc = documento.getText().toString();
                                        String nom = nombre.getText().toString();
                                        String apell = apellido.getText().toString();
                                        String corr = correo.getText().toString();
                                        String tele = telefono.getText().toString();
                                        String pass = password.getText().toString();
                                        String pass2 = password2.getText().toString();

                                        boolean verificardoc = db.buscadocumentoprofesor(doc);

                                        if (verificardoc == true) {

                                            boolean insert = db.insertprofesor(doc, nom, apell, corr, tele, pass);
                                            cargarwebserviceinsert();
                                            if (insert == true) {
                                                documento.setText("");
                                                nombre.setText("");
                                                apellido.setText("");
                                                correo.setText("");
                                                telefono.setText("");
                                                password.setText("");
                                                password2.setText("");
                                                Toast.makeText(getApplicationContext(), "registro exitoso", Toast.LENGTH_SHORT).show();

                                                //  Intent intent  = new Intent(Registroprofesor.this,Loginprofesor.class);
                                                //startActivity(intent);
                                            } else {
                                                Toast.makeText(getApplicationContext(), "registro no exitoso", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "usuario ya existe ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                          }
                      };
                      tr.start();
                    } else {
                        Toast.makeText(getApplicationContext(), "contraseña no coincide ", Toast.LENGTH_SHORT).show();
                        password.setText("");
                        password2.setText("");
                    }
                }
            }
        });
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



    private  void cargarwebserviceinsert(){


          ipconexion= new Ipconexion();

        String url ="http://"+ipconexion.getIp()+"/dbandroid/WS/insetar.php?documento="+documento.getText().toString()+"&nombre="+ nombre.getText().toString()+"&apellido="+apellido.getText().toString()+"&correo="+correo.getText().toString()+"&telefono="+telefono.getText().toString()+"&passwordp="+password.getText().toString()+"";
        url=url.replace(" ","%20");

             jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
             requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(getApplicationContext(),"registro en mysql exitoso ",Toast.LENGTH_SHORT ).show();

           }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getApplicationContext(),"registro no se realizo  ",Toast.LENGTH_SHORT ).show();
        Log.i("ERROR",error.toString());
    }




}
