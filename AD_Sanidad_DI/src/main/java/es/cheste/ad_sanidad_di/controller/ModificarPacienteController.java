package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModificarPacienteController {
	@javafx.fxml.FXML
	private TextField login_showPassword;
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private TextField paciente_apellidos_text;
	@javafx.fxml.FXML
	private Button cancel_edit_paciente_btn;
	@javafx.fxml.FXML
	private TextField paciente_pueblo_text;
	@javafx.fxml.FXML
	private Button acept_edit_paciente_btn;
	@javafx.fxml.FXML
	private TextField paciente_nombre_text;
	@javafx.fxml.FXML
	private TextField paciente_id_text;

	private final PacienteApiClient pacienteApiClient = new PacienteApiClient();
	private PanelMedicoController panelMedicoController;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	@Deprecated
    public void modificarPaciente(ActionEvent actionEvent) {
		String idText = paciente_id_text.getText();
		String nombre = paciente_nombre_text.getText();
		String apellidos = paciente_apellidos_text.getText();
		String pueblo = paciente_pueblo_text.getText();

		if (idText.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || pueblo.isEmpty()) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos");
			return;
		}

		try {
			long id = Long.parseLong(idText);
			Paciente pacienteExistente = pacienteApiClient.obtenerPacientePorId(id);
			if (pacienteExistente == null) {
				mostrarError("Paciente no encontrado", "El ID del paciente no existe");
				return;
			}

			Paciente paciente = new Paciente();
			paciente.setId(id);
			paciente.setNombre(nombre);
			paciente.setApellidos(apellidos);
			paciente.setPueblo_residencia(pueblo);

			pacienteApiClient.actualizarPaciente(paciente);
			mostrarInformacion("Paciente modificado", "El paciente ha sido modificado exitosamente");
			
			Stage stage = (Stage) acept_edit_paciente_btn.getScene().getWindow();
			stage.close();
		} catch (NumberFormatException e) {
			mostrarError("ID inválido", "El ID del paciente debe ser un número");
		} catch (RuntimeException e) {
			mostrarError("Error", "No se pudo modificar el paciente: " + e.getMessage());
		}
	}

	@Deprecated
	public void cancerarModificarPaciente(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_edit_paciente_btn.getScene().getWindow();
		stage.close();
	}

	private void mostrarError(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	private void mostrarInformacion(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	@javafx.fxml.FXML
	public void addCita(ActionEvent actionEvent) {
	}

	@javafx.fxml.FXML
	public void cancelAddCita(ActionEvent actionEvent) {
	}
}
