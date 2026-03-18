package com.example.application.views.libros;

public abstract class Fantasia extends Libro {

    String tipoMundo;

    public Fantasia(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio) {
        super(titulo, id, genero, autor, cantidadPaginas, precio);
        this.tipoMundo = tipoMundo;
        
    }

    
    public String getTipoMundo() {
        return tipoMundo;
    }

    public void setTipoMundo(String tipoMundo) {
        this.tipoMundo = tipoMundo;
    }
    
    @Override
    public abstract int precioLibro();
}
