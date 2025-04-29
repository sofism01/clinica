package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;

public class SuscripcionFactory {
    public enum TipoSuscripcion {
        BASICA, PREMIUM
    }

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo, List<Servicio> servicios) {
        return switch (tipo) {
            case BASICA -> new SuscripcionBasica(servicios);
            case PREMIUM -> new SuscripcionPremium(servicios);
        };
    }
}
