package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.enums.TipoServicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.utils.EnvioEmail;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Window;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Getter
public class Clinica {

    private List<Cita> citas;
    private List<Servicio> servicios;
    private List<Paciente> pacientes;
    private final String medicoUsuario = "medico";
    private final String medicoPassword = "doctor123";
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

    public boolean validarMedico(String usuario, String password) {
        return medicoUsuario.equals(usuario) && medicoPassword.equals(password);
    }

    public Paciente validarPaciente(String usuario, String password) {
        return pacientes.stream()
                .filter(p -> usuario.equals(p.getUsername()) && password.equals(p.getPassword()))
                .findFirst()
                .orElse(null);
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.initModality(Modality.APPLICATION_MODAL);
        Window owner = Window.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
        if (owner != null) {
            alert.initOwner(owner);
        }
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    public void registrarPaciente(String nombre, String cedula, String telefono, String email, Suscripcion suscripcion) throws Exception{
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
        //Validar los campos no sean vacios
        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son necesarios");
        }

        if (suscripcion == null) {
            throw new Exception("Seleccione una suscripcion");
        }

        Paciente paciente = Paciente.builder()
                .nombre(nombre)
                .cedula(cedula)
                .telefono(telefono)
                .email(email)
                .suscripcion(suscripcion)
                .username(cedula)
                .password(cedula)
                .build();
        cedulasRegistradas.add(cedula);
        pacientes.add(paciente);
    }

    public void registrarCita(String idPaciente, TipoServicio tipoServicio, LocalDate dia, String hora) throws Exception{
        Paciente paciente = clinica.getPacientes().stream()
                .filter(p -> p.getCedula().equals(idPaciente))
                .findFirst()
                .orElseThrow(() -> new Exception("Paciente no encontrado"));

//Validar los campos no sean vacios
        if (idPaciente.isEmpty() || tipoServicio==null || dia==null || hora==null) {
            throw new Exception("Todos los campos son necesarios");
        }

        if (dia.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            throw new Exception("No se puede registrar una cita en una fecha pasada");
        }

        for (Cita c : citas) {
            if (c.getPaciente().equals(paciente) &&
                    c.getFecha().equals(dia) &&
                    c.getHora().equals(hora)) {
                throw new Exception("Cita ya existe para este paciente en esa fecha y hora");
            }
        }

        Factura factura = paciente.getSuscripcion().generarFacturaCobro(tipoServicio);

        Cita cita = Cita.builder()
                .id(UUID.randomUUID().toString())
                .fecha(dia)
                .hora(hora)
                .paciente(paciente)
                .factura(factura)
                .tipoServicio(tipoServicio)
                .build();

        citas.add(cita);

        String cuerpo = String.format(
                "Hola %s,\n\nTu cita para el servicio '%s' ha sido agendada exitosamente.\n" +
                        "Fecha y hora de la cita: %s %s\n" +
                        "Valor a pagar: $%.2f\n\nGracias.",
                paciente.getNombre(),
                tipoServicio.getNombre(),
                dia.toString(),
                hora,
                factura.getTotal()
        );

        EnvioEmail.enviarNotificacion(paciente.getEmail(), "Confirmación de su cita", cuerpo);
    }

    public Factura generarFactura(Paciente paciente, TipoServicio tipoServicio) throws Exception{
        return paciente.getSuscripcion().generarFacturaCobro(tipoServicio);
    }

    public List<Servicio> getServiciosDisponibles() {
        return new ArrayList<>(servicios);
    }

    public List<Servicio> getServiciosDisponibles(Suscripcion suscripcion) {
        return suscripcion.getServiciosDisponibles();
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        for (Paciente p : clinica.getPacientes()) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    public List<String> generarHorarioCitas(){
        List<String> horarios = new ArrayList<>();
        for (int i = 7; i < 17; i++) {
            for (int min = 0; min < 60; min += 15) {
                int hour12 = (i % 12 == 0) ? 12 : i % 12;
                String amPm = (i < 12) ? "AM" : "PM";
                String minuto = (min < 10) ? "0" + min : String.valueOf(min);
                String horaFormateada = hour12 + ":" + minuto + " " + amPm;
                horarios.add(horaFormateada);
            }
        }
        return horarios;
    }


    public void eliminarCita(String id) {
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).getId().equals(id)) {
                citas.remove(i);
            }
        }
    }



}
