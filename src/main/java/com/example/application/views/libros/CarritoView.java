package com.example.application.views.libros;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Carrito")
@Route("carrito")
@Menu(order = 1, icon = LineAwesomeIconUrl.SHOPPING_CART_SOLID)
public class CarritoView extends VerticalLayout {

    private final CarritoService carritoService;
    private final LibroService libroService;

    public CarritoView(CarritoService carritoService, LibroService libroService) {
        this.carritoService = carritoService;
        this.libroService = libroService;

        setPadding(true);
        setSpacing(true);
        setSizeFull();

        getStyle()
            .set("background", "linear-gradient(160deg, #0a0704 0%, #110d08 50%, #0d0a06 100%)")
            .set("min-height", "100vh")
            .set("color", "#f0e6d0")
            .set("padding", "32px");

        construirVista();
    }

    private void construirVista() {
        removeAll();

        H2 titulo = new H2("🛒 Carrito de compras");
        titulo.getStyle()
            .set("color", "#f0e6d0")
            .set("margin-bottom", "20px");

        add(titulo);

        if (carritoService.obtenerItems().isEmpty()) {
            Div vacio = new Div();
            vacio.setText("Tu carrito está vacío.");
            vacio.getStyle()
                .set("color", "#a89070")
                .set("font-style", "italic");
            add(vacio);
            return;
        }

        for (CarritoItem item : carritoService.obtenerItems()) {
            Div card = new Div();
            card.getStyle()
                .set("background", "linear-gradient(145deg, #1a1108 0%, #100c06 100%)")
                .set("border", "1px solid #5a3a1a")
                .set("border-radius", "12px")
                .set("padding", "16px")
                .set("margin-bottom", "12px")
                .set("box-shadow", "0 8px 24px rgba(0,0,0,0.35)");

            H3 nombre = new H3(item.getLibro().getTitulo());
            nombre.getStyle().set("color", "#f0e6d0").set("margin", "0 0 8px 0");

            Div detalles = new Div();
            detalles.setText("Cantidad: " + item.getCantidad()
                    + " | Precio unitario: $" + item.getLibro().getPrecio()
                    + " | Subtotal: $" + item.getSubtotal());
            detalles.getStyle().set("color", "#a89070");

            Button eliminar = new Button("Eliminar", e -> {
                carritoService.eliminarLibro(item.getLibro().getId());
                construirVista();
            });
            eliminar.getStyle()
                .set("margin-top", "10px")
                .set("background", "#7f1d1d")
                .set("color", "white")
                .set("border", "none")
                .set("border-radius", "8px");

            card.add(nombre, detalles, eliminar);
            add(card);
        }

        Div total = new Div();
        total.setText("Total: $" + carritoService.getTotal());
        total.getStyle()
            .set("font-size", "1.2rem")
            .set("font-weight", "700")
            .set("color", "#e8c96d")
            .set("margin-top", "20px");

        Button confirmar = new Button("Confirmar compra", e -> {
            boolean exito = carritoService.confirmarCompra(libroService);

            if (exito) {
                Notification.show("✅ Compra realizada con éxito");
                construirVista();
            } else {
                Notification.show("❌ No hay suficiente stock para completar la compra");
            }
        });

        confirmar.getStyle()
            .set("margin-top", "16px")
            .set("background", "linear-gradient(135deg, #b8860b, #c9a84c)")
            .set("color", "#0d0a06")
            .set("border", "none")
            .set("border-radius", "8px")
            .set("font-weight", "700")
            .set("padding", "10px 24px");

        add(total, confirmar);
    }
}