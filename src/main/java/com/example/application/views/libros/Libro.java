package com.example.application.views.libros;

public abstract class Libro implements GestionLibro {
    private String titulo;
    private long id;
    private String genero;
    private String autor;
    private int cantidadPaginas;
    private double precio;
    private String imagenUrl;
    private int stock;

    public Libro(String titulo, String genero, String autor, int cantidadPaginas, double precio, String imagenUrl, int stock) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.cantidadPaginas = cantidadPaginas;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.stock = stock;
    }

    public Libro(String titulo, String autor, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.genero = "No definido";
        this.cantidadPaginas = 0;
        this.imagenUrl = "";
        this.stock = 0;
    }

    public String obtenerFichaTecnica(boolean incluirImagen) {
        String ficha = obtenerFichaTecnica();

        if (incluirImagen && imagenUrl != null && !imagenUrl.isEmpty()) {
            ficha += " | Imagen: " + imagenUrl;
        }

        return ficha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String obtenerFichaTecnica() {
        return "Título: " + titulo +
               " | Autor: " + autor +
               " | Género: " + genero +
               " | Páginas: " + cantidadPaginas +
               " | Precio: $" + precio +
               " | Stock: " + stock;
    }

    @Override
    public abstract String descripcionLibro();
}