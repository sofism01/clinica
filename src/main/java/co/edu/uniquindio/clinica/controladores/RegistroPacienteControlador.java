package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> cmbSuscripcion;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

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
        cmbSuscripcion.getItems().clear();
    }

    @FXML
    void initialize() {
        cmbSuscripcion.getItems().addAll("Suscripcion Basica", "Suscripcion Premium");
    }
}
