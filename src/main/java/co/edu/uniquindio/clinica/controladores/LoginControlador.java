package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {

    @FXML
    private ChoiceBox<String> cmbRol;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    private final Clinica clinica = Clinica.getInstancia();

    @FXML
    public void initialize() {
        cmbRol.getItems().addAll("Médico", "Paciente");
        cmbRol.setValue("Médico");
    }

    @FXML
    void ingresar(ActionEvent event) {
        try {
            String rol = cmbRol.getValue();
            String usuario = txtUsuario.getText().trim();
            String password = txtPassword.getText().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                throw new Exception("Ingrese usuario y contraseña.");
            }

            Parent root;
            if ("Médico".equals(rol)) {
                if (!clinica.validarMedico(usuario, password)) {
                    throw new Exception("Credenciales de médico incorrectas.");
                }
                root = FXMLLoader.load(getClass().getResource("/panel.fxml"));
            } else {
                Paciente paciente = clinica.validarPaciente(usuario, password);
                if (paciente == null) {
                    throw new Exception("Credenciales de paciente incorrectas.");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/paciente.fxml"));
                root = loader.load();
                PacienteControlador controlador = loader.getController();
                controlador.setPaciente(paciente);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clínica - " + rol);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de acceso");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
