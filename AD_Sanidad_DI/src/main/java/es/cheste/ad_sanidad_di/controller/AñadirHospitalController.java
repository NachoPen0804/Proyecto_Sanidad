package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.HospitalApiClient; // Cliente API para hospitales
import es.cheste.ad_sanidad_di.model.Hospital; // Tu modelo de Hospital
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class AñadirHospitalController {

	@FXML
	private TextField login_showPassword; // Parece innecesario, lo dejo por compatibilidad
	@FXML
	private Button cancel_add_hospital_btn;
	@FXML
	private AnchorPane login_form; // Parece innecesario, lo dejo por compatibilidad
	@FXML
	private TextField hospital_nombre_text;
	@FXML
	private Button acept_add_medico_btn; // Debería ser "acept_add_hospital_btn", lo corregiré
	@FXML
	private TextField hospital_localidad_text;

	private final HospitalApiClient hospitalApiClient = new HospitalApiClient();

	@FXML
	public void addHospital(ActionEvent actionEvent) {
		String nombre = hospital_nombre_text.getText().trim();
		String localidad = hospital_localidad_text.getText().trim();

		// Validar campos
		if (nombre.isEmpty()) {
			mostrarAlerta(Alert.AlertType.ERROR, "Error", "El nombre del hospital es obligatorio.");
			return;
		}
		// Localidad puede ser null según tu modelo (@Column(nullable = true))

		try {
			// Verificar si ya existe un hospital con el mismo nombre
			List<Hospital> hospitales = hospitalApiClient.obtenerHospitales();
			boolean existe = hospitales.stream()
					.anyMatch(h -> h.getNombre().equalsIgnoreCase(nombre));
			if (existe) {
				mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un hospital con el nombre '" + nombre + "'.");
				return;
			}

			// Crear un nuevo hospital
			Hospital nuevoHospital = new Hospital();
			nuevoHospital.setNombre(nombre);
			nuevoHospital.setLocalidad(localidad.isEmpty() ? null : localidad); // Permitir null en localidad

			// Guardar el hospital a través de la API
			hospitalApiClient.create(nuevoHospital);

			// Mostrar mensaje de éxito
			mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Hospital '" + nombre + "' añadido correctamente.");

			// Cerrar la ventana
			Stage stage = (Stage) acept_add_medico_btn.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo añadir el hospital: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelAddHospital(ActionEvent actionEvent) {
		// Cerrar la ventana actual y volver a la ventana anterior
		Stage stage = (Stage) cancel_add_hospital_btn.getScene().getWindow();
		stage.close();
	}

	// Método auxiliar para mostrar alertas
	private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}