package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Iniciar5 extends AppCompatActivity  implements Response.Listener<JSONObject>,Response.ErrorListener{

    RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    RadioGroup rg1, rg2, rg3;
    ViewPager vp;
    String fecha = "";
    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;
    RequestQueue requestQueue;
    ArrayList<Sesionestudiante> listausuario;
    String actividad = "Actividad 5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar5);



        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        rb6 = (RadioButton) findViewById(R.id.radio6);

        rg1 = (RadioGroup) findViewById(R.id.preguntaid1);
        rg2 = (RadioGroup) findViewById(R.id.preguntaid2);
        rg3 = (RadioGroup) findViewById(R.id.preguntaid3);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        fecha = new Date().toString();
        vp = (ViewPager) findViewById(R.id.vistaMimica);
        Adaptador adaptador = new Adaptador(getSupportFragmentManager());
        vp.setAdapter(adaptador);

    }

    public void onClick(View view) {

        if(rg1.getCheckedRadioButtonId() == -1 || rg2.getCheckedRadioButtonId() == -1 || rg3.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(),"Debe Responder todas las preguntas",Toast.LENGTH_SHORT).show();

        }else{
            if(view.getId() == R.id.btnGuardar){
                validar();
            }
        }
    }

    private void validar() {
        String res1 = " \n";
        String res2 = " \n";
        String res3 = " ";
        if(rb1.isChecked() == true) {
            res1 = rb1.getText().toString();
        }
        if(rb2.isChecked() == true){
            res1 = rb2.getText().toString();
        }
        if(rb3.isChecked() == true){
            res2 = rb3.getText().toString();
        }
        if(rb4.isChecked() == true){
            res2 = rb4.getText().toString();
        }
        if(rb5.isChecked() == true){
            res3 = rb5.getText().toString();
        }
        if(rb6.isChecked() == true){
            res3 = rb6.getText().toString();
        }

        Toast.makeText(getApplicationContext(),res1 +"--"+ res2 +"--"+ res3, Toast.LENGTH_SHORT).show();
        String docuencontrado= consultarsesion();
        String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        insertaractividad5("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividad5.php?actividad="+actividad+"&documento="+docuencontrado+"&respuesta1="+res1+"&respuesta2="+res2+"&respuesta3="+res3+"&fecha="+fecha+"");
    }

    private  void insertaractividad5( String urll){


        String url =urll;

        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    private String consultarsesion( ) {

        SQLiteDatabase db = conn.getReadableDatabase();
        com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante sesionestudiant = null;
        listausuario= new ArrayList<Sesionestudiante>();
        String doc ="";

        Cursor cursor =db.rawQuery("select * from iniciaronsesionestudiante  ORDER BY numero DESC LIMIT 1 ",null);
        while (cursor.moveToNext()){
            sesionestudiant= new Sesionestudiante();
            sesionestudiant.setPocision(cursor.getInt(0));
            sesionestudiant.setDocumento(cursor.getString(1));

            listausuario.add((Sesionestudiante) sesionestudiant);
        }
        doc =listausuario.get(0).getDocumento();
        return  doc;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }


}


