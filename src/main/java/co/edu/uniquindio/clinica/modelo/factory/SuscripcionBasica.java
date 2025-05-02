package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.enums.TipoServicio;
import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.time.LocalDateTime;
import java.util.*;


public class SuscripcionBasica implements Suscripcion {
    @Override
    public TipoSuscripcion getNombreSuscripcion() {
        return TipoSuscripcion.BASICA;
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return List.of(
                Servicio.builder()
                        .precio(20000)
                        .nombre(String.valueOf(TipoServicio.OFTALMOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(String.valueOf(TipoServicio.CONSULTA_GENERAL))
                        .build(),
                Servicio.builder()
                        .precio(50000)
                        .nombre(String.valueOf(TipoServicio.NEUMOLOGIA))
                        .build(),
                Servicio.builder()
                        .precio(40000)
                        .nombre(String.valueOf(TipoServicio.PSIQUIATRIA))
                        .build()
        );
    }

    @Override
    public Factura generarFacturaCobro(TipoServicio tipoServicio) {

        double subtotal = tipoServicio.getPrecio();
        double total;

        Servicio servicio = getServiciosDisponibles().stream()
                .filter(s -> s.getNombre().equals(tipoServicio))
                .findFirst().orElse(null);

        if(servicio == null){
            total = subtotal;
        }else{
            total = servicio.getPrecio();
        }

        return Factura.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .fecha(LocalDateTime.now())
                .subtotal(subtotal)
                .total(total)
                .build();
    }
    }
