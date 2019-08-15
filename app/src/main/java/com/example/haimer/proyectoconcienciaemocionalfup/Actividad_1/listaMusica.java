package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

/**
 * Created by Personal on 04/10/2018.
 */

public class listaMusica {

    private String nombre;
    private String cantante;
    private int cancion;

    public listaMusica(String nombre, String cantante, int cancion) {
        this.nombre = nombre;
        this.cantante = cantante;
        this.cancion = cancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public int getCancion() {
        return cancion;
    }

    public void setCancion(int cancion) {
        this.cancion = cancion;
    }
}

