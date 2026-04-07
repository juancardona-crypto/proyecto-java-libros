package com.example.application.views.libros;

public class LibroCienciaFiccion extends Libro {

    private String ambientacion;

    public LibroCienciaFiccion(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                               String ambientacion, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
        this.ambientacion = ambientacion;
    }

    public String getAmbientacion() {
        return ambientacion;
    }

    public void setAmbientacion(String ambientacion) {
        this.ambientacion = ambientacion;
    }

    @Override
    public String descripcionLibro() {
        return "Libro de ciencia ficción con ambientación " + ambientacion + ".";
    }
}