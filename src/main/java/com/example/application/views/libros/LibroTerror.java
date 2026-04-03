package com.example.application.views.libros;


public class LibroTerror extends Libro {
    private String tipoTerror;

    public LibroTerror(String titulo, String genero, String autor,
                       int cantidadPaginas, double precio, String tipoTerror, String imagenUrl) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.tipoTerror = tipoTerror;
    }

    public String getTipoTerror() { return tipoTerror; }
    public void setTipoTerror(String tipoTerror) { this.tipoTerror = tipoTerror; }

    @Override
    public String descripcionLibro() {
        return "Terror de tipo " + tipoTerror + ". ¡Prepárate para no dormir!";
    }
}
