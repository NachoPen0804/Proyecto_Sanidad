package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	@javafx.fxml.FXML
	private TextField login_showPassword;
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private CheckBox login_checkBox;
	@javafx.fxml.FXML
	private TextField login_usuario;
	@javafx.fxml.FXML
	private Button login_boton;
	@javafx.fxml.FXML
	private PasswordField login_contraseña;

	@javafx.fxml.FXML
	public void loginAccount(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void loginShowPassword(ActionEvent actionEvent) {
	}
}
