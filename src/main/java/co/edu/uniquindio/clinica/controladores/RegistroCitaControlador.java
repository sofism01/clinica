package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.enums.TipoServicio;
import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.*;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class RegistroCitaControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbHora;

    @FXML
    private ComboBox<TipoServicio> cmbServicio;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtCedula;

    Clinica clinica = Clinica.getInstancia();

    private ObservableList<TipoServicio> listaServicios;

    public RegistroCitaControlador(){
        listaServicios = FXCollections.observableArrayList();
        clinica = Clinica.getInstancia();
    }

    @FXML
    void agendarCita(ActionEvent event) {
        try {
            String cedulaPaciente = txtCedula.getText();
           clinica.registrarCita(
                   cedulaPaciente,
                   cmbServicio.getValue(),
                   dpFecha.getValue(),
                   cmbHora.getValue()

           );
//el email de confirmación llega al spam del correo

            limpiarCampos();
            clinica.mostrarAlerta("Cita agendada correctamente, la factura fue enviada al email del paciente", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            clinica.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtCedula.clear();
        cmbServicio.setValue(null);
        dpFecha.setValue(null);
        cmbHora.setValue(null);

    }


    @FXML
    void initialize() {
        cmbHora.getItems().addAll(clinica.generarHorarioCitas());
        cmbServicio.getItems().addAll(TipoServicio.values());
    }
}