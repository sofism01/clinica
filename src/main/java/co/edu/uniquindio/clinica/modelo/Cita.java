package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.enums.TipoServicio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cita {
    private String id;
    private LocalDate fecha;
    private String hora;
    private Paciente paciente;
    private Factura factura;
    private TipoServicio tipoServicio;

}
