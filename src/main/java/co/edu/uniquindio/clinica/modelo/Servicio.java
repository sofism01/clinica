package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Servicio {
    private String id;
    private String nombre;
    private double precio;
}
