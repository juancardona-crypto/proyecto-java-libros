package com.example.application.views.libros;

public class LibroRomance extends Libro {

    private String tipoRomance;

    public LibroRomance(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                        String tipoRomance, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
        this.tipoRomance = tipoRomance;
    }

    public String getTipoRomance() {
        return tipoRomance;
    }

    public void setTipoRomance(String tipoRomance) {
        this.tipoRomance = tipoRomance;
    }

    @Override
    public String descripcionLibro() {
        return "Libro de romance de tipo " + tipoRomance + ".";
    }
}