package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AñadirPacienteController {
	@javafx.fxml.FXML
	private TextField login_showPassword;
	@javafx.fxml.FXML
	private AnchorPane login_form;
	@javafx.fxml.FXML
	private Button acept_add_paciente_btn;
	@javafx.fxml.FXML
	private TextField paciente_apellidos_text;
	@javafx.fxml.FXML
	private TextField paciente_pueblo_text;
	@javafx.fxml.FXML
	private TextField paciente_nombre_text;
	@javafx.fxml.FXML
	private Button cancel_add_paciente_btn;
	private final PacienteApiClient pacienteApiClient = new PacienteApiClient();
	
	private PanelMedicoController panelMedicoController;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	@Deprecated
	public void addPaciente(ActionEvent actionEvent) {
		String nombre = paciente_nombre_text.getText();
		String apellidos = paciente_apellidos_text.getText();
		String pueblo = paciente_pueblo_text.getText();

		if (nombre.isEmpty() || apellidos.isEmpty() || pueblo.isEmpty()) {
			return;
		}

		Paciente paciente = new Paciente();
		paciente.setNombre(nombre);
		paciente.setApellidos(apellidos);
		paciente.setPueblo_residencia(pueblo);

		try {
			pacienteApiClient.crearPaciente(paciente);
			mostrarInformacion("Paciente añadido", "El paciente ha sido añadido exitosamente");
			panelMedicoController.actualizarTablaPacientes();
			Stage stage = (Stage) acept_add_paciente_btn.getScene().getWindow();
			stage.close();
		} catch (RuntimeException e) {
		}
	}

	@Deprecated
	public void cancelAddPaciente(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_add_paciente_btn.getScene().getWindow();
		stage.close();
	}
	
	private void mostrarInformacion(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		System.out.println(mensaje);
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
