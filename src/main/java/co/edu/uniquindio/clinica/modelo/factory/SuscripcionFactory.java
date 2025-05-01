package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;

public class SuscripcionFactory {

    public enum TipoSuscripcion {
        BASICA, PREMIUM
    }

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo, List<Servicio> serviciosBasicos) {
        return switch (tipo) {
            case BASICA -> new SuscripcionBasica(serviciosBasicos);
            case PREMIUM -> {
                SuscripcionBasica basica = new SuscripcionBasica(serviciosBasicos);
                yield new SuscripcionPremium(basica);
            }
        };
    }
}
