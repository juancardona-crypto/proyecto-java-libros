package com.example.application.views.libros;


public abstract class Terror extends Libro {

     String tipoTerror;

    public Terror(String titulo, String id, String genero, String autor, int cantidadPaginas, double precio) {
        super(titulo, id, genero, autor, cantidadPaginas, precio);
        this.tipoTerror = tipoTerror;
        
    }
      public String getTipoMundo() {
        return tipoTerror;
    }

    public void setTipoMundo(String tipoTerror) {
        this.tipoTerror = tipoTerror;
    }


    @Override
    public abstract String descripcionLibro();
}
