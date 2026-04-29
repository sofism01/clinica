package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "cedula")
public class Paciente {
    private String nombre;
    private String cedula;
    private String telefono;
    private String email;
    private Suscripcion suscripcion;
    private String username;
    private String password;
}
