package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;

public class PanelMedicoController {
	@javafx.fxml.FXML
	private Label dashboard_tA;
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private Button appointments_btn;
	@javafx.fxml.FXML
	private Label date_time;
	@javafx.fxml.FXML
	private Label nav_username;
	@javafx.fxml.FXML
	private AnchorPane dashboard_form;
	@javafx.fxml.FXML
	private Circle top_profile;
	@javafx.fxml.FXML
	private Label nav_adminID;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private Label top_username;
	@javafx.fxml.FXML
	private Button profile_btn;
	@javafx.fxml.FXML
	private Button patients_btn;
	@javafx.fxml.FXML
	private Label dashboard_TP;
	@javafx.fxml.FXML
	private Label dashboard_AP;
	@javafx.fxml.FXML
	private Button dashboard_btn;
	@javafx.fxml.FXML
	private Label dashboard_IP;
	@javafx.fxml.FXML
	private Button logout_btn;


	@javafx.fxml.FXML
	private TableColumn tabla_fecha;
	@javafx.fxml.FXML
	private TableColumn tabla_id;
	@javafx.fxml.FXML
	private TableColumn tabla_medicoId;
	@javafx.fxml.FXML
	private TableColumn tabla_pacienteId;
	@javafx.fxml.FXML
	private TableView tabla_citas;

	@javafx.fxml.FXML
	public void pacientes_lista(ActionEvent actionEvent) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/cheste/ad_sanidad_di/prueba.fxml"));

			// Crear una nueva escena
			Scene scene = new Scene(root);

			// Crear una nueva ventana (Stage)
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Nueva Ventana");

			// Mostrar la nueva ventana
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	@FXML
	public void initialize() {
		tabla_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tabla_pacienteId.setCellValueFactory(new PropertyValueFactory<>("paciente.id"));
		tabla_medicoId.setCellValueFactory(new PropertyValueFactory<>("medico.id"));
		tabla_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

		cargarDatos();
	}

	private void cargarDatos() {
		List<Visita> visitasList = VisitaApiCliente.obtenerVisitas();
		ObservableList<Visita> observableVisitasList = FXCollections.observableArrayList(visitasList);
		tabla_citas.setItems(observableVisitasList);
	}
	
}
