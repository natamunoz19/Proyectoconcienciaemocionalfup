package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_12;

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

public class Iniciar12 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    RadioGroup rg1;
    RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9;
    String fecha= "";
    String Actividad = "Actividad12";
    String emocion = "";
    Button guardar;

    ArrayList<Sesionestudiante> listausuario;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar12);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        fecha = new Date().toString();
        rg1 = (RadioGroup) findViewById(R.id.radioGroup);

        guardar = (Button) findViewById(R.id.btnGuardar);
        r1 = (RadioButton) findViewById(R.id.RadioButton1);
        r2 = (RadioButton) findViewById(R.id.RadioButton2);
        r3 = (RadioButton) findViewById(R.id.RadioButton3);
        r4 = (RadioButton) findViewById(R.id.RadioButton4);
        r5 = (RadioButton) findViewById(R.id.RadioButton5);
        r6 = (RadioButton) findViewById(R.id.RadioButton6);
        r7 = (RadioButton) findViewById(R.id.RadioButton7);
        r8 = (RadioButton) findViewById(R.id.RadioButton8);
        r9 = (RadioButton) findViewById(R.id.RadioButton9);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(r1.isChecked() == true){
                    emocion = "Feliz";

                }
                if(r2.isChecked() == true){
                    emocion = "Triste";

                }
                if(r3.isChecked() == true){
                    emocion = "Enojado";

                }
                if(r4.isChecked() == true){
                    emocion = "Sorprendido";

                }
                if(r5.isChecked() == true){
                    emocion = "Somnoliento";

                }if(r6.isChecked() == true){
                    emocion = "Pensativo";

                }if(r7.isChecked() == true){
                    emocion = "Aburrido";

                }
                if(r8.isChecked() == true){
                    emocion = "Asustado";

                }
                if(r9.isChecked() == true){
                    emocion = "Apenado";

                }

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docuencontrado= consultarsesion();
                String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                insertaractividad12("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividad12.php?actividad=+"+Actividad+"documento="+docuencontrado+"&emocion="+emocion+"&fecha="+fecha+"");


            }
        });


    }

    private  void insertaractividad12( String urll){


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
