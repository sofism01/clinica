package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class SuscripcionPremium implements  Suscripcion{
    private List<Servicio> serviciosIncluidos;

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return serviciosIncluidos;
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio) {
        double subtotal = servicio.getPrecio();
        double totalConDescuento = subtotal * 0.5;
        return new Factura.FacturaBuilder()
                .id(UUID.randomUUID().toString())
                .fecha(LocalDateTime.now())
                .subtotal(subtotal)
                .total(totalConDescuento)
                .build();
    }
}
