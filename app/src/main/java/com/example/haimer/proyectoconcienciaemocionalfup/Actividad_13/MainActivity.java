package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_13;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

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



import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements  Response.Listener<JSONObject>,Response.ErrorListener {


   public static   Ipconexion ipconexion;
    static RequestQueue requestQueue;


 static    JsonObjectRequest jsonObjectRequest;
   static   ArrayList<Sesionestudiante> listausuario;
   static   DatabaseHelper conn;

    private static GestureDetectGridView mGridView;

   static   String horainicio = "";
    public static String horafin="";

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private static String[] tileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        ipconexion = new Ipconexion();
        conn = new DatabaseHelper(this);
        Date date = new Date();

        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        horainicio =hourFormat.format(date);


        init();

        scramble();

        setDimensions();

    }


    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.fila11);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.fila12);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.fila13);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.fila21);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.fila22);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.fila23);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.fila31);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.fila32);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.fila33);

            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static   void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) {


            Toast.makeText(context, "GANASTE !", Toast.LENGTH_SHORT).show();
            Date date = new Date();

            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

            horafin = String.valueOf(hourFormat.format(date));

            String hoja ="hoja de block";
            String docuencontrado= consultarsesion();
            String fecha= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

              MainActivity  mainActivity = new MainActivity();
              mainActivity.insertaractiviada13("http://"+ipconexion.getIp()+"/dbandroid/WS/insertactividad13.php?documento="+docuencontrado+"&horainicio="+horainicio+"&horafin="+horafin+"&fecha="+fecha+"");

              //  mainActivity.cambiar();
        }
    }

    public  static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }





    public  void insertaractiviada13(String urll){

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


    private  static String consultarsesion( ) {

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



    public  void  cambiar (){
        Intent intent = new Intent(MainActivity.this, Principalestudiantes.class);
        startActivity(intent);

    }
}
