package com.example.application.views.libros;

public class LibroRomance extends Libro {

    String sentimiento;

    public LibroRomance(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String sentimiento, String imagenUrl){
        super(titulo, id, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.sentimiento = sentimiento;
    }

    public String getPensamiento(){
        return sentimiento;
    }

    public void setSentimiento(String sentimiento){
        this.sentimiento = sentimiento;
    }

    @Override
    public String descripcionLibro(){
        return "";
    }
}
