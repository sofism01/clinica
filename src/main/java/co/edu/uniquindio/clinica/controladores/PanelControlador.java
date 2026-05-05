package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PanelControlador {

    Clinica clinica = Clinica.getInstancia();

    @FXML
    private StackPane panelPrincipal;




    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroPaciente.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarRegistroCita(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroCitas.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaCitas(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listarCitas.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void regresarALogin(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clínica - Ingreso");
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
