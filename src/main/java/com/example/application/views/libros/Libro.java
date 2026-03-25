package com.example.application.views.libros;

public abstract class Libro {
    String titulo;
    String id;
    String genero;
    String autor;
    int cantidadPaginas;
    double precio;
    String imagenUrl;
    public Libro(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String imagenUrl){
        this.titulo = titulo;
        this.id = id;
        this.genero = genero;
        this.autor = autor;
        this.cantidadPaginas = cantidadPaginas;
        this.precio = precio;
        this.imagenUrl = imagenUrl;


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

    public String getImagenUrl() {
    return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
    public abstract String descripcionLibro();

}
