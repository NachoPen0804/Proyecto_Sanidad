package es.cheste.ad_sanidad_di.controller;


import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.application.Platform;
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

import java.time.LocalDate;
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
	@FXML
	private TableColumn<Visita, Long> id_tabla;
	@FXML
	private TableView<Visita> tablaCitas;

	@FXML
	private TableColumn<Visita, String> id_paciente_tabla;
	@FXML
	private TableColumn<Visita, String> id_medico_tabla;
	@FXML
	private TableColumn<Visita, LocalDate> fecha_tabla;
	
	private final VisitaApiCliente visitaApi = new VisitaApiCliente();


	@Deprecated
	public void pacientes_lista(ActionEvent actionEvent) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/cheste/ad_sanidad_di/prueba.fxml"));

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Nueva Ventana");

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@FXML
	public void initialize() {
		id_tabla.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_paciente_tabla.setCellValueFactory(new PropertyValueFactory<>("paciente"));
		id_medico_tabla.setCellValueFactory(new PropertyValueFactory<>("medico"));
		fecha_tabla.setCellValueFactory(new PropertyValueFactory<>("fecha"));


		cargarDatos();
	}

	private void cargarDatos() {

		List<Visita> visitas = visitaApi.obtenerVisitas();
		ObservableList<Visita> visitasObservableList = FXCollections.observableArrayList(visitas);
		tablaCitas.setItems(visitasObservableList);
	}

}
