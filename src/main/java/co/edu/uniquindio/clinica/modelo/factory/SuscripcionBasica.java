package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.enums.Servicios;
import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.time.LocalDateTime;
import java.util.*;


public class SuscripcionBasica implements Suscripcion {
    private List<Servicio> serviciosIncluidos;

    public SuscripcionBasica() {
        this.serviciosIncluidos = generarServiciosAleatorios(5); //  5 servicios aleatorios
    }

    private List<Servicio> generarServiciosAleatorios(int cantidad) {
        List<Servicios> todos = new ArrayList<>(Arrays.asList(Servicios.values()));
        Collections.shuffle(todos);
        List<Servicio> seleccionados = new ArrayList<>();


        for (int i = 0; i < cantidad && i < todos.size(); i++) {
            Servicios tipo = todos.get(i);
            String id = UUID.randomUUID().toString();
            String nombre = tipo.name();
            double precio = 80000;
            seleccionados.add(new Servicio(id, nombre, precio));
        }

        return seleccionados;
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return serviciosIncluidos;
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio) {
        double subtotal = servicio.getPrecio();
        double total;

        if (serviciosIncluidos.indexOf(servicio) >= 0) {
            int index = serviciosIncluidos.indexOf(servicio);

            if (index < 2) {
                total = 0; // Gratis
            } else if (index < 5) {
                total = subtotal * 0.2; // 20% de descuento
            } else {
                total = subtotal; // Precio completo
            }
        } else {
            total = subtotal; // Si el servicio no wsta en la suscripcion sepaga completo
        }

        return new Factura.FacturaBuilder()
                .id(UUID.randomUUID().toString())
                .fecha(LocalDateTime.now())
                .subtotal(subtotal)
                .total(total)
                .build();
    }
}