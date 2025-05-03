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
           clinica.registrarCita(
                   txtCedula.getText(),
                   cmbServicio.getValue(),
                   dpFecha.getValue(),
                   cmbHora.getValue()

           );

            limpiarCampos();

        } catch (Exception e) {
            clinica.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtCedula.clear();
        cmbServicio.getItems().clear();
        dpFecha.setValue(null);
        cmbHora.setValue(null);

    }


    @FXML
    void initialize() {
        cmbHora.getItems().addAll(clinica.generarHorarioCitas());
        cmbServicio.getItems().addAll(TipoServicio.values());
    }
}