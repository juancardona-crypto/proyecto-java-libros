package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class LibroFormView extends VerticalLayout {

    // Campos comunes
    private final TextField titulo = new TextField("Título");
    private final TextField id = new TextField("ID");
    private final TextField autor = new TextField("Autor");
    private final IntegerField cantidadPaginas = new IntegerField("Cantidad de páginas");
    private final NumberField precio = new NumberField("Precio");
    private final ComboBox<String> genero = new ComboBox<>("Género");

    // Campos dinámicos por género
    private final TextField campoEspecifico = new TextField();

    private final FormLayout formLayout = new FormLayout();
    private final LibroService libroService;

    public LibroFormView(LibroService libroService) {
        this.libroService = libroService;

        genero.setItems(
            "Fantasía", "Ciencia Ficción", "Aventura",
            "Thriller", "Terror", "Romance", "Biografía"
        );

        // Escucha el género elegido y actualiza el campo específico
        genero.addValueChangeListener(e -> actualizarCampoEspecifico(e.getValue()));

        formLayout.add(titulo, id, autor, cantidadPaginas, precio, genero, campoEspecifico);
        campoEspecifico.setVisible(false);

        Button guardar = new Button("Publicar libro", e -> guardarLibro());

        add(formLayout, guardar);
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
            // Agregar el resto cuando tengamos las clases
            default -> null;
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
    }
}