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

    Libro Tormenta = new LibroAventura("Tormenta De Espadas", "Fantasía", "George R.R. Martin", 1127, 120000, "Medieval", "https://www.penguinlibros.com/ar/1640618/tormenta-de-espadas-cancion-de-hielo-y-fuego-3.jpg");
    

    public List<Libro> obtenerTodos() {
        libros.add(Tormenta);
        return new ArrayList<>(libros);
    }
    

    public List<Libro> obtenerPorGenero(String genero) {
        return libros.stream()
                .filter(l -> l.getGenero().equalsIgnoreCase(genero))
                .toList();
    }

    public Libro buscarPorId(long id) {
        return libros.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean comprarLibro(long id) {
        Libro libro = buscarPorId(id);

        if (libro != null) {
            libros.remove(libro);
            return true;
        }

        return false;
    }
}