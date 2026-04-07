package com.example.application.views.libros;

public class LibroFantasia extends Libro {

    private String tipoMundo;

    public LibroFantasia(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                         String tipoMundo, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
        this.tipoMundo = tipoMundo;
    }

    public String getTipoMundo() {
        return tipoMundo;
    }

    public void setTipoMundo(String tipoMundo) {
        this.tipoMundo = tipoMundo;
    }

    @Override
    public String descripcionLibro() {
        return "Libro de fantasía ambientado en un mundo " + tipoMundo + ".";
    }
}