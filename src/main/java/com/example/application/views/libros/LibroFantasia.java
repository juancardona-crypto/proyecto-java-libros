package com.example.application.views.libros;

public class LibroFantasia extends Libro {

    String tipoMundo;
    

    public LibroFantasia(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio, String tipoMundo, String imagenUrl){
        super(titulo, id, genero, autor, cantidadPaginas, precio, imagenUrl);
        this.tipoMundo = tipoMundo;
        
    }

    public String getTipoMundo() {
        return tipoMundo;
    }

    public void setTipoMundo(String tipoMundo) {
        this.tipoMundo = tipoMundo;
    }


    @Override
    public String descripcionLibro(){
        return "";
    }
}
