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

@PageTitle("Librería")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class LibrosView extends VerticalLayout {

    private static final String BG_PAGE          = "linear-gradient(160deg, #0a0704 0%, #110d08 50%, #0d0a06 100%)";
    private static final String BG_CARD          = "linear-gradient(145deg, #1a1108 0%, #100c06 100%)";
    private static final String DORADO           = "#c9a84c";
    private static final String DORADO_CLARO     = "#e8c96d";
    private static final String DORADO_OPACO     = "rgba(183, 150, 62, 0.49)";
    private static final String CAFE_BORDE       = "#5a3a1a";
    private static final String CAFE_BORDE_SUAVE = "#7b461e";
    private static final String TEXTO_PRINCIPAL  = "#f0e6d0";
    private static final String TEXTO_SECUNDARIO = "#a89070";
    private static final String TEXTO_MUTED      = "#6b5040";

    private final TextField titulo = new TextField("Título");
    private final TextField autor = new TextField("Autor");
    private final IntegerField cantidadPaginas = new IntegerField("Páginas");
    private final NumberField precio = new NumberField("Precio");
    private final IntegerField stock = new IntegerField("Cuantas unidades desea publicar");
    private final ComboBox<String> genero = new ComboBox<>("Género");
    private final TextField campoEspecifico = new TextField();
    private final TextField imagenUrl = new TextField("URL de imagen (opcional)");
    private final FormLayout formLayout = new FormLayout();

    private final FlexLayout catalogoGrid = new FlexLayout();
    private final LibroService libroService;
    private final CarritoService carritoService;

    public LibrosView(LibroService libroService, CarritoService carritoService) {
        this.libroService = libroService;
        this.carritoService = carritoService;

        setPadding(false);
        setSpacing(false);
        setSizeFull();

        getStyle()
            .set("background", BG_PAGE)
            .set("min-height", "100vh")
            .set("font-family", "'Georgia', 'Times New Roman', serif");

        Div hero = construirHero();
        Div seccionCatalogo = construirSeccionCatalogo();
        Div seccionFormulario = construirSeccionFormulario();

        mostrarTodosLosLibros();

        add(hero, seccionCatalogo, seccionFormulario);

        inyectarEstilos();
    }

    private Div construirHero() {
        Div hero = new Div();
        hero.getStyle()
            .set("width", "100%")
            .set("padding", "56px 48px 40px")
            .set("box-sizing", "border-box")
            .set("background", "linear-gradient(180deg, rgba(10,7,4,0.95) 0%, transparent 100%)")
            .set("border-bottom", "1px solid " + CAFE_BORDE_SUAVE)
            .set("position", "relative")
            .set("overflow", "hidden");

        Div ornamento = new Div();
        ornamento.getElement().setProperty("innerHTML",
            "<svg width='300' height='300' viewBox='0 0 300 300' " +
            "style='position:absolute;top:-80px;right:-60px;opacity:0.04;pointer-events:none;'>" +
            "<circle cx='150' cy='150' r='140' stroke='#c9a84c' stroke-width='1' fill='none'/>" +
            "<circle cx='150' cy='150' r='100' stroke='#c9a84c' stroke-width='1' fill='none'/>" +
            "<circle cx='150' cy='150' r='60' stroke='#c9a84c' stroke-width='1' fill='none'/>" +
            "</svg>");
        hero.add(ornamento);

        Div eyebrow = new Div();
        eyebrow.getElement().setProperty("innerHTML",
            "<span style='display:inline-flex;align-items:center;gap:10px;" +
            "color:" + DORADO + ";font-size:0.72rem;font-weight:600;" +
            "letter-spacing:0.22em;text-transform:uppercase;" +
            "font-family:Georgia,serif;'>" +
            "&#10022; Bienvenido &#10022;</span>");
        eyebrow.getStyle().set("margin-bottom", "12px");

        H2 tituloPagina = new H2("Gran Librería");
        tituloPagina.getStyle()
            .set("font-family", "Georgia, 'Times New Roman', serif")
            .set("font-size", "clamp(2.2rem, 5vw, 3.8rem)")
            .set("font-weight", "400")
            .set("margin", "0 0 8px 0")
            .set("color", TEXTO_PRINCIPAL)
            .set("letter-spacing", "0.06em")
            .set("line-height", "1.1");

        Div lineaDecorativa = new Div();
        lineaDecorativa.getStyle()
            .set("width", "80px")
            .set("height", "2px")
            .set("background", "linear-gradient(to right, transparent, " + DORADO + ", transparent)")
            .set("margin", "14px 0");

        Div subtituloHero = new Div();
        subtituloHero.getElement().setProperty("innerHTML",
            "<span style='color:" + TEXTO_SECUNDARIO + ";font-size:1rem;" +
            "font-style:italic;font-family:Georgia,serif;letter-spacing:0.03em;'>" +
            "Descubre obras que transforman el alma</span>");

        hero.add(eyebrow, tituloPagina, lineaDecorativa, subtituloHero);
        return hero;
    }

    private Div construirSeccionCatalogo() {
        Div seccion = new Div();
        seccion.getStyle()
            .set("padding", "40px 48px")
            .set("box-sizing", "border-box")
            .set("width", "100%");

        Div encabezado = new Div();
        encabezado.getStyle()
            .set("display", "flex")
            .set("align-items", "center")
            .set("gap", "16px")
            .set("margin-bottom", "20px");

        Div lineaIzq = new Div();
        lineaIzq.getStyle()
            .set("flex", "1")
            .set("height", "1px")
            .set("background", "linear-gradient(to right, transparent, " + CAFE_BORDE + ")");

        Div labelSeccion = new Div();
        labelSeccion.getElement().setProperty("innerHTML",
            "<span style='color:" + DORADO + ";font-size:0.68rem;font-weight:600;" +
            "letter-spacing:0.2em;text-transform:uppercase;" +
            "font-family:Georgia,serif;white-space:nowrap;'>" +
            "&#10022; Nuestro Catálogo &#10022;</span>");

        Div lineaDer = new Div();
        lineaDer.getStyle()
            .set("flex", "1")
            .set("height", "1px")
            .set("background", "linear-gradient(to left, transparent, " + CAFE_BORDE + ")");

        encabezado.add(lineaIzq, labelSeccion, lineaDer);

        H3 subtitulo = new H3("Filtrar por género");
        subtitulo.getStyle()
            .set("font-family", "Georgia, serif")
            .set("font-size", "0.78rem")
            .set("font-weight", "400")
            .set("letter-spacing", "0.12em")
            .set("text-transform", "uppercase")
            .set("color", TEXTO_MUTED)
            .set("margin", "0 0 12px 0");

        HorizontalLayout menuGeneros = construirMenuGeneros();

        catalogoGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        catalogoGrid.getStyle()
            .set("gap", "24px")
            .set("padding", "24px 0 8px 0")
            .set("align-items", "stretch");

        seccion.add(encabezado, subtitulo, menuGeneros, catalogoGrid);
        return seccion;
    }

    private Div construirSeccionFormulario() {
        Div wrapper = new Div();
        wrapper.getStyle()
            .set("padding", "8px 48px 56px")
            .set("box-sizing", "border-box")
            .set("width", "100%");

        Div separadorOrn = new Div();
        separadorOrn.getElement().setProperty("innerHTML",
            "<div style='display:flex;align-items:center;gap:16px;margin-bottom:32px;'>" +
            "<div style='flex:1;height:1px;background:linear-gradient(to right,transparent," + CAFE_BORDE + ");'></div>" +
            "<span style='color:" + DORADO + ";font-size:0.68rem;font-weight:600;" +
            "letter-spacing:0.2em;text-transform:uppercase;font-family:Georgia,serif;'>" +
            "&#10022; Publicar Obra &#10022;</span>" +
            "<div style='flex:1;height:1px;background:linear-gradient(to left,transparent," + CAFE_BORDE + ");'></div>" +
            "</div>");

        Div formCard = new Div();
        formCard.getStyle()
            .set("background", BG_CARD)
            .set("border-radius", "18px")
            .set("padding", "36px 40px")
            .set("border", "1px solid " + CAFE_BORDE)
            .set("box-shadow", "0 20px 60px rgba(0,0,0,0.6), inset 0 1px 0 rgba(201,168,76,0.1)")
            .set("position", "relative")
            .set("overflow", "hidden");

        Div ornFormCard = new Div();
        ornFormCard.getElement().setProperty("innerHTML",
            "<div style='position:absolute;top:0;right:0;width:120px;height:120px;" +
            "background:radial-gradient(circle at top right, rgba(201,168,76,0.06) 0%, transparent 70%);" +
            "pointer-events:none;'></div>");
        formCard.add(ornFormCard);

        Div formHeader = new Div();
        formHeader.getStyle()
            .set("margin-bottom", "28px")
            .set("padding-bottom", "20px")
            .set("border-bottom", "1px solid " + CAFE_BORDE_SUAVE);

        H3 tituloForm = new H3("Añadir nueva obra");
        tituloForm.getStyle()
            .set("font-family", "Georgia, serif")
            .set("font-size", "1.5rem")
            .set("font-weight", "400")
            .set("color", TEXTO_PRINCIPAL)
            .set("margin", "0 0 6px 0")
            .set("letter-spacing", "0.04em");

        Div descForm = new Div();
        descForm.getElement().setProperty("innerHTML",
            "<span style='color:" + TEXTO_MUTED + ";font-size:0.82rem;" +
            "font-style:italic;font-family:Georgia,serif;'>" +
            "Completa los campos para publicar tu libro en el catálogo</span>");

        formHeader.add(tituloForm, descForm);

        construirFormulario();

        Button btnGuardar = new Button("✦  Publicar en el catálogo");
        btnGuardar.getStyle()
            .set("margin-top", "24px")
            .set("background", "linear-gradient(135deg, #b8860b, #c9a84c)")
            .set("color", "#0d0a06")
            .set("border-radius", "8px")
            .set("font-family", "Georgia, serif")
            .set("font-weight", "700")
            .set("font-size", "0.88rem")
            .set("letter-spacing", "0.08em")
            .set("padding", "12px 32px")
            .set("border", "none")
            .set("box-shadow", "0 4px 20px rgba(201,168,76,0.35)")
            .set("cursor", "pointer")
            .set("transition", "all 0.2s ease")
            .set("text-transform", "uppercase");

        btnGuardar.addClickListener(e -> guardarLibro());

        formCard.add(formHeader, formLayout, btnGuardar);
        wrapper.add(separadorOrn, formCard);
        return wrapper;
    }

    private HorizontalLayout construirMenuGeneros() {
        HorizontalLayout menu = new HorizontalLayout();
        menu.setSpacing(false);
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
                .set("border-radius", "4px")
                .set("font-family", "Georgia, serif")
                .set("font-size", "0.8rem")
                .set("font-weight", "400")
                .set("letter-spacing", "0.06em")
                .set("background", "rgba(107,76,30,0.18)")
                .set("color", TEXTO_SECUNDARIO)
                .set("border", "1px solid " + CAFE_BORDE_SUAVE)
                .set("padding", "7px 16px")
                .set("cursor", "pointer")
                .set("transition", "all 0.2s ease");
            menu.add(btn);
        }

        Button btnTodos = new Button("Ver todos", e -> mostrarTodosLosLibros());
        btnTodos.getStyle()
            .set("border-radius", "4px")
            .set("font-family", "Georgia, serif")
            .set("font-size", "0.8rem")
            .set("font-weight", "700")
            .set("letter-spacing", "0.08em")
            .set("background", "linear-gradient(135deg, #b8860b, #c9a84c)")
            .set("color", "#0d0a06")
            .set("border", "none")
            .set("padding", "7px 20px")
            .set("box-shadow", "0 2px 12px rgba(201,168,76,0.3)")
            .set("cursor", "pointer");

        menu.add(btnTodos);
        return menu;
    }

    private void mostrarLibrosPorGenero(String generoFiltro) {
        catalogoGrid.removeAll();
        List<Libro> filtrados = libroService.obtenerPorGenero(generoFiltro);

        if (filtrados.isEmpty()) {
            catalogoGrid.add(construirMensajeVacio("No hay obras de " + generoFiltro + " en el catálogo aún."));
            return;
        }

        for (Libro libro : filtrados) {
            catalogoGrid.add(new LibroCard(libro, libroService, carritoService));
        }
    }

    private void mostrarTodosLosLibros() {
        catalogoGrid.removeAll();
        List<Libro> todos = libroService.obtenerTodos();

        if (todos.isEmpty()) {
            catalogoGrid.add(construirMensajeVacio("El catálogo está vacío. ¡Sé el primero en publicar una obra!"));
            return;
        }

        for (Libro libro : todos) {
            catalogoGrid.add(new LibroCard(libro, libroService, carritoService));
        }
    }

    private Div construirMensajeVacio(String mensaje) {
        Div vacio = new Div();
        vacio.getElement().setProperty("innerHTML",
            "<span style='display:block;text-align:center;padding:48px 24px;" +
            "color:" + TEXTO_MUTED + ";font-style:italic;font-family:Georgia,serif;" +
            "font-size:1rem;letter-spacing:0.04em;'>" +
            "&#10022; " + mensaje + " &#10022;</span>");
        vacio.getStyle().set("width", "100%");
        return vacio;
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
        aplicarEstiloCampo(stock);
        aplicarEstiloCampo(genero);
        aplicarEstiloCampo(campoEspecifico);
        aplicarEstiloCampo(imagenUrl);

        formLayout.add(titulo, autor, cantidadPaginas, precio, stock, genero, campoEspecifico, imagenUrl);
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

        if (generoVal == null || titulo.isEmpty() || autor.isEmpty()
                || cantidadPaginas.getValue() == null || precio.getValue() == null
                || stock.getValue() == null || stock.getValue() < 0) {
            Notification.show("Por favor completá todos los campos obligatorios.");
            return;
        }

        String especifico = campoEspecifico.getValue();

        Libro libro = switch (generoVal) {
            case "Fantasía" -> new LibroFantasia(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Ciencia Ficción" -> new LibroCienciaFiccion(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Aventura" -> new LibroAventura(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Thriller" -> new LibroThriller(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Terror" -> new LibroTerror(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Romance" -> new LibroRomance(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            case "Biografía" -> new LibroBiografia(titulo.getValue(), generoVal, autor.getValue(),
                    cantidadPaginas.getValue(), precio.getValue(), especifico, imagenUrl.getValue(), stock.getValue());

            default -> {
                Notification.show("Género no disponible.");
                yield null;
            }
        };

        if (libro != null) {
            libroService.agregarLibro(libro);
            Notification.show("✦ Obra publicada en el catálogo.");
            limpiarFormulario();
            mostrarTodosLosLibros();
        }
    }

    private void limpiarFormulario() {
        titulo.clear();
        autor.clear();
        cantidadPaginas.clear();
        precio.clear();
        stock.clear();
        genero.clear();
        campoEspecifico.clear();
        campoEspecifico.setVisible(false);
        imagenUrl.clear();
    }

    private void aplicarEstiloCampo(com.vaadin.flow.component.Component campo) {
        campo.getElement().getStyle()
            .set("background", "#1a1108")
            .set("color", "#f0e6d0")
            .set("border-radius", "8px")
            .set("font-family", "Georgia, serif");
    }

    private void inyectarEstilos() {
        getElement().executeJs(
            "const old = document.getElementById('libros-premium-style');" +
            "if (old) old.remove();" +
            "const s = document.createElement('style');" +
            "s.id = 'libros-premium-style';" +
            "s.textContent = `" +
            "body, html {" +
            "  background: linear-gradient(160deg,#0a0704 0%,#110d08 50%,#0d0a06 100%) !important;" +
            "  color: #f0e6d0 !important;" +
            "}" +
            "vaadin-app-layout::part(drawer) {" +
            "  background: #0d0904 !important;" +
            "  border-right: 1px solid #3b2412 !important;" +
            "}" +
            "vaadin-app-layout::part(navbar) {" +
            "  background: linear-gradient(90deg, #0d0904, #1a1108) !important;" +
            "  border-bottom: 1px solid #3b2412 !important;" +
            "}" +
            "`;" +
            "document.head.appendChild(s);"
        );
    }
}