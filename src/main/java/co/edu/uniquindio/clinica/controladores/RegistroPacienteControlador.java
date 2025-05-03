package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroPacienteControlador {

    Clinica clinica = Clinica.getInstancia();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbSuscripcion;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    // Método para obtener la suscripción seleccionada
    @Getter
    private Suscripcion suscripcionSeleccionada;

    private ObservableList<Suscripcion> listaSuscripcion;

    public RegistroPacienteControlador(){
        listaSuscripcion = FXCollections.observableArrayList();
        clinica = Clinica.getInstancia();
    }


    @FXML
    void registrarPaciente(ActionEvent event) {
        try {
            clinica.registrarPaciente(
                    txtNombre.getText(),
                    txtCedula.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText()
); //System.out.println(txtCedula.getText() + " " + txtEmail.getText() + " " + txtTelefono.getText());
            limpiarCampos();
        } catch (Exception e) {
            clinica.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtEmail.clear();
        txtTelefono.clear();
        cmbSuscripcion.setValue(null);
    }

    @FXML
    void initialize() {
        cmbSuscripcion.getItems().addAll(
                Arrays.stream(TipoSuscripcion.values())
                        .map(TipoSuscripcion::getNombre)
                        .toList()
        );

    }


}

