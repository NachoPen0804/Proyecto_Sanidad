package es.cheste.proyecto_sanidad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class HelloController {

	@FXML
	private TextField login_patientID;
	@FXML
	private TextField login_showPassword;
	@FXML
	private Button login_loginBtn;
	@FXML
	private PasswordField login_password;
	@FXML
	private AnchorPane login_form;
	@FXML
	private AnchorPane main_form;
	@FXML
	private CheckBox login_checkBox;

	@FXML
    protected void onHelloButtonClick() {
		
    }

	@FXML
	public void loginAccount(ActionEvent actionEvent) {
	}

	@FXML
	public void loginShowPassword(ActionEvent actionEvent) {
	}

	@Deprecated
	public void switchPage(ActionEvent actionEvent) {
	}
}