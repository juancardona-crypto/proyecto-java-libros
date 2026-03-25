package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LibroCard extends Div {

    public LibroCard(Libro libro) {
        addClassName("libro-card");

        String emoji = getEmojiGenero(libro.getGenero());

        // Cover decorativo
        Div cover = new Div();
        cover.addClassName("libro-cover");
        Span emojiSpan = new Span(emoji);
        emojiSpan.addClassName("libro-emoji");
        cover.add(emojiSpan);

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
            .set("min-height", "360px")
            .set("cursor", "pointer");
    }

    private String getEmojiGenero(String genero) {
        if (genero == null) return "📚";
        return switch (genero) {
            case "Fantasía"        -> "🧙";
            case "Ciencia Ficción" -> "🚀";
            case "Aventura"        -> "🗺️";
            case "Thriller"        -> "🔍";
            case "Terror"          -> "👻";
            case "Romance"         -> "💖";
            case "Biografía"       -> "🎩";
            default                -> "📚";
        };
    }
}