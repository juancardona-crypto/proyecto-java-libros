package com.example.application.views.libros;

public class LibroThriller extends Libro {

    String tipoThriller;

    public LibroThriller(String titulo, String genero, String autor, int cantidadPaginas, double precio, String tipoThriller, String imagenUrl ){
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl);
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