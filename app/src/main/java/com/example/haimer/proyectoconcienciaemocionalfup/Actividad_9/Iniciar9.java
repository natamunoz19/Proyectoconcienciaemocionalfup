package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Iniciar9 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9;
    RadioGroup elegir;
    String fecha = "";
    String Actividad = "Actividad9";
    String res = "";
    Button guardar;
    ArrayList<Sesionestudiante> listausuario;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar9);

        r1 = (RadioButton) findViewById(R.id.radioGroup1);
        r2 = (RadioButton) findViewById(R.id.radioGroup2);
        r3 = (RadioButton) findViewById(R.id.radioGroup3);
        r4 = (RadioButton) findViewById(R.id.radioGroup4);
        r5 = (RadioButton) findViewById(R.id.radioGroup5);
        r6 = (RadioButton) findViewById(R.id.radioGroup6);
        r7 = (RadioButton) findViewById(R.id.radioGroup7);
        r8 = (RadioButton) findViewById(R.id.radioGroup8);
        r9 = (RadioButton) findViewById(R.id.radioGroup9);

        fecha = new Date().toString();
        elegir = (RadioGroup) findViewById(R.id.elecccion);
        guardar = (Button) findViewById(R.id.btnGuardar);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        elegir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(r1.isChecked() == true){
                    res = "Feliz";

                }
                if(r2.isChecked() == true){
                    res = "Triste";

                }
                if(r3.isChecked() == true){
                    res = "Enojado";

                }
                if(r4.isChecked() == true){
                    res = "Sorprendido";

                }
                if(r5.isChecked() == true){
                    res = "Somnoliento";

                }if(r6.isChecked() == true){
                    res = "Pensativo";

                }if(r7.isChecked() == true){
                    res = "Aburrido";

                }
                if(r8.isChecked() == true){
                    res = "Asustado";

                }
                if(r9.isChecked() == true){
                    res = "Apenado";

                }
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docuencontrado= consultarsesion();
                String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                insertaractividad9("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividad9.php?actividad="+Actividad+"documento="+docuencontrado+"&respuesta1="+res+"&dibujocanvas=hola"+"&fecha="+fecha+"");
            }
        });


    }


    private  void insertaractividad9( String urll){


        String url =urll;

        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
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


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
