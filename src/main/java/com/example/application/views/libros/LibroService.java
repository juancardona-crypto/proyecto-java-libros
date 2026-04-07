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

        Libro tormenta = new LibroAventura(
                "Tormenta de Espadas",
                "Fantasía",
                "George R.R. Martin",
                1127,
                120000,
                "Medieval con batallas épicas",
                "https://www.penguinlibros.com/ar/1640618/tormenta-de-espadas-cancion-de-hielo-y-fuego-3.jpg", 10);

        Libro nombreDelViento = new LibroFantasia(
                "El Nombre del Viento",
                "Fantasía",
                "Patrick Rothfuss",
                662,
                28900,
                "Medieval",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFlQSeDrbCPJWP6cbwUede8QwhyYTvxFSkqz7ful48oR2EU6I4JYpkzvykZrr9JVe8l67Lv-ae6xCcHzlihu0fcxOWpYFUIzHnSgtIdBU&s=10", 8);
    

        Libro dune = new LibroCienciaFiccion(
                "Dune", "Ciencia Ficción", "Frank Herbert",
                412, 32500, "Distópico en Arrakis",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_M-tIOhEDno97-IfHWc_G3g5GwAfL3Em4Cw&s", 6);

        Libro it = new LibroTerror(
                "It", "Terror", "Stephen King",
                1138, 24500, "Sobrenatural",
                "https://upload.wikimedia.org/wikipedia/commons/1/1a/It_%281986%29_front_cover%2C_first_edition.jpg", 4);

        Libro orgullo = new LibroRomance(
                "Orgullo y Prejuicio", "Romance", "Jane Austen",
                432, 18500, "Histórico",
                "https://editorialverbum.es/wp-content/uploads/2016/11/orgulloyprejuicio.jpg", 7);

        Libro jobs = new LibroBiografia(
                "Steve Jobs", "Biografía", "Walter Isaacson",
                656, 27500, "Steve Jobs",
                "https://http2.mlstatic.com/D_NQ_NP_986547-MLU44860530711_022021-O.webp", 3);

        Libro resplandor = new LibroTerror(
                "El Resplandor", "Terror", "Stephen King",
                447, 21900, "Psicológico",
                "https://m.media-amazon.com/images/M/MV5BYmUxZDU3NjktMzA1OS00OGUwLWJkOTctYzhjOGI5MTcyY2U3XkEyXkFqcGc@._V1_.jpg", 4);

        Libro islaDelTesoro = new LibroAventura(
                "La Isla del Tesoro", "Aventura", "Robert Louis Stevenson",
                292, 18500, "Mar y piratas",
                "https://static.wikia.nocookie.net/makebooks/images/3/3f/Isla.jpg/revision/latest?cb=20170524151959&path-prefix=es", 5);

        Libro tomSawyer = new LibroAventura(
                "Las Aventuras de Tom Sawyer", "Aventura", "Mark Twain",
                224, 16900, "Río Mississippi",
                "https://image.cdn0.buscalibre.com/3532444.__RS360x360__.jpg", 6);

        Libro viajeCentroTierra = new LibroAventura(
                "Viaje al Centro de la Tierra", "Aventura", "Julio Verne",
                320, 19800, "Subterráneo",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFpoWM4h-6wUiR-Pm8O0MCtXe8C7QeSrRAkQ&s", 5);

        Libro neuromante = new LibroCienciaFiccion(
                "Neuromante", "Ciencia Ficción", "William Gibson",
                271, 23400, "Ciberpunk",
                "https://contentv2.tap-commerce.com/cover/large/9789505472406_1.jpg?id_com=1156", 3);

        Libro silencioInocentes = new LibroThriller(
                "El Silencio de los Inocentes", "Thriller", "Thomas Harris",
                421, 23900, "Psicológico",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9rbXEDj5lbeyCwGq2qtBEDOgptHrMT-I6EA&s", 4);

        Libro chicaDragon = new LibroThriller(
                "La Chica del Dragón Tatuado", "Thriller", "Stieg Larsson",
                544, 26500, "Investigación",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLmTcLIWOXPrZypNC2f__CgNKi5vS8y8UIkA&s", 4);

        Libro codigoDaVinci = new LibroThriller(
                "El Código Da Vinci", "Thriller", "Dan Brown",
                489, 22800, "Conspiración",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw48XZ61oSH0xXpFvAZl18dgShWMhwjHqboQ&s", 6);

        Libro mismaEstrella = new LibroRomance(
                "Bajo la Misma Estrella", "Romance", "John Green",
                313, 19500, "Contemporáneo",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-E4jWPIEfzH_fBJQb296WmHFtgAWIo_7CEA&s", 7);

        Libro diarioAnaFrank = new LibroBiografia(
                "El Diario de Ana Frank", "Biografía", "Ana Frank",
                283, 15800, "Ana Frank",
                "https://www.libreriacasatomada.com/imagenes/9786287/978628773243.GIF", 5);


        agregarLibro(it);
        agregarLibro(resplandor);
        agregarLibro(islaDelTesoro);
        agregarLibro(tomSawyer);
        agregarLibro(viajeCentroTierra);
        agregarLibro(dune);
        agregarLibro(neuromante);
        agregarLibro(silencioInocentes);
        agregarLibro(chicaDragon);
        agregarLibro(codigoDaVinci);
        agregarLibro(mismaEstrella);
        agregarLibro(jobs);
        agregarLibro(diarioAnaFrank);
        agregarLibro(tormenta);
        agregarLibro(nombreDelViento);
        agregarLibro(orgullo);
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

    public void eliminarLibrosSinStock() {
        libros.removeIf(libro -> libro.getStock() <= 0);
    }
}