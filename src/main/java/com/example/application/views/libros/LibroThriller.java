package com.example.application.views.libros;

public class LibroThriller extends Libro {

    String tipoThriller;

    public LibroThriller(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String imagenUrl , String tipoThriller){
        super(titulo, id, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.tipoThriller = tipoThriller;
    }

    public String getTipoThriller() {
        return tipoThriller;
    }

    public void setTipoThriller(String tipoThriller) {
        this.tipoThriller = tipoThriller;
    }

    @Override
    public String descripcionLibro() {
        return "";
    }
}