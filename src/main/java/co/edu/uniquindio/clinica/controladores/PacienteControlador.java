package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PacienteControlador {

    @FXML
    private Label lblBienvenida;

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colIdCita;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colHora;

    @FXML
    private TableColumn<Cita, String> colServicio;

    @FXML
    private TableColumn<Cita, String> colAseguradora;

    @FXML
    private TableColumn<Cita, String> colPago;

    @FXML
    private Button btnPagar;

    @FXML
    private Label lblDetalleCita;

    @FXML
    private Label lblAseguradora;

    @FXML
    private Label lblPago;

    private final Clinica clinica = Clinica.getInstancia();
    private Paciente paciente;

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        lblBienvenida.setText("Bienvenido, " + paciente.getNombre());
        cargarCitas();
    }

    @FXML
    public void initialize() {
        colIdCita.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha().toString()));
        colHora.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getHora()));
        colServicio.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipoServicio().getNombre()));
        colAseguradora.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPaciente().getSuscripcion().getNombreSuscripcion().getNombre()));
        colPago.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFactura().isPagado() ? "Pagado" : "Pendiente"));

        tablaCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> mostrarDetalle(newSelection));
        btnPagar.setDisable(true);
    }

    private void cargarCitas() {
        if (paciente == null) {
            return;
        }
        List<Cita> citasPaciente = clinica.getCitas().stream()
                .filter(cita -> cita.getPaciente().getCedula().equals(paciente.getCedula()))
                .filter(cita -> !cita.getFactura().isPagado())
                .toList();
        tablaCitas.setItems(FXCollections.observableArrayList(citasPaciente));
        if (citasPaciente.isEmpty()) {
            lblDetalleCita.setText("No hay citas asignadas.");
            lblAseguradora.setText("-");
            lblPago.setText("-");
        }
    }

    private void mostrarDetalle(Cita cita) {
        if (cita == null) {
            lblDetalleCita.setText("Seleccione una cita para ver más detalles.");
            lblAseguradora.setText("-");
            lblPago.setText("-");
            btnPagar.setDisable(true);
            return;
        }

        lblDetalleCita.setText(String.format("%s %s - %s", cita.getFecha(), cita.getHora(), cita.getTipoServicio().getNombre()));
        lblAseguradora.setText(cita.getPaciente().getSuscripcion().getNombreSuscripcion().getNombre());
        lblPago.setText(cita.getFactura().isPagado() ? "Pagado" : String.format("$%.2f", cita.getFactura().getTotal()));
        btnPagar.setDisable(cita.getFactura().isPagado() || cita.getFactura().getTotal() <= 0);
    }

    @FXML
    void pagarCopago(ActionEvent event) {
        Cita cita = tablaCitas.getSelectionModel().getSelectedItem();
        if (cita == null) {
            clinica.mostrarAlerta("Seleccione una cita primero.", Alert.AlertType.WARNING);
            return;
        }
        if (cita.getFactura().isPagado()) {
            clinica.mostrarAlerta("Esta cita ya fue pagada.", Alert.AlertType.INFORMATION);
            return;
          
        }
        if (cita.getFactura().getTotal() <= 0) {
            clinica.mostrarAlerta("No hay copago para esta cita.", Alert.AlertType.INFORMATION);
            return;
        }

        cita.getFactura().setPagado(true);
        cargarCitas();
        tablaCitas.getSelectionModel().clearSelection();
        mostrarDetalle(null);
        clinica.mostrarAlerta("Copago pagado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clínica - Ingreso");
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setResizable(false);
        } catch (IOException e) {
            clinica.mostrarAlerta("No se pudo cerrar sesión.", Alert.AlertType.ERROR);
        }
    }
}
