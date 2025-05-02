package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;

public class SuscripcionFactory {

    public enum TipoSuscripcion {
        BASICA, PREMIUM
    }

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo) {
        return switch (tipo) {
            case BASICA -> new SuscripcionBasica();
            case PREMIUM -> {
                SuscripcionBasica basica = new SuscripcionBasica();
                yield new SuscripcionPremium(basica);
            }
        };
    }
}
