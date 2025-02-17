package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CrearCitaController {
	@javafx.fxml.FXML
	private TextField paciente_cita_text;
	@javafx.fxml.FXML
	private TextField login_showPassword;
	@javafx.fxml.FXML
	private Button acept_add_cita_btn;
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private Button cancel_add_cita_btn;
	@javafx.fxml.FXML
	private DatePicker fecha_cita_add;

	@javafx.fxml.FXML
	public void addCita(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void cancelAddCita(ActionEvent actionEvent) {
	}
}
