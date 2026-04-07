package com.example.application.views.libros;

public class LibroBiografia extends Libro {

    private String personajeBiografiado;

    public LibroBiografia(String titulo, String genero, String autor, int cantidadPaginas, double precio,
                          String personajeBiografiado, String imagenUrl, int stock) {
        super(titulo, genero, autor, cantidadPaginas, precio, imagenUrl, stock);
        this.personajeBiografiado = personajeBiografiado;
    }

    public String getPersonajeBiografiado() {
        return personajeBiografiado;
    }

    public void setPersonajeBiografiado(String personajeBiografiado) {
        this.personajeBiografiado = personajeBiografiado;
    }

    @Override
    public String descripcionLibro() {
        return "Biografía de " + personajeBiografiado + ".";
    }
}