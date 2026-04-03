package com.example.application.views.libros;

import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private long nextId = 1;
    public void agregarLibro(Libro libro) {
        libro.setId(nextId++);
        libros.add(libro);
    }

    public List<Libro> obtenerTodos() {
        return new ArrayList<>(libros);
    }

    public List<Libro> obtenerPorGenero(String genero) {
        return libros.stream()
                .filter(l -> l.getGenero().equalsIgnoreCase(genero))
                .toList();
    }
}