package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.utils.EnvioEmail;
import javafx.scene.control.Alert;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Clinica {

    private List<Cita> citas;
    private List<Servicio> servicios;
    private List<Paciente> pacientes;
    public static Clinica clinica;
    private Set<String> cedulasRegistradas = new HashSet<>();

    //singleton
    public static Clinica getInstancia() {
        if (clinica == null) {
            clinica = new Clinica();
        }
        return clinica;
    }

    public Clinica() {
        this.citas = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    public void registrarPaciente(String nombre, String cedula, String telefono, String email) throws Exception{
        // Validar que el paciente no esté repetido por cc
        if (cedulasRegistradas.contains(cedula)) {
            throw new Exception("El número de cedula ya está registrado");
        }
            //Validar el numero de telefono
            if (telefono.length() != 10) {
                throw new Exception("El número de teléfono debe tener exactamente 10 dígitos");
            }

            for (char c : telefono.toCharArray()) {
                if (!Character.isDigit(c)) {
                    throw new Exception("El número de teléfono solo debe contener dígitos");
                }
            }
            //Validar el email
            if (!email.contains("@") || email.indexOf("@") == 0 || email.lastIndexOf(".")
                    < email.indexOf("@") + 2 || email.endsWith(".")) {
                throw new Exception("El email no tiene un formato válido");
            }


        Paciente paciente = Paciente.builder()
                .nombre(nombre)
                .cedula(cedula)
                .telefono(telefono)
                .email(email)
                .suscripcion( new SuscripcionBasica())
                .build();
        cedulasRegistradas.add(cedula);
        pacientes.add(paciente);
    }

    public void registrarCita(Cita cita, String email, Paciente paciente, Servicio servicio) throws Exception{
        for (Cita c : citas) {
            if(c.getPaciente().equals(cita.getPaciente()) && c.getFecha().equals(cita.getFecha())){
                throw new Exception("Cita en la misma fecha para el paciente");
            }
        }
        citas.add(cita);
        //Hacer el envio del email
        Factura factura = generarFactura(paciente, servicio);

        String cuerpo = String.format(
                "Hola %s,\n\nTu cita para el servicio '%s' ha sido agendada exitosamente.\n" +
                        "Fecha y hora de la cita: %s\n" +
                        "Valor a pagar: $%.2f\n\n" +
                        "Gracias.",
                paciente.getNombre(),
                servicio.getNombre(),
                factura.getFecha().toString(),
                factura.getTotal()
        );

        EnvioEmail.enviarNotificacion(email, "Confirmación de su cita", cuerpo);
    }

    public Factura generarFactura(Paciente paciente, Servicio servicio) throws Exception{
        return paciente.getSuscripcion().generarFacturaCobro(servicio);
    }

    public List<Servicio> getServiciosDisponibles() {
        return new ArrayList<>(servicios);
    }

    public List<Servicio> getServiciosDisponibles(Suscripcion suscripcion) {
        return suscripcion.getServiciosDisponibles();
    }

}
