package co.edu.uniquindio.clinica.enums;

import lombok.Getter;

@Getter
public enum TipoServicio {
    CONSULTA_GENERAL("Consulta general", 50000),
    CARDIOLOGIA("Cardiología", 105000),
    OFTALMOLOGIA("Oftalmología", 85000),
    PSICOLOGIA("Psicología", 75000),
    ODONTOLOGIA("Odontología", 90000),
    PEDIATRIA("Pediatria", 85000),
    PSIQUIATRIA("Psiquatria", 75000),
    NEUMOLOGIA("Neumología", 85000);

    private final String nombre;
    private final float precio;

    TipoServicio(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
