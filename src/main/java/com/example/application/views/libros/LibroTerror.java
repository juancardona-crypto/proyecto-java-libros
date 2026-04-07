package com.example.application.views.libros;

public class LibroTerror extends Libro {

    private String tipoTerror;

    public LibroTerror(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                       String tipoTerror, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
        this.tipoTerror = tipoTerror;
    }

    public String getTipoTerror() {
        return tipoTerror;
    }

    public void setTipoTerror(String tipoTerror) {
        this.tipoTerror = tipoTerror;
    }

    @Override
    public String descripcionLibro() {
        return "Libro de terror de tipo " + tipoTerror + ".";
    }
}