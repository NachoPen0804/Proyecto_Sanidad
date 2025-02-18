package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginPacienteController {
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private TextField login_showPassword;
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private TextField login_usuario;
	@javafx.fxml.FXML
	private Button login_boton;
	@javafx.fxml.FXML
	private Button login_medico_boton;
	@javafx.fxml.FXML
	private PasswordField login_contraseña;
	@javafx.fxml.FXML
	private CheckBox login_checkBox;

	@javafx.fxml.FXML
	public void loginAccountPaciente(ActionEvent actionEvent) {
		String idStr = login_usuario.getText();
		String contraseña = login_contraseña.getText();

		if (idStr.isEmpty() || contraseña.isEmpty()) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos");
			return;
		}

		try {
			long id = Long.parseLong(idStr);
			PacienteApiClient cliente = new PacienteApiClient();
			Paciente pacienteEncontrado = cliente.verificarPaciente(id, contraseña);
			if (pacienteEncontrado != null) {
				abrirVentanaPrincipal(pacienteEncontrado);
			} else {
				mostrarError("Credenciales incorrectas", "ID o contraseña inválidos");
			}
		} catch (NumberFormatException e) {
			mostrarError("Formato incorrecto", "El ID debe ser un número");
		}
	}

	private void abrirVentanaPrincipal(Paciente paciente) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PanelPaciente.fxml"));
			Parent root = loader.load();

			PanelPacienteController controller = loader.getController();
			controller.cargarPaciente(paciente);
			Stage stage = (Stage) login_boton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Sistema Hospitalario - Paciente");
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana principal");
		}
	}

	@javafx.fxml.FXML
	public void loginAccount(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void loginShowPassword(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void ventanaLoginMedico(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/Login.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage) login_medico_boton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Login Paciente");
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana de login de paciente");
		}
	}
	
	

	@Deprecated
	public void cerrarSesion(ActionEvent actionEvent) {
		
	}

	@Deprecated
	public void ventanaCambiarContrasenya(ActionEvent actionEvent) {
		
	}
	private void mostrarError(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
