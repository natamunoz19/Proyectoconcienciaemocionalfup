package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.R;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Iniciar1 extends AppCompatActivity {

    ListView lv;

    ArrayList<String> listainformacion ;
    ArrayList<estudiante> listausuario;
    DatabaseHelper conn;
    Ipconexion ipconexion;
    String actividad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar1);

        conn = new DatabaseHelper(getApplicationContext());
        lv = (ListView) findViewById(R.id.vista);


        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            actividad = bundle.getString("act1");

        }


        Thread tr = new Thread(){

            @Override
            public void run() {
                final String resp=  buscarestudiante();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listarprofe(resp);
                    }

                });

            }

        };
        tr.start();
    }

    public void listarprofe (String respuesta){
        listausuario= new ArrayList<estudiante>();

        try{
            JSONArray jsonarreglo = new JSONArray(respuesta);
            for (int i = 0;i<jsonarreglo.length();i++){
                estudiante u = new estudiante();
                u.setDocumento(jsonarreglo.getJSONObject(i).getString("documento"));
                u.setNnombre(jsonarreglo.getJSONObject(i).getString("nombre"));

                listausuario.add(u);


            }
            obtenerlista();
        }catch (Exception e){ e.printStackTrace();
        }

    }

    private void obtenerlista() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() >= 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add(" Documento : "+listausuario.get(i).getDocumento()+ "  Nombre : "+listausuario.get(i).getNnombre());

            }


        }else {
            listainformacion.add(" usuario no existe");



        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                estudiante id = listausuario.get(i);
                String codigo = id.getDocumento();
                //estudiante nom = listausuario.get(i);
                //String nombre = nom.getNnombre();
                Intent intent = new Intent(Iniciar1.this, iniciarmod1.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("act1",actividad);
                bundle1.putString("id", codigo);
                intent.putExtras(bundle1);
                Toast.makeText(getApplicationContext(),actividad+"---"+codigo,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }
    public String buscarestudiante() {
        ipconexion = new Ipconexion();
        //String parametros ="documento="+doc;

        HttpURLConnection connction = null;
        String respuesta = "";
        try {
            URL url = new URL("http://"+ipconexion.getIp()+"/dbandroid/WS/todosestudiantes.php");
            connction = (HttpURLConnection) url.openConnection();
          //  connction.setRequestMethod("POST");
          //  connction.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            connction.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connction.getOutputStream());
            //wr.writeBytes(parametros);
            wr.close();
            Scanner instream = new Scanner(connction.getInputStream());
            while (instream.hasNextLine()) {
                respuesta += (instream.nextLine());

            }


        } catch (Exception e) {
            respuesta=" no existe";
        }

        return respuesta;


    }


}
