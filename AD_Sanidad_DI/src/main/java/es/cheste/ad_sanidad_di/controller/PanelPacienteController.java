package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PanelPacienteController {
	@javafx.fxml.FXML
	private Label nombre_medico_superior;
	@javafx.fxml.FXML
	private Label citas_pend_paciente;
	@javafx.fxml.FXML
	private TableView tablaCitas;
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private Label nav_pacienteID;
	@javafx.fxml.FXML
	private Button chn_pass_btn;
	@javafx.fxml.FXML
	private TableColumn id_paciente_tabla;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private TableColumn id_medico_tabla;
	@javafx.fxml.FXML
	private Button cerrarSesion_btn;
	@javafx.fxml.FXML
	private AnchorPane panel_view_medicos;
	@javafx.fxml.FXML
	private TableColumn id_tabla;
	@javafx.fxml.FXML
	private TableColumn fecha_tabla;
	@javafx.fxml.FXML
	private Label nombre_paciente;

	@javafx.fxml.FXML
	public void cerrarSesion(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void ventanaCambiarContrasenya(ActionEvent actionEvent) {
	}
}
