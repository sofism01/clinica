package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Cita {
    private String id;
    private LocalDate fecha;
    private String hora;
    private Paciente paciente;
    private Factura factura;

}
