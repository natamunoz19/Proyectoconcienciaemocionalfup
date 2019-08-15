package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Registroestudiante extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener  {

   Ipconexion ipconexion;
    RequestQueue requestQueue;


    JsonObjectRequest jsonObjectRequest;
    DatabaseHelper db;
    Button btnregistrar;
    EditText documento,nombre,apellido,grado,telefono,password,password2 ,docprofesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgistroestudiante);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        documento= findViewById(R.id.documentousuario);
        nombre= findViewById(R.id.nombresusario);
        apellido = findViewById(R.id.apellidousuario);
        grado= findViewById(R.id.gradousuario);
        telefono= findViewById(R.id.telefonousuario);
        password = findViewById(R.id.passwordusuario);
        password2= findViewById(R.id.passwordusuario2);
        docprofesor= findViewById(R.id.docprofeestudiante);


            Bundle bundle = this.getIntent().getExtras();
         if(bundle != null){
             String documentopro = bundle.getString("docu");
             docprofesor.setText(documentopro);
         }


        db = new DatabaseHelper(this);
        btnregistrar= findViewById(R.id.btnregistrar);





        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String   doc = documento.getText().toString();
                String   nom = nombre.getText().toString();
                String  apell = apellido.getText().toString();
                String gra = grado.getText().toString();
                String tele = telefono.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();
                String docp= docprofesor.getText().toString();

                if(doc.equals("")|| nom.equals("") || apell.equals("") || gra.equals("")|| tele.equals("")|| pass.equals("")|| pass2.equals("") || docp.equals("")){

                    Toast.makeText(getApplicationContext(),"Inserte Datos",Toast.LENGTH_SHORT ).show();

                } else {

                    if(pass.equals(pass2)){


                        Thread tr = new Thread(){

                            @Override
                            public void run() {
                              final  String estu = buscarestudiante(documento.getText().toString());

                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           int es = objestu(estu);

                                           if(es>0){
                                               Toast.makeText(getApplicationContext(), "usuario ya existe ", Toast.LENGTH_SHORT).show();

                                           }else{
                                               String   doc = documento.getText().toString();
                                               String   nom = nombre.getText().toString();
                                               String  apell = apellido.getText().toString();
                                               String gra = grado.getText().toString();
                                               String tele = telefono.getText().toString();
                                               String pass = password.getText().toString();
                                               String docp= docprofesor.getText().toString();


                                               boolean insert = db.insert(doc,nom,apell,gra,tele,pass,docp);
                                                    cargarwebserviceinsert();
                                               if ( insert == true ){
                                                   documento.setText("");
                                                   nombre.setText("");
                                                   apellido.setText("");
                                                   grado.setText("");
                                                   telefono.setText("");
                                                   password.setText("");
                                                   password2.setText("");
                                                   docprofesor.setText("");
                                                   Toast.makeText(getApplicationContext(),"registro exitoso",Toast.LENGTH_SHORT ).show();
                                                  // Intent intent  = new Intent(Registroestudiante.this,Loginestudiante.class);
                                                //   startActivity(intent);

                                               }else {
                                                   Toast.makeText(getApplicationContext(),"registro no exitoso",Toast.LENGTH_SHORT ).show();
                                               }


                                           }


                                       }
                                   });

                            }


                        };
                        tr.start();

                    } else{
                        Toast.makeText(getApplicationContext(),"contraseña no coincide ",Toast.LENGTH_SHORT ).show();

                        password.setText("");
                        password2.setText("");

                    }


                }





            }
        });






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






    private  void cargarwebserviceinsert(){


        ipconexion= new Ipconexion();

        String url ="http://"+ipconexion.getIp()+"/dbandroid/WS/insertarestudiante.php?documento="+documento.getText().toString()+"&nombre="+nombre.getText().toString()+"&apellido="+apellido.getText().toString()+"&grado="+grado.getText().toString()+"&telefono="+telefono.getText().toString()+"&passworde="+password.getText().toString()+"&documentop="+docprofesor.getText().toString()+"";
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"registro no se realizo  ",Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
