package com.example.application.views.libros;

import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.PostConstruct;

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

    @PostConstruct
    private void cargarLibrosDeEjemplo() {

    Libro Tormenta = new LibroAventura("Tormenta De Espadas", "Fantasía", "George R.R. Martin", 1127, 120000, "Medieval", "https://www.penguinlibros.com/ar/1640618/tormenta-de-espadas-cancion-de-hielo-y-fuego-3.jpg");
    
    Libro nombreDelViento = new LibroFantasia(
                "El Nombre del Viento", "Fantasía", "Patrick Rothfuss", 662, 28900, "Medieval", "https://acdn-us.mitiendanube.com/stores/004/239/068/products/nombredelviento-e6a8de32101507336f17099277865964-1024-1024.webp");

        Libro dune = new LibroCienciaFiccion( "Dune", "Ciencia Ficción", "Frank Herbert", 412, 32500, "Distópico en Arrakis", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_M-tIOhEDno97-IfHWc_G3g5GwAfL3Em4Cw&s");

        Libro it = new LibroTerror("It", "Terror", "Stephen King", 1138, 24500, "Sobrenatural", "https://upload.wikimedia.org/wikipedia/commons/1/1a/It_%281986%29_front_cover%2C_first_edition.jpg");

        Libro orgullo = new LibroRomance("Orgullo y Prejuicio", "Romance", "Jane Austen", 432, 18500, "Histórico", "https://editorialverbum.es/wp-content/uploads/2016/11/orgulloyprejuicio.jpg");

        Libro jobs = new LibroBiografia( "Steve Jobs", "Biografía", "Walter Isaacson", 656, 27500, "Steve Jobs", "https://http2.mlstatic.com/D_NQ_NP_986547-MLU44860530711_022021-O.webp");

    agregarLibro(Tormenta);
    agregarLibro(nombreDelViento);
        agregarLibro(dune);
        agregarLibro(it);
        agregarLibro(orgullo);
        agregarLibro(jobs);
    }
    public List<Libro> obtenerTodos() {
         
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