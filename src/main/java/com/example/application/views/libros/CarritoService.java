package com.example.application.views.libros;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@SessionScope
public class CarritoService {

    private final List<CarritoItem> items = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        for (CarritoItem item : items) {
            if (item.getLibro().getId() == libro.getId()) {
                if (item.getCantidad() < libro.getStock()) {
                    item.setCantidad(item.getCantidad() + 1);
                }
                return;
            }
        }

        if (libro.getStock() > 0) {
            items.add(new CarritoItem(libro, 1));
        }
    }

    public List<CarritoItem> obtenerItems() {
        return new ArrayList<>(items);
    }

    public void eliminarLibro(long libroId) {
        items.removeIf(item -> item.getLibro().getId() == libroId);
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public int getCantidadTotalItems() {
        int total = 0;
        for (CarritoItem item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public double getTotal() {
        double total = 0;
        for (CarritoItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean confirmarCompra(LibroService libroService) {
        for (CarritoItem item : items) {
            Libro libroReal = libroService.buscarPorId(item.getLibro().getId());

            if (libroReal == null || libroReal.getStock() < item.getCantidad()) {
                return false;
            }
        }

        for (CarritoItem item : items) {
            Libro libroReal = libroService.buscarPorId(item.getLibro().getId());
            libroReal.setStock(libroReal.getStock() - item.getCantidad());
        }

        libroService.eliminarLibrosSinStock();
        vaciarCarrito();
        return true;
    }
}