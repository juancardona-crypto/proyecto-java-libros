package com.example.application.views.libros;

public class LibroCienciaFiccion extends Libro {

    String ambientacion;

    public LibroCienciaFiccion(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String ambientacion){
        super(titulo, id, genero, autor, cantidadPaginas, precio);
        this.ambientacion = ambientacion;
    }

    public String getAmbientacion(){
        return ambientacion;
    }

    public void setAmbientacion(String ambientacion){
        this.ambientacion = ambientacion;
    }

    @Override
    public String descripcionLibro(){
        return "";
    }
}
