package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.HospitalApiClient;
import es.cheste.ad_sanidad_di.model.Hospital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AñadirHospitalController {

	@FXML
	private TextField login_showPassword; // Innecesario, lo dejo por compatibilidad
	@FXML
	private Button cancel_add_hospital_btn;
	@FXML
	private AnchorPane login_form; // Innecesario, lo dejo por compatibilidad
	@FXML
	private TextField hospital_nombre_text;
	@FXML
	private Button acept_add_medico_btn; // Debería ser "acept_add_hospital_btn"
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

			// Abrir la ventana de MedicoAdd.fxml
			abrirVentanaMedicoAdd();

		} catch (Exception e) {
			mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo añadir el hospital: " + e.getMessage());
			e.printStackTrace();
		}
	}


	@FXML
	public void cancelAddHospital(ActionEvent actionEvent) {
		Stage stage = (Stage) cancel_add_hospital_btn.getScene().getWindow();
		stage.close();
	}

	// Método para abrir la ventana de MedicoAdd.fxml
	private void abrirVentanaMedicoAdd() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/MedicoAdd.fxml"));
			Parent root = loader.load();

			// Configurar el controlador de MedicoAdd si necesitas pasar datos
			// Ejemplo: AñadirMedicoController controller = loader.getController();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Añadir Médico");
			stage.initModality(Modality.APPLICATION_MODAL); // Modal para bloquear la ventana anterior
			stage.showAndWait(); // Mostrar y esperar a que se cierre

		} catch (IOException e) {
			mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de añadir médico: " + e.getMessage());
			e.printStackTrace();
		}
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