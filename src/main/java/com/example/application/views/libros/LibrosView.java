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
    private final TextField imagenUrl = new TextField("URL de la imagen (opcional)");
    private final FormLayout formLayout = new FormLayout();

    private final FlexLayout catalogoGrid = new FlexLayout();

    private final LibroService libroService;

    public LibrosView(LibroService libroService) {
        this.libroService = libroService;

        setPadding(true);
        setSpacing(true);
        setSizeFull();

        getStyle()
            .set("background", "linear-gradient(135deg, #0c0a09, #1c1917)")
            .set("color", "#f5f5f4")
            .set("min-height", "100vh")
            .set("padding", "24px");

        H2 tituloPagina = new H2("📚 Librería Online");
        tituloPagina.getStyle()
            .set("font-size", "2.4rem")
            .set("font-weight", "800")
            .set("margin-bottom", "0")
            .set("color", "#fafaf9")
            .set("letter-spacing", "0.5px");

        catalogoGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        catalogoGrid.getStyle()
            .set("gap", "24px")
            .set("padding", "16px 0")
            .set("align-items", "stretch");

        H3 subtitulo = new H3("Explorar por género:");
        subtitulo.getStyle()
            .set("margin-top", "24px")
            .set("margin-bottom", "8px")
            .set("color", "#e7e5e4")
            .set("font-weight", "700");

        HorizontalLayout menuGeneros = construirMenuGeneros();

        Div formCard = new Div();
        formCard.getStyle()
            .set("background", "#1c1917")
            .set("border-radius", "18px")
            .set("padding", "24px")
            .set("margin-top", "32px")
            .set("width", "100%")
            .set("border", "1px solid #44403c")
            .set("box-shadow", "0 10px 30px rgba(0,0,0,0.35)");

        H3 tituloFormulario = new H3("📝 Publicar un libro");
        tituloFormulario.getStyle()
            .set("margin-top", "0")
            .set("margin-bottom", "16px")
            .set("color", "#fafaf9")
            .set("font-weight", "700");

        construirFormulario();

        Button btnGuardar = new Button("Publicar libro", e -> guardarLibro());
        btnGuardar.getStyle()
            .set("margin-top", "16px")
            .set("background", "#a16207")
            .set("color", "white")
            .set("border-radius", "10px")
            .set("font-weight", "700")
            .set("padding", "10px 24px")
            .set("border", "none")
            .set("box-shadow", "0 6px 16px rgba(161,98,7,0.35)");

        formCard.add(tituloFormulario, formLayout, btnGuardar);

        mostrarTodosLosLibros();

        add(tituloPagina, subtitulo, menuGeneros, catalogoGrid, formCard);

        inyectarEstilos();
    }

    private HorizontalLayout construirMenuGeneros() {
        HorizontalLayout menu = new HorizontalLayout();
        menu.setSpacing(true);
        menu.getStyle()
            .set("flex-wrap", "wrap")
            .set("gap", "10px");

        String[] generos = {
            "Fantasía", "Ciencia Ficción", "Aventura",
            "Thriller", "Terror", "Romance", "Biografía"
        };

        for (String g : generos) {
            Button btn = new Button(g, e -> mostrarLibrosPorGenero(g));
            btn.getStyle()
                .set("border-radius", "20px")
                .set("font-weight", "600")
                .set("background", "#292524")
                .set("color", "#e7e5e4")
                .set("border", "1px solid #44403c")
                .set("padding", "8px 16px");
            menu.add(btn);
        }

        Button btnTodos = new Button("🔎 Ver todos", e -> mostrarTodosLosLibros());
        btnTodos.getStyle()
            .set("border-radius", "20px")
            .set("background", "#a16207")
            .set("color", "white")
            .set("font-weight", "700")
            .set("border", "none")
            .set("padding", "8px 18px")
            .set("box-shadow", "0 6px 16px rgba(161,98,7,0.35)");
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
                .set("color", "#a8a29e")
                .set("padding", "24px")
                .set("font-style", "italic");
            catalogoGrid.add(vacio);
            return;
        }

        for (Libro libro : filtrados) {
            catalogoGrid.add(new LibroCard(libro, libroService));
        }
    }

    private void mostrarTodosLosLibros() {
        catalogoGrid.removeAll();
        List<Libro> todos = libroService.obtenerTodos();

        if (todos.isEmpty()) {
            Div vacio = new Div();
            vacio.setText("No hay libros publicados todavía. ¡Sé el primero en publicar!");
            vacio.getStyle()
                .set("color", "#a8a29e")
                .set("padding", "24px")
                .set("font-style", "italic");
            catalogoGrid.add(vacio);
            return;
        }

        for (Libro libro : todos) {
            catalogoGrid.add(new LibroCard(libro, libroService));
        }
    }

    private void construirFormulario() {
        genero.setItems(
            "Fantasía", "Ciencia Ficción", "Aventura", "Thriller",
            "Terror", "Romance", "Biografía"
        );

        genero.addValueChangeListener(e -> actualizarCampoEspecifico(e.getValue()));
        campoEspecifico.setVisible(false);

        formLayout.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("700px", 2)
        );

        aplicarEstiloCampo(titulo);
        aplicarEstiloCampo(autor);
        aplicarEstiloCampo(cantidadPaginas);
        aplicarEstiloCampo(precio);
        aplicarEstiloCampo(genero);
        aplicarEstiloCampo(campoEspecifico);
        aplicarEstiloCampo(imagenUrl);

        formLayout.add(titulo, autor, cantidadPaginas, precio, genero, campoEspecifico, imagenUrl);
    }

    private void actualizarCampoEspecifico(String generoSeleccionado) {
        if (generoSeleccionado == null) {
            campoEspecifico.setVisible(false);
            return;
        }

        campoEspecifico.setVisible(true);

        switch (generoSeleccionado) {
            case "Fantasía" -> campoEspecifico.setLabel("Tipo de mundo (ej: Medieval, Épico)");
            case "Ciencia Ficción" -> campoEspecifico.setLabel("Ambientación (ej: Espacial, Distópico)");
            case "Aventura" -> campoEspecifico.setLabel("Lugar de aventura (ej: Selva, Mar)");
            case "Thriller" -> campoEspecifico.setLabel("Tipo de thriller (ej: Psicológico, Espionaje)");
            case "Terror" -> campoEspecifico.setLabel("Tipo de terror (ej: Sobrenatural, Gore)");
            case "Romance" -> campoEspecifico.setLabel("Tipo de romance (ej: Contemporáneo, Histórico)");
            case "Biografía" -> campoEspecifico.setLabel("Personaje biografiado");
        }
    }

    private void guardarLibro() {
        String generoVal = genero.getValue();

        if (generoVal == null || titulo.isEmpty() || autor.isEmpty()
                || cantidadPaginas.getValue() == null || precio.getValue() == null) {
            Notification.show("Por favor completá todos los campos obligatorios.");
            return;
        }

        String especifico = campoEspecifico.getValue();

        Libro libro = switch (generoVal) {
            case "Fantasía" -> new LibroFantasia(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Ciencia Ficción" -> new LibroCienciaFiccion(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Aventura" -> new LibroAventura(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Thriller" -> new LibroThriller(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Terror" -> new LibroTerror(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Romance" -> new LibroRomance(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

            case "Biografía" -> new LibroBiografia(
                    titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(),
                    especifico, imagenUrl.getValue());

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

    private void aplicarEstiloCampo(com.vaadin.flow.component.Component campo) {
        campo.getElement().getStyle()
            .set("background", "#292524")
            .set("color", "#f5f5f4")
            .set("border-radius", "10px");
    }

    private void inyectarEstilos() {
        getElement().executeJs(
            "const oldStyle = document.getElementById('libros-dark-style');" +
            "if (oldStyle) oldStyle.remove();" +

            "const style = document.createElement('style');" +
            "style.id = 'libros-dark-style';" +
            "style.textContent = `" +

            "body, html {" +
            "  background: linear-gradient(135deg, #0c0a09, #1c1917) !important;" +
            "  color: #f5f5f4 !important;" +
            "}" +

            "vaadin-app-layout::part(drawer) {" +
            "  background: #171311 !important;" +
            "  color: #f5f5f4 !important;" +
            "  border-right: 1px solid #3f3f46;" +
            "}" +

            "vaadin-app-layout::part(navbar) {" +
            "  background: #1c1917 !important;" +
            "  color: #f5f5f4 !important;" +
            "  border-bottom: 1px solid #3f3f46;" +
            "}" +

            "vaadin-side-nav {" +
            "  background: #171311 !important;" +
            "  color: #f5f5f4 !important;" +
            "}" +

            "vaadin-side-nav-item {" +
            "  color: #e7e5e4 !important;" +
            "}" +

            "vaadin-side-nav-item::part(content) {" +
            "  border-radius: 10px;" +
            "}" +

            "vaadin-scroller {" +
            "  background: transparent !important;" +
            "}" +

            ".genero-badge {" +
            "  background: #a16207 !important;" +
            "  color: white !important;" +
            "  padding: 4px 12px;" +
            "  border-radius: 9999px;" +
            "  font-size: 0.8rem;" +
            "  font-weight: 700;" +
            "  align-self: flex-start;" +
            "}" +

            ".libro-titulo {" +
            "  margin: 8px 0 4px 0;" +
            "  font-size: 1.1rem;" +
            "  color: #fafaf9 !important;" +
            "}" +

            ".libro-autor {" +
            "  color: #d6d3d1 !important;" +
            "  margin: 0;" +
            "}" +

            ".libro-info-row {" +
            "  gap: 8px;" +
            "}" +

            ".libro-info-chip {" +
            "  background: #292524 !important;" +
            "  color: #f5f5f4 !important;" +
            "  padding: 4px 10px;" +
            "  border-radius: 9999px;" +
            "  font-size: 0.85rem;" +
            "  border: 1px solid #44403c;" +
            "}" +

            ".libro-precio-row {" +
            "  margin-top: auto;" +
            "  display: flex;" +
            "  justify-content: space-between;" +
            "  align-items: center;" +
            "}" +

            ".libro-precio {" +
            "  font-size: 1.3rem;" +
            "  font-weight: 700;" +
            "  color: #fbbf24 !important;" +
            "}" +

            "vaadin-text-field::part(input-field)," +
            "vaadin-number-field::part(input-field)," +
            "vaadin-integer-field::part(input-field)," +
            "vaadin-combo-box::part(input-field) {" +
            "  background: #292524 !important;" +
            "  color: #f5f5f4 !important;" +
            "  border: 1px solid #44403c;" +
            "  border-radius: 10px;" +
            "}" +

            "label {" +
            "  color: #e7e5e4 !important;" +
            "}" +

            "`;" +
            "document.head.appendChild(style);"
        );
    }
}