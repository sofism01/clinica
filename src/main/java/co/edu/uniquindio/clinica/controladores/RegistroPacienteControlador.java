package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionSupreme;
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
            String tipo = cmbSuscripcion.getValue();
            Suscripcion suscripcion;

            if (tipo.equals("Básica")) {
                suscripcion = new SuscripcionBasica();
            } else if (tipo.equals("Premium")) {
                suscripcion = new SuscripcionPremium();
            }  else if (tipo.equals("Supreme")) {
                suscripcion = new SuscripcionSupreme();
            }else {
                throw new IllegalArgumentException("Tipo de suscripción no válido");
            }

            clinica.registrarPaciente(
                    txtNombre.getText(),
                    txtCedula.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    suscripcion);
            //System.out.println(txtCedula.getText() + " " + txtEmail.getText() + " " + txtTelefono.getText());

            limpiarCampos();
            clinica.mostrarAlerta("Paciente registrado correctamente", Alert.AlertType.INFORMATION);
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

