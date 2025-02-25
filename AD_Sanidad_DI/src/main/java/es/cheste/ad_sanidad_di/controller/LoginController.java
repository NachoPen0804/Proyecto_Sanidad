package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.model.Medico;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

@Controller
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
				abrirVentanaPrincipal(medicoEncontrado);
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

	private void abrirVentanaPrincipal(Medico medico) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PanelMedico.fxml"));
			Parent root = loader.load();

			PanelMedicoController controller = loader.getController();
			controller.setNombreMedico(medico.getNombre());
			controller.setNombreMedicoSuperior(medico.getNombre());
			controller.setId_medico(medico.getId());
			controller.cargarMedico(medico);

			// Crear un nuevo Stage
			Stage newStage = new Stage();
			newStage.initStyle(StageStyle.UNDECORATED); // Sin barra de título
			newStage.setScene(new Scene(root));
			newStage.setTitle("Sistema Hospitalario");

			// Posicionar la ventana en la esquina izquierda de la pantalla
			newStage.setX(100);  // Extremo izquierdo
			newStage.setY(100);  // Parte superior (ajustable según prefieras)

			// Cerrar la ventana de login
			Stage loginStage = (Stage) login_boton.getScene().getWindow();
			loginStage.close();

			// Mostrar la nueva ventana
			newStage.show();
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana principal");
			e.printStackTrace();
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

			Stage newStage = new Stage();
			newStage.initStyle(StageStyle.UNDECORATED);
			newStage.setScene(new Scene(root));
			newStage.setTitle("Login Paciente");

			Stage loginStage = (Stage) login_paciente_view_boton.getScene().getWindow();
			loginStage.close();

			newStage.show();
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana de login de paciente");
		}
	}

	@javafx.fxml.FXML
	public void salirVentana(Event event) {
		Stage stage = (Stage) login_boton.getScene().getWindow();
		stage.close();
	}

	@javafx.fxml.FXML
	public void addMedico(ActionEvent actionEvent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/MedicoAdd.fxml"));
			Parent root = loader.load();

			Stage newStage = new Stage();
			newStage.initStyle(StageStyle.UNDECORATED);
			newStage.setScene(new Scene(root));
			newStage.setTitle("Registro de Médico");

			Stage loginStage = (Stage) register_boton.getScene().getWindow();
			loginStage.close();

			newStage.show();
		} catch (Exception e) {
			mostrarError("Error", "No se pudo cargar la ventana de registro de médico");
		}
	}
}