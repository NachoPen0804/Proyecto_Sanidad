package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

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

	private long id_medico;

	private final PacienteApiClient pacienteApiClient = new PacienteApiClient();
	private final VisitaApiCliente visitaApiClient = new VisitaApiCliente();
	private final MedicoApiClient medicoApiClient = new MedicoApiClient();
	private PanelMedicoController panelMedicoController;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	@javafx.fxml.FXML
	public void addCita(ActionEvent actionEvent) {
		String pacienteIdText = paciente_cita_text.getText();
		LocalDate fechaCita = fecha_cita_add.getValue();

		if (pacienteIdText.isEmpty() || fechaCita == null) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos");
			return;
		}

		try {
			long pacienteId = Long.parseLong(pacienteIdText);
			Paciente paciente = pacienteApiClient.obtenerPacientePorId(pacienteId);
			if (paciente == null) {
				mostrarError("Paciente no encontrado", "El ID del paciente no existe");
				return;
			}

			Medico medico = medicoApiClient.obtenerMedicoPorId(id_medico);
			if (medico == null) {
				mostrarError("Médico no encontrado", "El ID del médico no existe");
				return;
			}

			visitaApiClient.create(pacienteId, id_medico, fechaCita);

			mostrarInformacion("Cita creada", "La cita ha sido creada exitosamente");

			// Cierra la ventana de creación de citas
			Stage stage = (Stage) acept_add_cita_btn.getScene().getWindow();
			stage.close();

			// Actualiza la tabla de citas en PanelMedicoController
			panelMedicoController.actualizarTablaCitas();
		} catch (NumberFormatException e) {
			mostrarError("ID inválido", "El ID del paciente debe ser un número");
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
	public void cancelAddCita(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_add_cita_btn.getScene().getWindow();
		stage.close();
	}


	private void mostrarInformacion(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	public void setIdMedico(long id_medico) {
		this.id_medico = id_medico;
	}
}
