package com.example.application.views.libros;

public class LibroAventura extends Libro {

    private String lugarAventura;

    public LibroAventura(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                         String lugarAventura, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
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
        return "Libro de aventura ambientado en " + lugarAventura + ".";
    }
}