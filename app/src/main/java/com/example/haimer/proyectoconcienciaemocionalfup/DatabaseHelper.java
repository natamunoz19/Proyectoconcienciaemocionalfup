package com.example.haimer.proyectoconcienciaemocionalfup;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by HAIMER on 14/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "appconcienciaemocional", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("Create table profesor(documento text primary key,nombre text,apellido text,correo text,telefono text,password text)");
        bd.execSQL("create table iniciaronsesionprofe(numero INTEGER PRIMARY KEY   AUTOINCREMENT ,documento text)");
        bd.execSQL("create table iniciaronsesionestudiante(numero INTEGER PRIMARY KEY   AUTOINCREMENT ,documento text)");

        bd.execSQL("Create table estudiante(documento text primary key,nombre text,apellido text,grado text,telefono text,password text,documentoprofesor text , FOREIGN KEY (documentoprofesor) REFERENCES profesor(documento))");
         bd.execSQL("create table actividad2resultado(  documentoni  text , documentopro text  ,estadoemocional text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("drop table if exists estudiante");
        bd.execSQL("drop table if exists profesor ");
        bd.execSQL("drop table if exists iniciaronsesionprofe ");
        bd.execSQL("drop table if exists iniciaronsesionestudiante ");


    }


    public boolean insertsesionestudiante(String documento){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento", documento);
        long ins = bd.insert("iniciaronsesionestudiante", null, contentValues);
        bd.close();
        if (ins == -1) return false;
        else return true;
    }

    public boolean insertsesionprofe(String documento){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento", documento);
        long ins = bd.insert("iniciaronsesionprofe", null, contentValues);
        bd.close();
        if (ins == -1) return false;
        else return true;
    }



    public boolean insert(String docuemnto, String nombre, String apellido,String grado
            , String telefono, String password,String docprofe) {

        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento", docuemnto);
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("grado",grado);
        contentValues.put("telefono", telefono);
        contentValues.put("password", password);
        contentValues.put("documentoprofesor",docprofe);

        long ins = bd.insert("estudiante", null, contentValues);
        bd.close();
        if (ins == -1) return false;
        else return true;
    }

    public boolean insertprofesor(String docuemnto, String nombre, String apellido,String correo
            , String telefono, String password) {

        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("documento", docuemnto);
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("correo",correo);
        contentValues.put("telefono", telefono);
        contentValues.put("password", password);
        long ins = bd.insert("profesor", null, contentValues);
        bd.close();
        if (ins == -1) return false;
        else return true;
    }






    public boolean buscadocumento (String documento){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from estudiante where documento=?",new String[]{documento});
        if (cursor.getCount()>0)return false;
        else return true;

    }


    public boolean buscadocumentoprofesor (String documento){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from profesor where documento=?",new String[]{documento});
        if (cursor.getCount()>0)return false;
        else return true;

    }




    public boolean documentopassword ( String doc ,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from estudiante where documento=? and password=?",new String[]{doc,pass});
        if(cursor.getCount()>0) return  true ;
        else return  false ;
    }


    public boolean documentopasswordprofesor ( String doc ,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from profesor where documento=? and password=?",new String[]{doc,pass});
        if(cursor.getCount()>0) return  true ;
        else return  false ;
    }







}
