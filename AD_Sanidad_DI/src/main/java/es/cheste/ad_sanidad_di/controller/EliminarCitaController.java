package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EliminarCitaController {
	@javafx.fxml.FXML
	private Button cancel_delete_cita_btn;
	@javafx.fxml.FXML
	private Button acept_delete_cita_btn;
	@javafx.fxml.FXML
	private TextField id_cita;

	private final VisitaApiCliente visitaApiClient = new VisitaApiCliente();
	private PanelMedicoController panelMedicoController;

	public void setPanelMedicoController(PanelMedicoController panelMedicoController) {
		this.panelMedicoController = panelMedicoController;
	}

	@javafx.fxml.FXML
	public void loginAccount(ActionEvent actionEvent) {
		
	}

	@javafx.fxml.FXML
	public void deleteCita(ActionEvent actionEvent) {
		String idCitaText = id_cita.getText();

		if (idCitaText.isEmpty()) {
			mostrarError("Campo vacío", "Por favor ingrese el ID de la cita");
			return;
		}

		try {
			long idCita = Long.parseLong(idCitaText);
			Visita visita = visitaApiClient.obtenerVisitaPorId(idCita);
			if (visita == null) {
				mostrarError("Cita no encontrada", "El ID de la cita no existe");
				return;
			}

			visitaApiClient.delete(idCita);
			mostrarInformacion("Cita eliminada", "La cita ha sido eliminada exitosamente");

			panelMedicoController.actualizarTablaCitas();

			Stage stage = (Stage) acept_delete_cita_btn.getScene().getWindow();
			stage.close();
		} catch (NumberFormatException e) {
			mostrarError("ID inválido", "El ID de la cita debe ser un número");
		}
	}

	@javafx.fxml.FXML
	public void cancelDeleteCita(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_delete_cita_btn.getScene().getWindow();
		stage.close();
		panelMedicoController.actualizarTablaCitas();
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