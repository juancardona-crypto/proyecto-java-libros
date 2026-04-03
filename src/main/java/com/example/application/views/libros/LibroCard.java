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

    public LibroCard(Libro libro) {
        addClassName("libro-card");


        Div cover = new Div();
        cover.addClassName("libro-cover");

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

        // Badge de género
        Span generoBadge = new Span(libro.getGenero());
        generoBadge.addClassName("genero-badge");

        // Título
        H3 tituloEl = new H3(libro.getTitulo());
        tituloEl.addClassName("libro-titulo");

        // Autor
        Paragraph autorEl = new Paragraph("✍️ " + libro.getAutor());
        autorEl.addClassName("libro-autor");

        // Info: páginas e ID
        HorizontalLayout infoRow = new HorizontalLayout();
        infoRow.addClassName("libro-info-row");

        Span paginas = new Span("📄 " + libro.getCantidadPaginas() + " págs.");
        paginas.addClassName("libro-info-chip");

        Span idSpan = new Span("ID: " + libro.getId());
        idSpan.addClassName("libro-info-chip");

        infoRow.add(paginas, idSpan);

        // Precio + botón
        Div precioDiv = new Div();
        precioDiv.addClassName("libro-precio-row");

        Span precioEl = new Span("$" + String.format("%.2f", libro.getPrecio()));
        precioEl.addClassName("libro-precio");

        Button btnComprar = new Button("Comprar");
        btnComprar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnComprar.addClickListener(e -> {
            Notification notif = Notification.show("🛒 \"" + libro.getTitulo() + "\" agregado al carrito!");
            notif.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notif.setDuration(3000);
        });

        precioDiv.add(precioEl, btnComprar);

        // Contenido
        Div content = new Div();
        content.addClassName("libro-content");
        content.add(generoBadge, tituloEl, autorEl, infoRow, precioDiv);

        add(cover, content);

        getElement().getStyle()
            .set("background", "var(--lumo-base-color)")
            .set("border-radius", "16px")
            .set("overflow", "hidden")
            .set("box-shadow", "0 4px 24px rgba(0,0,0,0.10)")
            .set("transition", "transform 0.2s ease, box-shadow 0.2s ease")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("width", "260px")
            .set("min-height", "420px")
            .set("cursor", "pointer");
    }

    private String getPlaceholderImageUrl(String genero) {
    if (genero == null) {
        return "https://picsum.photos/id/1015/600/800"; // Imagen genérica de libro
    }

    return switch (genero) {
        case "Fantasía"        -> "https://escolademagia.com.br/wp-content/uploads/2024/11/2151661289-1024x771.jpg";  
        case "Ciencia Ficción" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKGM_xfzK3rgbsRi_BCQRd3ZcJv2khJTQcow&s"; 
        case "Aventura"        -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYtrcpclka_DG4_18jzFznBFLqaczxswrriw&s";   
        case "Thriller"        -> "blob:https://gemini.google.com/a5ce852d-993f-4d78-8a61-739de55020bb";   
        case "Terror"          -> "https://i.pinimg.com/474x/a1/92/f9/a192f939a853a8a6368692c9ca1fa251.jpg";  
        case "Romance"         -> "https://thumbs.dreamstime.com/z/una-representaci%C3%B3n-de-la-acuarela-del-amor-matiz-coraz%C3%B3n-un-retrato-capturando-esencia-romance-para-las-celebraciones-d%C3%ADa-san-298173932.jpg";  
        case "Biografía"       -> "https://us.123rf.com/450wm/fernandocastoldi/fernandocastoldi1803/fernandocastoldi180300059/98983726-viejo-libro-abierto-con-pluma-y-tintero.jpg?ver=6";   
        default                -> "https://picsum.photos/id/1015/600/800"; 
    };
}
}