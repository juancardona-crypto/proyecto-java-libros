package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.List;

@PageTitle("Libros")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class LibrosView extends VerticalLayout {

    // Campos del formulario
    private final TextField titulo = new TextField("Título");
    private final TextField id = new TextField("ID");
    private final TextField autor = new TextField("Autor");
    private final IntegerField cantidadPaginas = new IntegerField("Cantidad de páginas");
    private final NumberField precio = new NumberField("Precio");
    private final ComboBox<String> genero = new ComboBox<>("Género");
    private final TextField campoEspecifico = new TextField();
    private final FormLayout formLayout = new FormLayout();

    // Área donde se muestran los libros filtrados
    private final VerticalLayout catalogoLayout = new VerticalLayout();

    private final LibroService libroService;

    public LibrosView(LibroService libroService) {
        this.libroService = libroService;

        // --- TÍTULO PRINCIPAL ---
        H2 titulo_pagina = new H2("📚 Librería Online");

        // --- MENÚ DE GÉNEROS (botones) ---
        H3 subtitulo = new H3("Explorar por género:");
        HorizontalLayout menuGeneros = construirMenuGeneros();

        // --- FORMULARIO DE CARGA ---
        H3 tituloFormulario = new H3("Publicar un libro:");
        construirFormulario();

        Button btnGuardar = new Button("Publicar libro", e -> guardarLibro());

        add(titulo_pagina, subtitulo, menuGeneros, catalogoLayout, tituloFormulario, formLayout, btnGuardar);
    }

    // -------------------------------------------------------
    // MENÚ DE GÉNEROS
    // -------------------------------------------------------
    private HorizontalLayout construirMenuGeneros() {
        HorizontalLayout menu = new HorizontalLayout();
        menu.setSpacing(true);

        String[] generos = {"Fantasía", "Ciencia Ficción", "Aventura", "Thriller", "Terror", "Romance", "Biografía"};

        for (String g : generos) {
            Button btn = new Button(g, e -> mostrarLibrosPorGenero(g));
            menu.add(btn);
        }

        // Botón para ver todos
        Button btnTodos = new Button("Ver todos", e -> mostrarTodosLosLibros());
        menu.add(btnTodos);

        return menu;
    }

    private void mostrarLibrosPorGenero(String generoFiltro) {
        catalogoLayout.removeAll();
        List<Libro> filtrados = libroService.obtenerPorGenero(generoFiltro);

        if (filtrados.isEmpty()) {
            catalogoLayout.add(new H3("No hay libros de " + generoFiltro + " todavía."));
            return;
        }

        catalogoLayout.add(new H3("Género: " + generoFiltro));
        for (Libro libro : filtrados) {
            catalogoLayout.add(new com.vaadin.flow.component.html.Paragraph(
                "📖 " + libro.getTitulo() + " — " + libro.getAutor() + " | $" + libro.getPrecio()
            ));
        }
    }

    private void mostrarTodosLosLibros() {
        catalogoLayout.removeAll();
        List<Libro> todos = libroService.obtenerTodos();

        if (todos.isEmpty()) {
            catalogoLayout.add(new H3("No hay libros cargados todavía."));
            return;
        }

        catalogoLayout.add(new H3("Todos los libros:"));
        for (Libro libro : todos) {
            catalogoLayout.add(new com.vaadin.flow.component.html.Paragraph(
                "📖 " + libro.getTitulo() + " — " + libro.getAutor() + " | " + libro.getGenero() + " | $" + libro.getPrecio()
            ));
        }
    }

    // -------------------------------------------------------
    // FORMULARIO
    // -------------------------------------------------------
    private void construirFormulario() {
        genero.setItems(
            "Fantasía", "Ciencia Ficción", "Aventura", "Thriller",
            "Terror", "Romance", "Biografía"
        );
        genero.addValueChangeListener(e -> actualizarCampoEspecifico(e.getValue()));
        campoEspecifico.setVisible(false);
        formLayout.add(titulo, id, autor, cantidadPaginas, precio, genero, campoEspecifico);
    }

    private void actualizarCampoEspecifico(String generoSeleccionado) {
        if (generoSeleccionado == null) {
            campoEspecifico.setVisible(false);
            return;
        }
        campoEspecifico.setVisible(true);
        switch (generoSeleccionado) {
            case "Fantasía"        -> campoEspecifico.setLabel("Tipo de mundo (ej: Medieval, Épico)");
            case "Ciencia Ficción" -> campoEspecifico.setLabel("Ambientación (ej: Espacial, Distópico)");
            case "Aventura"        -> campoEspecifico.setLabel("Lugar de aventura (ej: Selva, Mar)");
            case "Thriller"        -> campoEspecifico.setLabel("Tipo de thriller (ej: Psicológico, Espionaje)");
            case "Terror"          -> campoEspecifico.setLabel("Tipo de terror (ej: Sobrenatural, Gore)");
            case "Romance"         -> campoEspecifico.setLabel("Tipo de romance (ej: Contemporáneo, Histórico)");
            case "Biografía"       -> campoEspecifico.setLabel("Personaje biografiado");
        }
    }

    private void guardarLibro() {
        String generoVal = genero.getValue();
        if (generoVal == null || titulo.isEmpty() || autor.isEmpty()) {
            Notification.show("Por favor completá todos los campos obligatorios.");
            return;
        }

        String especifico = campoEspecifico.getValue();

        Libro libro = switch (generoVal) {
            case "Fantasía"        -> new LibroFantasia(titulo.getValue(), id.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico);
            case "Ciencia Ficción" -> new LibroCienciaFiccion(titulo.getValue(), id.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico);
            case "Aventura"        -> new LibroAventura(titulo.getValue(), id.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico);
            case "Thriller"        -> new LibroThriller(titulo.getValue(), id.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico);
            default -> {
                Notification.show("Género aún no disponible, será agregado próximamente.");
                yield null;
            }
        };

        if (libro != null) {
            libroService.agregarLibro(libro);
            Notification.show("✅ Libro publicado correctamente.");
            limpiarFormulario();
        }
    }

    private void limpiarFormulario() {
        titulo.clear();
        id.clear();
        autor.clear();
        cantidadPaginas.clear();
        precio.clear();
        genero.clear();
        campoEspecifico.clear();
        campoEspecifico.setVisible(false);
        catalogoLayout.removeAll();
    }
}
