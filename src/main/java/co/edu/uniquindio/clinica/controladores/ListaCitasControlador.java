package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Window;

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
    void cancelarCita(ActionEvent event) {
        // Obtener la cita seleccionada
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();

        if (citaSeleccionada != null) {
            // Eliminar la cita de la clínica
            clinica.eliminarCita(citaSeleccionada.getId());

            // Refrescar la tabla
            consultarPacientes(clinica.getCitas());
        } else {
            // Puedes mostrar una alerta si no hay selección
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.initModality(Modality.APPLICATION_MODAL);
            Window owner = Window.getWindows().stream()
                    .filter(Window::isShowing)
                    .findFirst()
                    .orElse(null);
            if (owner != null) {
                alerta.initOwner(owner);
            }
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor seleccione una cita para cancelar.");
            alerta.show();
        }
    }



    @FXML
    void initialize() {
        colIdCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colFechaCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colPacienteCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre()));
        colServicioCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getSuscripcion().getNombreSuscripcion().toString()));

        this.listaCitas = FXCollections.observableArrayList();
        consultarPacientes(clinica.getCitas());


        }


}
