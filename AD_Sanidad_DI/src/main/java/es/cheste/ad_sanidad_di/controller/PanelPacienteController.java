package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PanelPacienteController {
	@javafx.fxml.FXML
	private Label citas_pend_paciente;
	@javafx.fxml.FXML
	private TableView<Visita> tablaCitas;
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private Label nav_pacienteID;
	@javafx.fxml.FXML
	private Button chn_pass_btn;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private TableColumn<Visita, String> id_medico_tabla;
	@javafx.fxml.FXML
	private Button cerrarSesion_btn;
	@javafx.fxml.FXML
	private AnchorPane panel_view_medicos;
	@javafx.fxml.FXML
	private TableColumn<Visita, Long> id_tabla;
	@javafx.fxml.FXML
	private TableColumn<Visita, LocalDate> fecha_tabla;
	@javafx.fxml.FXML
	private Label nombre_paciente;

	private Paciente paciente;

	private final VisitaApiCliente visitaApi = new VisitaApiCliente();
    @FXML
    private Circle top_profile;
	@FXML
	private Label nombre_paciente_superior;
	@FXML
	private TableColumn hora_tabla;

	public void cargarPaciente(Paciente pacienteLogin) {
		this.paciente = pacienteLogin;
		setNombrePaciente(pacienteLogin.getNombre());
		setIdPaciente(pacienteLogin.getId());
		cargarDatosCitas();
	}


	@javafx.fxml.FXML
	public void cerrarSesion(ActionEvent actionEvent) {
		Stage stage = (Stage) cerrarSesion_btn.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/Login.fxml"));
			Parent root = loader.load();

			Stage loginStage = new Stage();
			loginStage.setScene(new Scene(root));
			loginStage.setTitle("Inicio de Sesión");
			loginStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@javafx.fxml.FXML
	public void ventanaCambiarContrasenya(ActionEvent actionEvent) {
	}

	public void setNombrePaciente(String nombre) {
		nombre_paciente.setText(nombre);
	}

	public void setIdPaciente(long id) {
		nav_pacienteID.setText(String.valueOf(id));
	}

	@FXML
	public void initialize() {
		id_tabla.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_medico_tabla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getNombre()));
		fecha_tabla.setCellValueFactory(new PropertyValueFactory<>("fecha"));
	}
	private void cargarDatosCitas() {
		List<Visita> todasLasVisitas = visitaApi.obtenerVisitas();
		List<Visita> visitasDelPaciente = todasLasVisitas.stream()
				.filter(visita -> visita.getPaciente().getId() == paciente.getId())
				.collect(Collectors.toList());
		ObservableList<Visita> visitasObservableList = FXCollections.observableArrayList(visitasDelPaciente);
		tablaCitas.setItems(visitasObservableList);
	}
}
