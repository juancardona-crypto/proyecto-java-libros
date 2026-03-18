package com.example.application.views.libros;

public abstract class Libro {
    String titulo;
    String id;
    String genero;
    String autor;
    int cantidadPaginas;
    double precio;

    public Libro(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio){
        this.titulo = titulo;
        this.id = id;
        this.genero = genero;
        this.autor = autor;
        this.cantidadPaginas = cantidadPaginas;
        this.precio = precio;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public abstract int precioLibro();

}
