package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.Principalestudiantes;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Actividad7_1 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {


    RadioGroup radioGroup, radioGroup1;
    RadioButton r1, r2, r3, r4,r5,r6,r7,r8;
    String tipojuego ;

    Button finalizar;

    String emocion1,emocion2;

    RequestQueue requestQueue;
    ArrayList<Sesionestudiante> listausuario;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;

    String dia ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actiividad7_1);


        finalizar= findViewById(R.id.btnfinaizar);

        r1= findViewById(R.id.r1);
        r2= findViewById(R.id.r2);
        r3= findViewById(R.id.r3);
        r4= findViewById(R.id.r4);
        r5= findViewById(R.id.r5);
        r6= findViewById(R.id.r6);
        r7= findViewById(R.id.r7);
        r8= findViewById(R.id.r8);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);


        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            tipojuego = bundle.getString("docu");

        }


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String docuencontrado= consultarsesion();
                String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());



                insertaractividad2("http://"+ipconexion.getIp()+"/dbandroid/WS/insertactividad7.php?documento="+docuencontrado+"&tipojuego="+tipojuego+"&emocion1="+emocion1+"&emocion2="+emocion2+"&fecha="+fecha+"");

                Intent intent = new Intent(Actividad7_1.this, Principalestudiantes.class);
                startActivity(intent);


            }
        });


        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);radioGroup1=(RadioGroup) findViewById(R.id.radioGroup1);




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (r1.isChecked()== true) {
                    emocion1="enoja";
                    Toast.makeText(getApplicationContext(), "me enoja", Toast.LENGTH_SHORT).show();
                } else if (r2.isChecked()== true) {

                    emocion1 ="me gusta";
                    Toast.makeText(getApplicationContext(), "me gusta", Toast.LENGTH_SHORT).show();
                } else if (r3.isChecked()== true) {
                    emocion1="triste";

                    Toast.makeText(getApplicationContext(), "no me gusta", Toast.LENGTH_SHORT).show();
                } else if (r4.isChecked()== true) {
                    emocion1="divierte";

                    Toast.makeText(getApplicationContext(), "me divierte", Toast.LENGTH_SHORT).show();
                }
            }


        });



        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup1, int i) {

                if (r5.isChecked()==true) {


                    emocion2 ="enoja";
                    Toast.makeText(getApplicationContext(), "me enoja", Toast.LENGTH_SHORT).show();
                } else if (r6.isChecked()== true) {
                    emocion2 ="me gusta";

                    Toast.makeText(getApplicationContext(), "me gusta", Toast.LENGTH_SHORT).show();
                } else if (r7.isChecked()== true) {
                    emocion2 ="triste";

                    Toast.makeText(getApplicationContext(), "no me gusta", Toast.LENGTH_SHORT).show();
                } else if (r8.isChecked()== true) {
                    emocion2 ="divierte";
                    Toast.makeText(getApplicationContext(), "me divierte", Toast.LENGTH_SHORT).show();
                }
            }


        });




    }


    private  void insertaractividad2( String urll){


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
