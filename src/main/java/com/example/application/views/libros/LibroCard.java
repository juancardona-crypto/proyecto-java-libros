package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LibroCard extends Div {

    private static final String COLOR_FONDO_CARD       = "linear-gradient(160deg, #1a1008 0%, #0d0a06 100%)";
    private static final String COLOR_BORDE_CARD       = "#6b4c1e";
    private static final String COLOR_DORADO_PRINCIPAL = "#c9a84c";
    private static final String COLOR_DORADO_CLARO     = "#e8c96d";
    private static final String COLOR_DORADO_OPACO     = "rgba(201,168,76,0.15)";
    private static final String COLOR_TEXTO_PRINCIPAL  = "#f0e6d0";
    private static final String COLOR_TEXTO_SECUNDARIO = "#a89070";
    private static final String COLOR_CAFE_BORDE       = "#5a3a1a";

    public LibroCard(Libro libro, LibroService libroService, CarritoService carritoService) {
        addClassName("libro-card");

        Div cover = new Div();
        cover.addClassName("libro-cover");
        cover.getStyle()
            .set("width", "100%")
            .set("height", "230px")
            .set("overflow", "hidden")
            .set("background", "#0d0a06")
            .set("position", "relative");

        if (libro.getImagenUrl() != null && !libro.getImagenUrl().isEmpty()) {
            Image img = new Image(libro.getImagenUrl(), libro.getTitulo());
            img.getStyle()
                .set("width", "100%")
                .set("height", "100%")
                .set("object-fit", "cover");
            cover.add(img);
        } else {
            Image placeholderImg = new Image(getPlaceholderImageUrl(libro.getGenero()), libro.getTitulo());
            placeholderImg.getStyle()
                .set("width", "100%")
                .set("height", "100%")
                .set("object-fit", "contain");
            cover.add(placeholderImg);
        }

        Span generoBadge = new Span(libro.getGenero());
        generoBadge.getStyle()
            .set("display", "inline-block")
            .set("background", COLOR_DORADO_OPACO)
            .set("color", COLOR_DORADO_CLARO)
            .set("border", "1px solid " + COLOR_BORDE_CARD)
            .set("border-radius", "4px")
            .set("font-size", "0.68rem")
            .set("font-weight", "600")
            .set("letter-spacing", "0.12em")
            .set("text-transform", "uppercase")
            .set("padding", "3px 10px");

        H3 tituloEl = new H3(libro.getTitulo());
        tituloEl.getStyle()
            .set("margin", "0")
            .set("font-size", "1.08rem")
            .set("font-weight", "700")
            .set("color", COLOR_TEXTO_PRINCIPAL)
            .set("line-height", "1.35")
            .set("letter-spacing", "0.01em");

        Paragraph autorEl = new Paragraph();
        autorEl.getElement().setProperty("innerHTML",
            "<span style=\"color:" + COLOR_DORADO_PRINCIPAL + ";font-size:0.75rem;\">✦</span>" +
            " <span style=\"color:" + COLOR_TEXTO_SECUNDARIO + ";font-size:0.82rem;font-style:italic;\">" +
            libro.getAutor() + "</span>");
        autorEl.getStyle().set("margin", "0");

        Paragraph ficha = new Paragraph(libro.obtenerFichaTecnica());
        ficha.getStyle()
            .set("font-size", "0.78rem")
            .set("color", COLOR_TEXTO_SECUNDARIO)
            .set("line-height", "1.5")
            .set("margin", "0")
            .set("padding", "8px 10px")
            .set("background", "rgba(255,255,255,0.03)")
            .set("border-left", "2px solid " + COLOR_BORDE_CARD)
            .set("border-radius", "0 4px 4px 0");

        Span stockSpan = new Span("Stock disponible: " + libro.getStock());
        stockSpan.getStyle()
            .set("color", libro.getStock() > 0 ? "#e8c96d" : "#ff6b6b")
            .set("font-size", "0.78rem")
            .set("font-weight", "600")
            .set("letter-spacing", "0.03em");

        HorizontalLayout infoRow = new HorizontalLayout();
        infoRow.getStyle()
            .set("gap", "8px")
            .set("margin", "0");
        infoRow.setSpacing(false);

        Span paginas = crearChip("📄 " + libro.getCantidadPaginas() + " págs.");
        Span idSpan = crearChip("ID: " + libro.getId());
        infoRow.add(paginas, idSpan);

        Div separador = new Div();
        separador.getStyle()
            .set("height", "1px")
            .set("background", "linear-gradient(to right, transparent, " + COLOR_BORDE_CARD + ", transparent)")
            .set("margin", "2px 0");

        Div precioDiv = new Div();
        precioDiv.getStyle()
        .set("display", "flex")
        .set("flex-direction", "column")   
        .set("align-items", "flex-start") 
        .set("gap", "12px")              
        .set("margin-top", "2px");

        // Precio
        Div precioWrapper = new Div();
        precioWrapper.getStyle()
            .set("display", "flex")
            .set("align-items", "baseline")    
            .set("gap", "2px");

        Span moneda = new Span("COP");
        moneda.getStyle()
            .set("font-size", "0.85rem")
            .set("color", COLOR_DORADO_PRINCIPAL)
            .set("font-weight", "600")
            .set("vertical-align", "super");

        Span monto = new Span(String.format("%.2f", libro.getPrecio()));
        monto.getStyle()
            .set("font-size", "1.55rem")
            .set("font-weight", "800")
            .set("color", COLOR_DORADO_CLARO)
            .set("letter-spacing", "-0.01em");

        precioWrapper.add(moneda, monto);

        // Botón
        Button btnComprar = new Button("Agregar al carrito");
        btnComprar.getStyle()
            .set("background", "linear-gradient(135deg, #b8860b, #c9a84c)")
            .set("color", "#0d0a06")
            .set("border", "none")
            .set("border-radius", "6px")
            .set("font-weight", "800")
            .set("font-size", "0.82rem")
            .set("letter-spacing", "0.05em")
            .set("padding", "8px 18px")
            .set("cursor", "pointer")
            .set("text-transform", "uppercase")
            .set("transition", "all 0.2s ease");

        precioDiv.add(precioWrapper, btnComprar);

        btnComprar.addClickListener(e -> {
            if (libro.getStock() > 0) {
                carritoService.agregarLibro(libro);
                Notification notif = Notification.show("🛒 \"" + libro.getTitulo() + "\" agregado al carrito");
                notif.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notif.setDuration(3000);
            } else {
                Notification notif = Notification.show("❌ No hay stock disponible");
                notif.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notif.setDuration(3000);
            }
        });

        precioDiv.add(precioWrapper, btnComprar);

        Div content = new Div();
        content.addClassName("libro-content");
        content.getStyle()
            .set("padding", "16px 18px")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("gap", "10px")
            .set("flex-grow", "1");

        content.add(generoBadge, tituloEl, autorEl, ficha, stockSpan, infoRow, separador, precioDiv);

        add(cover, content);

        getElement().getStyle()
            .set("background", COLOR_FONDO_CARD)
            .set("border", "1px solid " + COLOR_CAFE_BORDE)
            .set("border-radius", "14px")
            .set("overflow", "hidden")
            .set("box-shadow", "0 8px 32px rgba(0,0,0,0.6), inset 0 1px 0 rgba(201,168,76,0.08)")
            .set("transition", "transform 0.25s ease, box-shadow 0.25s ease, border-color 0.25s ease")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("width", "260px")
            .set("min-height", "460px")
            .set("cursor", "pointer");

        getElement().addEventListener("mouseover", e ->
            getElement().getStyle()
                .set("transform", "translateY(-4px)")
                .set("box-shadow", "0 16px 40px rgba(0,0,0,0.7), 0 0 0 1px " + COLOR_DORADO_PRINCIPAL)
                .set("border-color", COLOR_DORADO_PRINCIPAL)
        );
        getElement().addEventListener("mouseout", e ->
            getElement().getStyle()
                .set("transform", "translateY(0)")
                .set("box-shadow", "0 8px 32px rgba(0,0,0,0.6), inset 0 1px 0 rgba(201,168,76,0.08)")
                .set("border-color", COLOR_CAFE_BORDE)
        );
    }

    private Span crearChip(String texto) {
        Span chip = new Span(texto);
        chip.getStyle()
            .set("background", "rgba(107,76,30,0.25)")
            .set("color", "#a89070")
            .set("border", "1px solid #3b2412")
            .set("border-radius", "4px")
            .set("font-size", "0.72rem")
            .set("padding", "2px 8px")
            .set("white-space", "nowrap");
        return chip;
    }

    private String getPlaceholderImageUrl(String genero) {
        if (genero == null) return "https://picsum.photos/id/1015/600/800";
        return switch (genero) {
            case "Fantasía"        -> "https://escolademagia.com.br/wp-content/uploads/2024/11/2151661289-1024x771.jpg";
            case "Ciencia Ficción" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKGM_xfzK3rgbsRi_BCQRd3ZcJv2khJTQcow&s";
            case "Aventura"        -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYtrcpclka_DG4_18jzFznBFLqaczxswrriw&s";
            case "Thriller"        -> "https://picsum.photos/id/1025/600/800";
            case "Terror"          -> "https://i.pinimg.com/474x/a1/92/f9/a192f939a853a8a6368692c9ca1fa251.jpg";
            case "Romance"         -> "https://thumbs.dreamstime.com/z/una-representaci%C3%B3n-de-la-acuarela-del-amor-matiz-coraz%C3%B3n-un-retrato-capturando-esencia-romance-para-las-celebraciones-d%C3%ADa-san-298173932.jpg";
            case "Biografía"       -> "https://us.123rf.com/450wm/fernandocastoldi/fernandocastoldi1803/fernandocastoldi180300059/98983726-viejo-libro-abierto-con-pluma-y-tintero.jpg?ver=6";
            default                -> "https://picsum.photos/id/1015/600/800";
        };
    }
}