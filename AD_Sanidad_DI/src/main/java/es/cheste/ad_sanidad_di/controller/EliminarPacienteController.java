package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EliminarPacienteController {
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private Button cancel_delete_paciente_btn;
	@javafx.fxml.FXML
	private Button acept_delete_paciente_btn;
	@javafx.fxml.FXML
	private TextField id_paciente;


	private final PacienteApiClient pacienteApiClient = new PacienteApiClient();
	private PanelMedicoController panelMedicoController;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	@javafx.fxml.FXML
	public void deletePaciente(ActionEvent actionEvent) {
		String idText = id_paciente.getText();

		if (idText.isEmpty()) {
			mostrarError("Campo vacío", "Por favor ingrese el ID del paciente");
			return;
		}

		try {
			long id = Long.parseLong(idText);
			Paciente pacienteExistente = pacienteApiClient.obtenerPacientePorId(id);
			if (pacienteExistente == null) {
				mostrarError("Paciente no encontrado", "El ID del paciente no existe");
				return;
			}

			pacienteApiClient.delete(id);
			mostrarInformacion("Paciente eliminado", "El paciente ha sido eliminado exitosamente");

			Stage stage = (Stage) acept_delete_paciente_btn.getScene().getWindow();
			stage.close();

			panelMedicoController.actualizarTablaPacientes();
		} catch (NumberFormatException e) {
			mostrarError("ID inválido", "El ID del paciente debe ser un número");
		} catch (RuntimeException e) {
			mostrarError("Error", "No se pudo eliminar el paciente: " + e.getMessage());
		}
	}

	@javafx.fxml.FXML
	public void cancelDeletePaciente(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_delete_paciente_btn.getScene().getWindow();
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
}
