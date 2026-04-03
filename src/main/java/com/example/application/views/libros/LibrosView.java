package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
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

    private final TextField titulo = new TextField("Título");
    private final TextField autor = new TextField("Autor");
    private final IntegerField cantidadPaginas = new IntegerField("Cantidad de páginas");
    private final NumberField precio = new NumberField("Precio");
    private final ComboBox<String> genero = new ComboBox<>("Género");
    private final TextField campoEspecifico = new TextField();
    private final TextField imagenUrl = new TextField("URL de la imagen (opcional(obligatorio))");
    private final FormLayout formLayout = new FormLayout();

    private final FlexLayout catalogoGrid = new FlexLayout();

    private final LibroService libroService;

    public LibrosView(LibroService libroService) {
        this.libroService = libroService;

        setPadding(true);
        setSpacing(true);

        // Título principal
        H2 tituloPagina = new H2("📚 Librería Online");
        tituloPagina.getStyle()
            .set("font-size", "2.2rem")
            .set("font-weight", "800")
            .set("margin-bottom", "0");

        // Grid de tarjetas
        catalogoGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        catalogoGrid.getStyle()
            .set("gap", "24px")
            .set("padding", "16px 0");

        // Menú de géneros
        H3 subtitulo = new H3("Explorar por género:");
        subtitulo.getStyle().set("margin-top", "24px");
        HorizontalLayout menuGeneros = construirMenuGeneros();

        // Formulario
        Div formCard = new Div();
        formCard.getStyle()
            .set("background", "var(--lumo-contrast-5pct)")
            .set("border-radius", "16px")
            .set("padding", "24px")
            .set("margin-top", "32px")
            .set("width", "100%");

        H3 tituloFormulario = new H3("📝 Publicar un libro:");
        tituloFormulario.getStyle().set("margin-top", "0");
        construirFormulario();

        Button btnGuardar = new Button("Publicar libro", e -> guardarLibro());
        btnGuardar.getStyle()
            .set("margin-top", "16px")
            .set("background", "#4f46e5")
            .set("color", "white")
            .set("border-radius", "8px")
            .set("font-weight", "700")
            .set("padding", "10px 24px");

        formCard.add(tituloFormulario, formLayout, btnGuardar);

        mostrarTodosLosLibros();

        add(tituloPagina, subtitulo, menuGeneros, catalogoGrid, formCard);

        inyectarEstilos();
    }

    // -------------------------------------------------------
    // MENÚ DE GÉNEROS
    // -------------------------------------------------------
    private HorizontalLayout construirMenuGeneros() {
        HorizontalLayout menu = new HorizontalLayout();
        menu.setSpacing(true);
        menu.getStyle().set("flex-wrap", "wrap");

        String[] generos = {"Fantasía", "Ciencia Ficción", "Aventura", "Thriller", "Terror", "Romance", "Biografía"};

        for (String g : generos) {
            Button btn = new Button(g, e -> mostrarLibrosPorGenero(g));
            btn.getStyle().set("border-radius", "20px").set("font-weight", "600");
            menu.add(btn);
        }

        Button btnTodos = new Button("🔎 Ver todos", e -> mostrarTodosLosLibros());
        btnTodos.getStyle()
            .set("border-radius", "20px")
            .set("background", "#4f46e5")
            .set("color", "white")
            .set("font-weight", "700");
        menu.add(btnTodos);

        return menu;
    }

    private void mostrarLibrosPorGenero(String generoFiltro) {
        catalogoGrid.removeAll();
        List<Libro> filtrados = libroService.obtenerPorGenero(generoFiltro);

        if (filtrados.isEmpty()) {
            Div vacio = new Div();
            vacio.setText("No hay libros de " + generoFiltro + " todavía.");
            vacio.getStyle()
                .set("color", "var(--lumo-secondary-text-color)")
                .set("padding", "24px")
                .set("font-style", "italic");
            catalogoGrid.add(vacio);
            return;
        }

        for (Libro libro : filtrados) {
            catalogoGrid.add(new LibroCard(libro));
        }
    }

    private void mostrarTodosLosLibros() {
        catalogoGrid.removeAll();
        List<Libro> todos = libroService.obtenerTodos();

        if (todos.isEmpty()) {
            Div vacio = new Div();
            vacio.setText("No hay libros publicados todavía. ¡Sé el primero en publicar!");
            vacio.getStyle()
                .set("color", "var(--lumo-secondary-text-color)")
                .set("padding", "24px")
                .set("font-style", "italic");
            catalogoGrid.add(vacio);
            return;
        }

        for (Libro libro : todos) {
            catalogoGrid.add(new LibroCard(libro));
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
        formLayout.add(titulo, autor, cantidadPaginas, precio, genero, campoEspecifico, imagenUrl);
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
        if (generoVal == null || titulo.isEmpty() || autor.isEmpty() ||
    cantidadPaginas.getValue() == null || precio.getValue() == null) {
            Notification.show("Por favor completá todos los campos obligatorios.");
            return;
        }

        String especifico = campoEspecifico.getValue();

        Libro libro = switch (generoVal) {
            case "Fantasía"        -> new LibroFantasia(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Ciencia Ficción" -> new LibroCienciaFiccion(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Aventura"        -> new LibroAventura(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Thriller"        -> new LibroThriller(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Terror"          -> new LibroTerror(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Romance"         -> new LibroRomance(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            case "Biografía"       -> new LibroBiografia(titulo.getValue(), generoVal, autor.getValue(), cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue());
            default -> {
                Notification.show("Género aún no disponible, será agregado próximamente.");
                yield null;
            }
        };

        if (libro != null) {
            libroService.agregarLibro(libro);
            Notification.show("✅ Libro publicado correctamente.");
            limpiarFormulario();
            mostrarTodosLosLibros();
        }
    }

    private void limpiarFormulario() {
        titulo.clear();
        autor.clear();
        cantidadPaginas.clear();
        precio.clear();
        genero.clear();
        campoEspecifico.clear();
        campoEspecifico.setVisible(false);
        imagenUrl.clear();
    }

    // -------------------------------------------------------
    // ESTILOS CSS
    // -------------------------------------------------------
    private void inyectarEstilos() {
    getElement().executeJs(
        "const style = document.createElement('style');" +
        "style.textContent = `" +
        "  .libro-card { ... tus estilos actuales ... } " + 

        "  .genero-badge {" +
        "    background: var(--lumo-primary-color);" +
        "    color: white;" +
        "    padding: 4px 12px;" +
        "    border-radius: 9999px;" +
        "    font-size: 0.8rem;" +
        "    font-weight: 600;" +
        "    align-self: flex-start;" +
        "  }" +
        "  .libro-titulo { margin: 8px 0 4px 0; font-size: 1.1rem; }" +
        "  .libro-autor { color: var(--lumo-secondary-text-color); margin: 0; }" +
        "  .libro-info-row { gap: 8px; }" +
        "  .libro-info-chip {" +
        "    background: var(--lumo-contrast-10pct);" +
        "    padding: 2px 10px;" +
        "    border-radius: 9999px;" +
        "    font-size: 0.85rem;" +
        "  }" +
        "  .libro-precio-row { margin-top: auto; display: flex; justify-content: space-between; align-items: center; }" +
        "  .libro-precio { font-size: 1.3rem; font-weight: 700; color: var(--lumo-success-color); }" +
        "`;" +
        "document.head.appendChild(style);"
    );
}
}