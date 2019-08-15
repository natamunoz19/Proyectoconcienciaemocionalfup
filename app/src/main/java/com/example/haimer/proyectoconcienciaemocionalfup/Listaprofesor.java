package com.example.haimer.proyectoconcienciaemocionalfup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Listaprofesor extends AppCompatActivity {


    ListView listaprofesor;
    Button buscarprofe ,seleccionarprofe ,continuar;
    EditText docprofe , documentoselec,nuevodoc,nuevonom ;

    String docuprofesor , nombreprofe ;

    DatabaseHelper conn;
    ArrayList<String>listainformacion ;
    ArrayList<Usuarios>listausuario;

    Ipconexion ipconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaprofesor);

        conn= new DatabaseHelper(this);
        listaprofesor = findViewById(R.id.ListaProfesor);
        docprofe = findViewById(R.id.textbuscardocprofe);
        buscarprofe =findViewById(R.id.btnbuscarprofe);
        seleccionarprofe = findViewById(R.id.btnseleccionarprofe);
        documentoselec = findViewById(R.id.docprofeestudiante);
        nuevodoc= findViewById(R.id.docuprofeencontrado);
        nuevonom=findViewById(R.id.nombreprofeencontrado);
        continuar= findViewById(R.id.btncontinuar);
        continuar.setEnabled(false);

        seleccionarprofe.setEnabled(false);

         continuar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(nuevodoc.getText().toString().equals("") || nuevonom.getText().toString().equals("")  ){

                     Toast.makeText(getApplicationContext(),"selecionar profesor ",Toast.LENGTH_SHORT ).show();
                 } else {


                     Intent intent = new Intent(Listaprofesor.this, Registroestudiante.class);

                     Bundle bundle = new Bundle();
                     bundle.putString("docu", nuevodoc.getText().toString());

                     intent.putExtras(bundle);
                     startActivity(intent);
                 }

             }
         });





       buscarprofe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Thread tr = new Thread(){

                   @Override
                   public void run() {
                     final String resp=  buscarprofe(docprofe.getText().toString());
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
       });



  seleccionarprofe.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

              nuevodoc.setText(docuprofesor);
                  nuevonom.setText(nombreprofe);
                  continuar.setEnabled(true);

      }
  });

    }



    public String buscarprofe(String doc) {
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


        } catch (Exception e) {
            respuesta=" no existe";
        }

        return respuesta.toString();


    }

    public void listarprofe (String respuesta){
        listausuario= new ArrayList<Usuarios>();

       try{
         JSONArray jsonarreglo = new JSONArray(respuesta);
         for (int i = 0;i<jsonarreglo.length();i++){
           Usuarios u = new Usuarios();
           u.setDocumeto(jsonarreglo.getJSONObject(i).getString("documento"));
           u.setNombre(jsonarreglo.getJSONObject(i).getString("nombre"));
           u.setApellido(jsonarreglo.getJSONObject(i).getString("apellido"));
            listausuario.add(u);
             seleccionarprofe.setEnabled(true);

         }
          obtenerlista();
       }catch (Exception e){ e.printStackTrace();
       }

    }







/*

    private void consultarpersonas( ) {

        SQLiteDatabase db = conn.getReadableDatabase();
        Usuarios usuarios = null;
        listausuario= new ArrayList<Usuarios>();

        Cursor cursor =db.rawQuery("select * from profesor",null);
        while (cursor.moveToLast()){
            usuarios= new Usuarios();
            usuarios.setDocumeto(cursor.getString(0));
            usuarios.setNombre(cursor.getString(1));
            usuarios.setApellido(cursor.getString(2));

            listausuario.add(usuarios);
        }



    }



*/

    private void obtenerlista() {

        listainformacion= new ArrayList<String>();

        if (listausuario.size() == 1){
            for (int i =0; i<listausuario.size();i++){
                listainformacion.add(" Documento : "+listausuario.get(i).documeto + "  Nombre : "+listausuario.get(i).nombre+"  Apellido : "+listausuario.get(i).apellido);

                docuprofesor=listausuario.get(0).documeto;
                nombreprofe=listausuario.get(0).nombre;

                seleccionarprofe.setEnabled(true);


            }


        }else {
            listainformacion.add(" usuario no existe");
            seleccionarprofe.setEnabled(false);


        }

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listaprofesor.setAdapter(adaptador);


    }


}
