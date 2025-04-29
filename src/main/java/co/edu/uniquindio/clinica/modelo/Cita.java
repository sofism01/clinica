package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Cita {
    private String id;
    private LocalDateTime fecha;
    private Paciente paciente;
    private Factura factura;

}
