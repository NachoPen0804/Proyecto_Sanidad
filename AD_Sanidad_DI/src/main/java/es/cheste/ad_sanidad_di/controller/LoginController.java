package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.model.Usuario;
import es.cheste.ad_sanidad_di.repository.UsuarioRepository;
import es.cheste.ad_sanidad_di.service.UsuarioServicie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

	private UsuarioServicie usuarioService;

	@javafx.fxml.FXML
	public void loginAccount(ActionEvent actionEvent) {
		String usuario = login_usuario.getText();
		String contraseña = login_contraseña.getText();

		if (usuario.isEmpty() || contraseña.isEmpty()) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos");
			return;
		}

		Usuario usuarioEncontrado = usuarioService.findByNombre(usuario);

		if (usuarioEncontrado != null && usuarioEncontrado.getContraseña().equals(contraseña)) {
			abrirVentanaPrincipal();
		} else {
			mostrarError("Credenciales incorrectas", "Usuario o contraseña inválidos");
		}
	}

	@javafx.fxml.FXML
	public void loginShowPassword(ActionEvent actionEvent) {
		if (login_checkBox.isSelected()) {
			login_showPassword.setText(login_contraseña.getText());
			login_showPassword.setVisible(true);
			login_contraseña.setVisible(false);
		} else {
			login_contraseña.setText(login_showPassword.getText());
			login_contraseña.setVisible(true);
			login_showPassword.setVisible(false);
		}
	}

	private void abrirVentanaPrincipal() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/cheste/ad_sanidad_di/PanelMedico.fxml"));
			Stage stage = (Stage) login_boton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Sistema Hospitalario");
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana principal");
		}
	}

	private void mostrarError(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
