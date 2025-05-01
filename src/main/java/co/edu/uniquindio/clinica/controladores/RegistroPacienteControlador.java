package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPacienteControlador {

    Clinica clinica = Clinica.getInstancia();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cmbSuscripcion;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void registrarPaciente(ActionEvent event) {
        try {
            clinica.registrarPaciente(
                    txtNombre.getText(),
            txtCedula.getText(),
            txtEmail.getText(),
            txtTelefono.getText()
);
        } catch (Exception e) {
            clinica.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtEmail.clear();
        txtTelefono.clear();
    }

    @FXML
    void initialize() {

    }
}
