package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.Servicios;
import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class SuscripcionPremium implements  Suscripcion{
    private List<Servicio> serviciosIncluidos;

    public SuscripcionPremium(SuscripcionBasica basica) {
        List<Servicio> base = new ArrayList<>(basica.getServiciosDisponibles());
        Set<String> nombresBase = base.stream()
                .map(Servicio::getNombre)
                .collect(Collectors.toSet());

        // Agrega 5 servicios nuevos que no estén en la básica
        List<Servicio> adicionales = generarServiciosAdicionales(5, nombresBase);

        this.serviciosIncluidos = new ArrayList<>();
        this.serviciosIncluidos.addAll(base);
        this.serviciosIncluidos.addAll(adicionales);
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return serviciosIncluidos;
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio) {
        double subtotal = servicio.getPrecio();
        double total;

        int index = serviciosIncluidos.indexOf(servicio);
        if (index >= 0 && index < 5) {
            total = 0; // Gratis
        } else {
            total = subtotal; // Precio completo
        }

        return new Factura.FacturaBuilder()
                .id(UUID.randomUUID().toString())
                .fecha(LocalDateTime.now())
                .subtotal(subtotal)
                .total(total)
                .build();
    }

    // Método para generar servicios adicionales aleatorios
    private List<Servicio> generarServiciosAdicionales(int cantidad, Set<String> excluidos) {
        List<Servicios> disponibles = Arrays.stream(Servicios.values())
                .filter(s -> !excluidos.contains(s.name()))
                .collect(Collectors.toList());

        Collections.shuffle(disponibles);
        List<Servicio> nuevos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < cantidad && i < disponibles.size(); i++) {
            Servicios tipo = disponibles.get(i);
            nuevos.add(new Servicio(
                    UUID.randomUUID().toString(),
                    tipo.name(),
                    60000
            ));
        }

        return nuevos;
    }
}
