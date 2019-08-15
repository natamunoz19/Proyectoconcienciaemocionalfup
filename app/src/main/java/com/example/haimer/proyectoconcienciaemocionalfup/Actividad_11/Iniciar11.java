package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_11;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2.Sesionestudiante;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Iniciar11 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    VideoView vv;
    RadioGroup rg1, rg2, rg3;
    RadioButton r1, r2, r3, r4, r5, r6;
    String fecha= "";
    String Actividad = "Actividad11";
    Button btn;

    ArrayList<Sesionestudiante> listausuario;
    String res1 = " \n";
    String res2 = " \n";
    String res3 = "\n";


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar11);

        vv = (VideoView) findViewById(R.id.videoView);
      //  Uri uri = Uri.parse("android.resource://com.example.haimer.proyectoconcienciaemocionalfup.Actividad_11/"+ R.raw.video1);
        vv.setMediaController(new MediaController(this));
     //   vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();

        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup) findViewById(R.id.radioGroup3);

        r1 = (RadioButton) findViewById(R.id.radio1);
        r2 = (RadioButton) findViewById(R.id.radio2);
        r3 = (RadioButton) findViewById(R.id.radio3);
        r4 = (RadioButton) findViewById(R.id.radio4);
        r5 = (RadioButton) findViewById(R.id.radio5);
        r6 = (RadioButton) findViewById(R.id.radio6);

        btn = (Button) findViewById(R.id.btnGuardar);
        fecha = new Date().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg1.getCheckedRadioButtonId() == -1 || rg2.getCheckedRadioButtonId() == -1 || rg3.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(),"Debe Responder todas las preguntas",Toast.LENGTH_SHORT).show();

                }else {

                    rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {

                            if (r1.isChecked() == true) {
                                res1 = r1.getText().toString();
                            }
                            if (r2.isChecked() == true) {
                                res1 = r2.getText().toString();
                            }
                        }
                    });

                    rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (r3.isChecked() == true) {
                                res2 = r3.getText().toString();
                            }
                            if (r4.isChecked() == true) {
                                res2 = r4.getText().toString();
                            }
                        }
                    });

                    rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (r5.isChecked() == true) {
                                res3 = r5.getText().toString();
                            }
                            if (r6.isChecked() == true) {
                                res3 = r6.getText().toString();
                            }

                        }
                    });
                                   }

            }
        });

        String docuencontrado= consultarsesion();
        String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        insertaractividad11("http://"+ipconexion.getIp()+"/dbandroid/WS/insertaractividad11.php?actividad="+Actividad+"documento="+docuencontrado+"&respuesta1="+res1+"&respuesta2="+res2+"&respuesta3="+res3+"&fecha="+fecha+"");

    }

    private  void insertaractividad11( String urll){


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
