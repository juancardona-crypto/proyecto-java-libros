package com.example.application.views.libros;

public class LibroBiografia extends Libro {

    String pensamiento;

    public LibroBiografia(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String imagenUrl , String pensamiento){
        super(titulo, id, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.pensamiento = pensamiento;
    }

    public String getPensamiento(){
        return pensamiento;
    }

    public void setAmbientacion(String pensamiento){
        this.pensamiento = pensamiento;
    }

    @Override
    public String descripcionLibro(){
        return "";
    }
}
