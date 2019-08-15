package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.haimer.proyectoconcienciaemocionalfup.DatabaseHelper;
import com.example.haimer.proyectoconcienciaemocionalfup.Ipconexion;
import com.example.haimer.proyectoconcienciaemocionalfup.Principalestudiantes;
import com.example.haimer.proyectoconcienciaemocionalfup.R;
import com.example.haimer.proyectoconcienciaemocionalfup.Usuarios;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Seleccionarimagen extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    private ImageView alegria,imgtocar ,triste,ira ;
    private  int modificarX=200;
    private  int modificary=200;
    private  int  ancho = 200;
    private int alto =200;
    float inicialtocarx = 0;
    float inicialtocary=0;
    float inicialmux= 0;
    float inicialmuy=0;
    float inicialx = 0;
    float inicialy = 0;
    float inicialtristex = 0;
    float inicialtristey = 0;
    float inicialirax = 0;
    float inicialiray = 0;

    RequestQueue requestQueue;
    ArrayList<Sesionestudiante> listausuario;


    JsonObjectRequest jsonObjectRequest;
    Ipconexion ipconexion;
    DatabaseHelper conn;

    String dia ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionarimagen);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            dia = bundle.getString("docu");

        }


        alegria=findViewById(R.id.imagenalegria);
        imgtocar= findViewById(R.id.imagentocar);
        triste=findViewById(R.id.tristeimagen);
        ira=findViewById(R.id.imagenira);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);

        alegria.setOnTouchListener(handlerMover);
        triste.setOnTouchListener(handlerMovertriste);
        ira.setOnTouchListener(handlerMoverira);

    }


    View.OnTouchListener handlerMover;

    {
        handlerMover = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {



                PointF DownPT = new PointF();
                PointF StartPT = new PointF();
                int eid = event.getAction();
                PointF mv;



                switch (eid) {



                    case MotionEvent.ACTION_MOVE:

                        //obtenemos la posicion del dedo
                        StartPT = new PointF(v.getX(), v.getY());
                        // calculamos el desplazamiento
                        mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);

                        // para que la imagen este centrada al dedo
                        v.setX((StartPT.x + mv.x) - modificarX);
                        v.setY((StartPT.y + mv.y) - modificary);

                        v.setX((StartPT.x + mv.x) - modificarX);

                        inicialmux = (StartPT.x + mv.x) - modificarX;
                        inicialmuy = (StartPT.y + mv.y) - modificary;


                        break;

                    case MotionEvent.ACTION_DOWN:
                        posimgtocar(imgtocar);
                        posinicialesalegria(alegria);

                        //guardamos las posiciones iniciales
                        StartPT = new PointF(v.getX(), v.getY());


                        DownPT.x = event.getX();
                        DownPT.y = event.getY();




                        break;

                    case MotionEvent.ACTION_UP:
                        boolean  colicion = colicion();

                        if (colicion == true) {

                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialtocarx));
                            v.setY((inicialtocary ));
                            imgtocar.setVisibility(View.INVISIBLE);

                            String docuencontrado= consultarsesion();
                            String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                             insertaractividad2("http://"+ipconexion.getIp()+"/dbandroid/WS/insertactividad2.php?documento="+docuencontrado+"&estadoactividad=feliz&diasemana="+dia+"&fecha="+fecha+"");


                            Intent intent = new Intent(Seleccionarimagen.this, Principalestudiantes.class);
                            startActivity(intent);



                        } else if(v.getX() != inicialx && v.getY() != inicialy){

                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialx +mv.x)-modificarX);
                            v.setY((inicialy +mv.y)-modificary);
                            imgtocar.setVisibility(View.VISIBLE);
                        }



                        break;
                }
                return true;
            }
        };
    }


    public  void posimgtocar (View v){
        PointF Postocar = new PointF();

        Postocar= new PointF(v.getX(),v.getY());

        inicialtocarx = Postocar.x;
        inicialtocary=Postocar.y;

    }
    public  void posinicialesalegria (View vi ){

        PointF posalegria ;



        posalegria= new PointF(vi.getX(),vi.getY());

        if (inicialx == 0.0 && inicialy==0.0 ){

            inicialx = posalegria.x;
            inicialy=posalegria.y;
        }







    }


    public boolean colicion () {


        if ( ((inicialmux >= inicialtocarx && inicialmux <=(inicialtocarx+ancho))&&( inicialmuy>=inicialtocary && inicialmuy<=(inicialtocary+alto)))  ||
                ((inicialmux+ancho>=inicialtocarx&&(inicialmux+ancho)<=(inicialtocarx+ancho))&& inicialmuy>=inicialtocary && inicialmuy<=(inicialtocary+alto)  )
               || ((inicialmux >= inicialtocarx && inicialmux <=(inicialtocarx+ancho))&&( (inicialmuy+alto)>=inicialtocary && (inicialmuy+alto)<=(inicialtocary+alto)))
        || (((inicialmux+ancho) >= inicialtocarx &&( inicialmux +ancho)<=(inicialtocarx+ancho))&&( (inicialmuy+alto)>= (inicialtocary )&& (inicialmuy+alto)<=(inicialtocary+alto)))    ) {
            return true;

        }else {
            return false;
        }





    }

    //  acciones de imagen  triste
    View.OnTouchListener handlerMovertriste;

    {
        handlerMovertriste = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                PointF DownPT = new PointF();
                PointF StartPT = new PointF();
                int eid = event.getAction();
                PointF mv;



                switch (eid) {



                    case MotionEvent.ACTION_MOVE:

                        //obtenemos la posicion del dedo
                        StartPT = new PointF(v.getX(), v.getY());
                        // calculamos el desplazamiento
                        mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);

                        // para que la imagen este centrada al dedo
                        v.setX((StartPT.x + mv.x) - modificarX);
                        v.setY((StartPT.y + mv.y) - modificary);

                        v.setX((StartPT.x + mv.x) - modificarX);

                        inicialmux = (StartPT.x + mv.x) - modificarX;
                        inicialmuy = (StartPT.y + mv.y) - modificary;





                        break;

                    case MotionEvent.ACTION_DOWN:
                        posimgtocar(imgtocar);
                        posinicialestriste(triste);

                        //guardamos las posiciones iniciales
                        StartPT = new PointF(v.getX(), v.getY());


                        DownPT.x = event.getX();
                        DownPT.y = event.getY();




                        break;

                    case MotionEvent.ACTION_UP:
                        boolean  colicion = colicion();

                        if (colicion == true) {



                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialtocarx));
                            v.setY((inicialtocary ));


                            String docuencontrado= consultarsesion();
                            String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                            insertaractividad2("http://"+ipconexion.getIp()+"/dbandroid/WS/insertactividad2.php?documento="+docuencontrado+"&estadoactividad=triste&diasemana="+dia+"&fecha="+fecha+"");

                            imgtocar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Seleccionarimagen.this, Principalestudiantes.class);
                            startActivity(intent);


                        } else if(v.getX() != inicialtristex && v.getY() != inicialtristey){

                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialtristex +mv.x)-modificarX);
                            v.setY((inicialtristey +mv.y)-modificary);
                            imgtocar.setVisibility(View.VISIBLE);
                        }



                        break;
                }
                return true;
            }
        };
    }

    public  void posinicialestriste (View vi ) {

        PointF posalegria;


        posalegria = new PointF(vi.getX(), vi.getY());

        if (inicialtristex == 0.0 && inicialtristey == 0.0) {

            inicialtristex= posalegria.x;
            inicialtristey = posalegria.y;
        }


    }


    // acciones de imagen ira


    View.OnTouchListener handlerMoverira;

    {
        handlerMoverira = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                PointF DownPT = new PointF();
                PointF StartPT = new PointF();
                int eid = event.getAction();
                PointF mv;



                switch (eid) {



                    case MotionEvent.ACTION_MOVE:

                        //obtenemos la posicion del dedo
                        StartPT = new PointF(v.getX(), v.getY());
                        // calculamos el desplazamiento
                        mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);

                        // para que la imagen este centrada al dedo
                        v.setX((StartPT.x + mv.x) - modificarX);
                        v.setY((StartPT.y + mv.y) - modificary);

                        v.setX((StartPT.x + mv.x) - modificarX);

                        inicialmux = (StartPT.x + mv.x) - modificarX;
                        inicialmuy = (StartPT.y + mv.y) - modificary;





                        break;

                    case MotionEvent.ACTION_DOWN:
                        posimgtocar(imgtocar);
                        posinicialesira(ira);

                        //guardamos las posiciones iniciales
                        StartPT = new PointF(v.getX(), v.getY());


                        DownPT.x = event.getX();
                        DownPT.y = event.getY();




                        break;

                    case MotionEvent.ACTION_UP:
                        boolean  colicion = colicion();

                        if (colicion == true) {



                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialtocarx));
                            v.setY((inicialtocary ));

                            String docuencontrado= consultarsesion();
                            String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                            insertaractividad2("http://"+ipconexion.getIp()+"/dbandroid/WS/insertactividad2.php?documento="+docuencontrado+"&estadoactividad=ira&diasemana="+dia+"&fecha="+fecha+"");

                            imgtocar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Seleccionarimagen.this, Principalestudiantes.class);
                            startActivity(intent);


                        } else if(v.getX() != inicialirax && v.getY() != inicialiray){

                            mv = new PointF(event.getX() - DownPT.x, event.getY() - DownPT.y);
                            v.setX((inicialirax +mv.x)-modificarX);
                            v.setY((inicialiray +mv.y)-modificary);
                            imgtocar.setVisibility(View.VISIBLE);
                        }



                        break;
                }
                return true;            }
        };
    }


    public  void posinicialesira (View vi ) {

        PointF posalegria;


        posalegria = new PointF(vi.getX(), vi.getY());

        if (inicialirax == 0.0 && inicialiray== 0.0) {

            inicialirax= posalegria.x;
            inicialiray = posalegria.y;
        }


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


    // obtener usuario


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
         doc =listausuario.get(0).documento;
        return  doc;
    }




}
