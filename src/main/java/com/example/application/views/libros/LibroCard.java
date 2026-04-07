package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LibroCard extends Div {

    public LibroCard(Libro libro, LibroService libroService) {
        addClassName("libro-card");

        Div cover = new Div();
        cover.addClassName("libro-cover");

        cover.getStyle()
    .set("width", "100%")
    .set("height", "220px")
    .set("overflow", "hidden")
    .set("background", "#0f172a");

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
                .set("object-fit", "cover");
            cover.add(placeholderImg);
        }

        Span generoBadge = new Span(libro.getGenero());
        generoBadge.addClassName("genero-badge");

        H3 tituloEl = new H3(libro.getTitulo());
        tituloEl.addClassName("libro-titulo");

        Paragraph autorEl = new Paragraph("✍️ " + libro.getAutor());
        autorEl.addClassName("libro-autor");

        Paragraph ficha = new Paragraph(libro.obtenerFichaTecnica());
        ficha.getStyle()
    .set("font-size", "0.85rem")
    .set("color", "#cbd5e1")
    .set("line-height", "1.4")
    .set("margin", "0");

        HorizontalLayout infoRow = new HorizontalLayout();
        infoRow.addClassName("libro-info-row");

        Span paginas = new Span("📄 " + libro.getCantidadPaginas() + " págs.");
        paginas.addClassName("libro-info-chip");

        Span idSpan = new Span("ID: " + libro.getId());
        idSpan.addClassName("libro-info-chip");

        infoRow.add(paginas, idSpan);

        Div precioDiv = new Div();
        precioDiv.addClassName("libro-precio-row");

        Span precioEl = new Span("$" + String.format("%.2f", libro.getPrecio()));
        precioEl.addClassName("libro-precio");
        precioEl.getStyle()
    .set("font-size", "1.4rem")
    .set("font-weight", "800")
    .set("color", "#a78bfa");

        Button btnComprar = new Button("Comprar");
        btnComprar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnComprar.getStyle()
    .set("border-radius", "10px")
    .set("font-weight", "700")
    .set("background", "#6366f1")
    .set("color", "white")
    .set("padding", "8px 16px");

        btnComprar.addClickListener(e -> {
            boolean comprado = libroService.comprarLibro(libro.getId());

            Notification notif;
            if (comprado) {
                notif = Notification.show("✅ \"" + libro.getTitulo() + "\" comprado correctamente");
                notif.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                this.setVisible(false);
            } else {
                notif = Notification.show("❌ Error al comprar el libro");
                notif.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

            notif.setDuration(3000);
        });

        precioDiv.add(precioEl, btnComprar);

        Div content = new Div();
        content.addClassName("libro-content");
        content.add(generoBadge, tituloEl, autorEl, ficha, infoRow, precioDiv);

        content.getStyle()
    .set("padding", "16px")
    .set("display", "flex")
    .set("flex-direction", "column")
    .set("gap", "10px")
    .set("color", "#e5e7eb")
    .set("flex-grow", "1");

        add(cover, content);
getElement().getStyle()
    .set("background", "linear-gradient(145deg, #1e293b, #020617)")
    .set("border-radius", "18px")
    .set("overflow", "hidden")
    .set("box-shadow", "0 10px 30px rgba(0,0,0,0.4)")
    .set("transition", "all 0.25s ease")
    .set("display", "flex")
    .set("flex-direction", "column")
    .set("width", "260px")
    .set("min-height", "420px")
    .set("cursor", "pointer");
    }

    private String getPlaceholderImageUrl(String genero) {
        if (genero == null) {
            return "https://picsum.photos/id/1015/600/800";
        }

        return switch (genero) {
            case "Fantasía" -> "https://escolademagia.com.br/wp-content/uploads/2024/11/2151661289-1024x771.jpg";
            case "Ciencia Ficción" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKGM_xfzK3rgbsRi_BCQRd3ZcJv2khJTQcow&s";
            case "Aventura" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYtrcpclka_DG4_18jzFznBFLqaczxswrriw&s";
            case "Thriller" -> "https://picsum.photos/id/1025/600/800";
            case "Terror" -> "https://i.pinimg.com/474x/a1/92/f9/a192f939a853a8a6368692c9ca1fa251.jpg";
            case "Romance" -> "https://thumbs.dreamstime.com/z/una-representaci%C3%B3n-de-la-acuarela-del-amor-matiz-coraz%C3%B3n-un-retrato-capturando-esencia-romance-para-las-celebraciones-d%C3%ADa-san-298173932.jpg";
            case "Biografía" -> "https://us.123rf.com/450wm/fernandocastoldi/fernandocastoldi1803/fernandocastoldi180300059/98983726-viejo-libro-abierto-con-pluma-y-tintero.jpg?ver=6";
            default -> "https://picsum.photos/id/1015/600/800";
        };
    }
}