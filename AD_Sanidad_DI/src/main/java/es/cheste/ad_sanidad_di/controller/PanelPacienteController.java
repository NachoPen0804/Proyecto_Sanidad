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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PanelPacienteController {

	@FXML
	private Label citas_pend_paciente;
	@FXML
	private TableView<Visita> tablaCitas;
	@FXML
	private AnchorPane main_form;
	@FXML
	private Label nav_pacienteID;
	@FXML
	private Button chn_pass_btn;
	@FXML
	private Label current_form;
	@FXML
	private TableColumn<Visita, String> id_medico_tabla;
	@FXML
	private Button cerrarSesion_btn;
	@FXML
	private AnchorPane panel_view_medicos;
	@FXML
	private TableColumn<Visita, Long> id_tabla;
	@FXML
	private TableColumn<Visita, LocalDate> fecha_tabla;
	@FXML
	private Label nombre_paciente;
	@FXML
	private Circle top_profile;
	@FXML
	private Label nombre_paciente_superior;
	@FXML
	private TableColumn<Visita, String> hora_tabla;
	@FXML
	private TableColumn<Visita, String> minuto_tabla;

	private Paciente paciente;
	private final VisitaApiCliente visitaApi = new VisitaApiCliente();

	public void cargarPaciente(Paciente pacienteLogin) {
		this.paciente = pacienteLogin;
		setNombrePaciente(pacienteLogin.getNombre());
		setIdPaciente(pacienteLogin.getId());
		cargarDatosCitas();
	}

	@FXML
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

	@FXML
	public void ventanaCambiarContrasenya(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PasswordEditPaciente.fxml"));
			Parent root = loader.load();

			Stage newStage = new Stage();
			newStage.setScene(new Scene(root));
			newStage.setTitle("Cambiar Contraseña");

			ModificarContrasenyaPacienteController cambiarContrasenyaController = loader.getController();
			cambiarContrasenyaController.setPaciente(paciente);

			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNombrePaciente(String nombre) {
		nombre_paciente.setText(nombre);
		nombre_paciente_superior.setText(nombre);
	}

	public void setIdPaciente(long id) {
		nav_pacienteID.setText(String.valueOf(id));
	}

	@FXML
	public void initialize() {
		id_tabla.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_medico_tabla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getNombre()));
		fecha_tabla.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		hora_tabla.setCellValueFactory(data -> new SimpleStringProperty(String.format("%02d", data.getValue().getHora())));
		minuto_tabla.setCellValueFactory(data -> new SimpleStringProperty(String.format("%02d", data.getValue().getMinuto())));

		if (paciente != null) {
			cargarDatosCitas();
		}
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