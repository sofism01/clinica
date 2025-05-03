package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaPacientesControlador implements Initializable {

    Clinica clinica = Clinica.getInstancia();

    private ObservableList<Paciente> listaPacientes;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Paciente, String> colEmailPaciente;

    @FXML
    private TableColumn<Paciente, String> colIdPaciente;

    @FXML
    private TableColumn<Paciente, String> colNombrePaciente;

    @FXML
    private TableColumn<Paciente, String> colSuscripcionPaciente;

    @FXML
    private TableColumn<Paciente, String> colTelefonoPaciente;

    @FXML
    private TableView<Paciente> tablaPacientes;

    public ListaPacientesControlador(){
        this.clinica = Clinica.getInstancia();

    }

    private void consultarPacientes(List<Paciente> pacientes){
        tablaPacientes.setItems(FXCollections.observableArrayList(pacientes));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        colNombrePaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefonoPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colEmailPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colSuscripcionPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuscripcion().getNombreSuscripcion().toString()));

        this.listaPacientes = FXCollections.observableArrayList();
        consultarPacientes(clinica.getPacientes());

    }
}
