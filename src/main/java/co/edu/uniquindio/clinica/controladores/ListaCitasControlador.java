package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaCitasControlador {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


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

    private ObservableList<Cita> listaCitas;

    Clinica clinica = Clinica.getInstancia();

    private void consultarPacientes(List<Cita> citas){
        tablaCitas.setItems(FXCollections.observableArrayList(citas));
    }

    public ListaCitasControlador(){
        this.clinica = Clinica.getInstancia();

    }

    @FXML
    void initialize() {
        colIdCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colFechaCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colPacienteCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre()));
        colServicioCita.setCellValueFactory(cellData -> new SimpleStringProperty());


        this.listaCitas = FXCollections.observableArrayList();
        consultarPacientes(clinica.getCitas());
    }
}
