package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CrearCitaController {
	@FXML
	private TextField login_showPassword;
	@FXML
	private AnchorPane login_form;

	private long id_medico;

	private final PacienteApiClient pacienteApiClient = new PacienteApiClient();
	private final VisitaApiCliente visitaApiClient = new VisitaApiCliente();
	private final MedicoApiClient medicoApiClient = new MedicoApiClient();
	private PanelMedicoController panelMedicoController;

	@FXML
	private TextField paciente_cita_text;
	@FXML
	private Button acept_add_cita_btn;
	@FXML
	private Button cancel_add_cita_btn;
	@FXML
	private DatePicker fecha_cita_add;
	@FXML
	private ChoiceBox<String> opcion_hora_cita;
	@FXML
	private ChoiceBox<String> opcion_minuto_cita;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	public void setIdMedico(long id_medico) {
		this.id_medico = id_medico;
	}

	@FXML
	public void initialize() {
		// Configurar ChoiceBox para las horas (00-23)
		opcion_hora_cita.setItems(FXCollections.observableArrayList(
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23"
		));

		// Configurar ChoiceBox para los minutos (00, 15, 30, 45)
		opcion_minuto_cita.setItems(FXCollections.observableArrayList(
				"00", "15", "30", "45"
		));
	}

	@FXML
	public void addCita(ActionEvent actionEvent) {
		String pacienteIdText = paciente_cita_text.getText();
		LocalDate fechaCita = fecha_cita_add.getValue();
		String horaSeleccionada = opcion_hora_cita.getValue();
		String minutoSeleccionado = opcion_minuto_cita.getValue();

		// Validar campos vacíos
		if (pacienteIdText.isEmpty() || fechaCita == null || horaSeleccionada == null || minutoSeleccionado == null) {
			mostrarError("Campos vacíos", "Por favor complete todos los campos.");
			return;
		}

		// Validar que la fecha no sea anterior a hoy
		if (fechaCita.isBefore(LocalDate.now())) {
			mostrarError("Fecha inválida", "La fecha no puede ser anterior a hoy.");
			return;
		}

		try {
			long pacienteId = Long.parseLong(pacienteIdText);
			int hora = Integer.parseInt(horaSeleccionada);
			int minuto = Integer.parseInt(minutoSeleccionado);

			Paciente paciente = pacienteApiClient.obtenerPacientePorId(pacienteId);
			if (paciente == null) {
				mostrarError("Paciente no encontrado", "El ID del paciente no existe.");
				return;
			}

			Medico medico = medicoApiClient.obtenerMedicoPorId(id_medico);
			if (medico == null) {
				mostrarError("Médico no encontrado", "El ID del médico no existe.");
				return;
			}

			// Crear la cita con hora y minuto
			visitaApiClient.create(pacienteId, id_medico, fechaCita, hora, minuto);

			mostrarInformacion("Cita creada", "La cita ha sido creada exitosamente.");

			// Cerrar la ventana
			Stage stage = (Stage) acept_add_cita_btn.getScene().getWindow();
			stage.close();

			// Actualizar la tabla de citas
			if (panelMedicoController != null) {
				panelMedicoController.actualizarTablaCitas();
			}
		} catch (NumberFormatException e) {
			mostrarError("ID inválido", "El ID del paciente debe ser un número.");
		} catch (Exception e) {
			mostrarError("Error", "No se pudo crear la cita: " + e.getMessage());
		}
	}

	@FXML
	public void cancelAddCita(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_add_cita_btn.getScene().getWindow();
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