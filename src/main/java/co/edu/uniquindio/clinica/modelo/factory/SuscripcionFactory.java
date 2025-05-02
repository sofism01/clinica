package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;

import static co.edu.uniquindio.clinica.enums.TipoSuscripcion.BASICA;
import static co.edu.uniquindio.clinica.enums.TipoSuscripcion.PREMIUM;

public class SuscripcionFactory {

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipoSuscripcion) {

        return switch (tipoSuscripcion){
            case BASICA -> new SuscripcionBasica();
            case PREMIUM -> new SuscripcionPremium();
        };

    }
}
