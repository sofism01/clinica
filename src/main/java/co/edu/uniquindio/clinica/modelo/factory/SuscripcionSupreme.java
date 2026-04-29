package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.enums.TipoServicio;
import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// para agregar este nuevo tipo de suscripción SUPREME se añadió tanto a la Enum "TipoSuscripcion"
// como a la Factory "SuscripcionFactory", posteriormente se creó esta nueva clase y se le asignaron los servicios
//correspondientes, en este caso todos los servicios estan incluidos en la suscripción, por lo tanto no tienen ningún costo

public class SuscripcionSupreme implements Suscripcion{
    @Override
    public TipoSuscripcion getNombreSuscripcion() {
        return TipoSuscripcion.SUPREME;
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return List.of(
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.CARDIOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.CONSULTA_GENERAL))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.OFTALMOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.ODONTOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.PSICOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.PEDIATRIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.PSIQUIATRIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.NEUMOLOGIA))
                        .build()

        );
    }

    @Override
    public Factura generarFacturaCobro(TipoServicio tipoServicio) {
        return Factura.builder()
                .id(UUID.randomUUID().toString())
                .fecha(LocalDateTime.now())
                .subtotal(tipoServicio.getPrecio())
                .total(0.0)
                .build();
    }
}
