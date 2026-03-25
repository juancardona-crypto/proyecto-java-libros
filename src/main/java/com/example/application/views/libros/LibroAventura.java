package com.example.application.views.libros;

public class LibroAventura extends Libro {

    String lugarAventura;

    public LibroAventura(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String lugarAventura, String imagenUrl) {
        super(titulo, id, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.lugarAventura = lugarAventura;
    }

    public String getLugarAventura() {
        return lugarAventura;
    }

    public void setLugarAventura(String lugarAventura) {
        this.lugarAventura = lugarAventura;
    }

    @Override
    public String descripcionLibro() {
        return "";
    }
}