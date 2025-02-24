package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.api.UsuarioApiClient;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Usuario;
import es.cheste.ad_sanidad_di.repository.UsuarioRepository;
import es.cheste.ad_sanidad_di.service.UsuarioServicie;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
	@javafx.fxml.FXML
	private Button login_paciente_view_boton;
	@javafx.fxml.FXML
	private Button register_boton;

	@javafx.fxml.FXML
	public void loginAccount(ActionEvent actionEvent) {
		String idStr = login_usuario.getText();
		String contraseña = login_contraseña.getText();

		if (idStr.isEmpty() || contraseña.isEmpty()) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos");
			return;
		}

		try {
			long id = Long.parseLong(idStr);
			MedicoApiClient cliente = new MedicoApiClient();
			Medico medicoEncontrado = cliente.verificarMedico(id, contraseña);
			if (medicoEncontrado != null) {
				abrirVentanaPrincipal( medicoEncontrado);
			} else {
				mostrarError("Credenciales incorrectas", "ID o contraseña inválidos");
			}
		} catch (NumberFormatException e) {
			mostrarError("Formato incorrecto", "El ID debe ser un número");
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

	private void abrirVentanaPrincipal( Medico medico) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PanelMedico.fxml"));
			Parent root = loader.load();

			PanelMedicoController controller = loader.getController();
			controller.setNombreMedico(medico.getNombre());
			controller.setNombreMedicoSuperior(medico.getNombre());
			controller.setId_medico(medico.getId());
			controller.cargarMedico(medico);
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

	@javafx.fxml.FXML
	public void ventanaLoginPaciente(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/LoginPaciente.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage) login_paciente_view_boton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Login Paciente");
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana de login de paciente");
		}
	}

	@javafx.fxml.FXML
	public void registerAccount(ActionEvent actionEvent) {
	}

    @javafx.fxml.FXML
    public void salirVentana(Event event) {
    }
}
