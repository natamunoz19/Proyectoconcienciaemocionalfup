package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.*;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.Principalestudiantes;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Actividad4_3 extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {
    Spinner spinner;
    ImageView imageView ,imagen2 ;
    Button siguiente;
    Ipconexion ipconexion;
    RequestQueue requestQueue;


    JsonObjectRequest jsonObjectRequest;
    ArrayList<Sesionestudiante> listausuario;
    DatabaseHelper conn;
    String estadoactividad="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad4_3);


        spinner=findViewById(R.id.spinner);
        imageView=findViewById(R.id.imageView);
        siguiente= findViewById(R.id.btnsiguienteactiviadad4);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String docuencontrado= consultarsesion();
                String lija="papel burbuja";
                String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                insertaractiviada4("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractiviadad4.php?documento="+docuencontrado+"&tipoimagen="+lija+"&estadoactividad="+estadoactividad+"&fecha="+fecha+"");

                Intent intent = new Intent(Actividad4_3.this,Principalestudiantes.class);
                startActivity(intent);
            }
        });



        final String str[]={"escoje","risa","enojado","triste"};


        ArrayAdapter arrayAdapter=new ArrayAdapter(Actividad4_3.this,android.R.layout.simple_spinner_dropdown_item,str);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (spinner.getSelectedItem().equals("escoje")){

                    imageView.setImageResource(R.drawable.blanca);
                    estadoactividad =parent.getItemAtPosition(i).toString();
                    siguiente.setEnabled(false);

                }
                else if (spinner.getSelectedItem().equals("risa")){

                    imageView.setImageResource(R.drawable.risa4);
                    estadoactividad =parent.getItemAtPosition(i).toString();
                    siguiente.setEnabled(true);

                } else if  (spinner.getSelectedItem().equals("enojado")){

                    imageView.setImageResource(R.drawable.enojado4);
                    estadoactividad =parent.getItemAtPosition(i).toString();
                    siguiente.setEnabled(true);

                }else  if (spinner.getSelectedItem().equals("triste")){

                    imageView.setImageResource(R.drawable.triste4);
                    estadoactividad =parent.getItemAtPosition(i).toString();
                    siguiente.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private  void insertaractiviada4( String urll){

        String url =urll;

        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }



    private String consultarsesion( ) {

        SQLiteDatabase db = conn.getReadableDatabase();
        Sesionestudiante sesionestudiant = null;
        listausuario= new ArrayList<Sesionestudiante>();
        String doc ="";

        Cursor cursor =db.rawQuery("select * from iniciaronsesionestudiante  ORDER BY numero DESC LIMIT 1 ",null);
        while (cursor.moveToNext()){
            sesionestudiant= new Sesionestudiante();
            sesionestudiant.setPocision(cursor.getInt(0));
            sesionestudiant.setDocumento(cursor.getString(1));

            listausuario.add(sesionestudiant);
        }
        doc =listausuario.get(0).getDocumento();
        return  doc;
    }

}
