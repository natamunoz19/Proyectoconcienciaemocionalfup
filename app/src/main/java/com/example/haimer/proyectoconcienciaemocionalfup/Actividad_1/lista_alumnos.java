package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

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

public class lista_alumnos extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    Button guardar;
    RadioButton op1, op2, op3, op4, op5, op6 ,op7, op8, op9;
    String fecha = "";
    RadioGroup estado;
    ArrayList<Sesionestudiante> listausuario;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;
    RequestQueue requestQueue;
    String cancion = "";
    String emocion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            cancion = bundle.getString("ncancion");

        }


        guardar = (Button) findViewById(R.id.btnGuardar);
        op1 = (RadioButton) findViewById(R.id.op1);
        op2 = (RadioButton) findViewById(R.id.op2);
        op3 = (RadioButton) findViewById(R.id.op3);
        op4 = (RadioButton) findViewById(R.id.op4);
        op5 = (RadioButton) findViewById(R.id.op5);
        op6 = (RadioButton) findViewById(R.id.op6);
        op7 = (RadioButton) findViewById(R.id.op7);
        op8 = (RadioButton) findViewById(R.id.op8);
        op9 = (RadioButton) findViewById(R.id.op9);

        fecha = new Date().toString();
        estado = (RadioGroup) findViewById(R.id.preguntas);

        estado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(op1.isChecked() == true){
                    emocion = "Feliz";

                }
                if(op2.isChecked() == true){
                    emocion = "Triste";

                }
                if(op3.isChecked() == true){
                    emocion = "Enojado";

                }
                if(op4.isChecked() == true){
                    emocion = "Sorprendido";

                }
                if(op5.isChecked() == true){
                    emocion = "Somnoliento";

                }if(op6.isChecked() == true){
                    emocion = "Pensativo";

                }if(op7.isChecked() == true){
                    emocion = "Aburrido";

                }
                if(op8.isChecked() == true){
                    emocion = "Asustado";

                }
                if(op9.isChecked() == true){
                    emocion = "Apenado";

                }

            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docuencontrado= consultarsesion();
                String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                insertaractividad2("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividad1.php?documento="+docuencontrado+"&cancion="+cancion+"&emocion="+emocion+"&fecha="+fecha+"");


            }
        });
    }


    private  void insertaractividad2( String urll){


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
