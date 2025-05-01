package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaCitasControlador {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Cita, String> colFacturaCita;

    @FXML
    private TableColumn<Cita, String> colFechaCita;

    @FXML
    private TableColumn<Cita, String> colIdCita;

    @FXML
    private TableColumn<Cita, String> colPacienteCita;

    @FXML
    private TableColumn<Cita, String> colServicioCita;

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    void initialize() {

    }
}
