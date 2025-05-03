package co.edu.uniquindio.clinica.enums;

public enum TipoSuscripcion {
    BASICA("Básica"),
    PREMIUM("Premium"),
    SUPREME("Supreme");

    private final String nombre;

    TipoSuscripcion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
