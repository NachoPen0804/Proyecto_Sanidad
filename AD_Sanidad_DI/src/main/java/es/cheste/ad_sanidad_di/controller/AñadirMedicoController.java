package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.HospitalApiClient;
import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.model.Hospital;
import es.cheste.ad_sanidad_di.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AñadirMedicoController {

    @FXML
    private TextField medico_apellidos_text;
    @FXML
    private TextField login_showPassword; // Innecesario, lo dejo por compatibilidad
    @FXML
    private AnchorPane login_form; // Innecesario, lo dejo por compatibilidad
    @FXML
    private TextField medico_hospital_text; // ID del hospital
    @FXML
    private Button cancel_add_medico_btn;
    @FXML
    private Button view_add_hospital_btn;
    @FXML
    private Button acept_add_medico_btn;
    @FXML
    private TextField medico_nombre_text;
    @FXML
    private PasswordField medico_password;
    @FXML
    private PasswordField medico_confirm_password;

    private final MedicoApiClient medicoApiClient = new MedicoApiClient();
    private final HospitalApiClient hospitalApiClient = new HospitalApiClient();

    @FXML
    public void addMedico(ActionEvent actionEvent) {
        String nombre = medico_nombre_text.getText().trim();
        String apellidos = medico_apellidos_text.getText().trim();
        String hospitalIdText = medico_hospital_text.getText().trim();
        String password = medico_password.getText();
        String confirmPassword = medico_confirm_password.getText();

        // Validar campos vacíos
        if (nombre.isEmpty() || apellidos.isEmpty() || hospitalIdText.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Por favor, completa todos los campos.");
            return;
        }

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }

        try {
            // Validar y obtener el hospital
            long hospitalId = Long.parseLong(hospitalIdText);
            Hospital hospital = hospitalApiClient.obtenerHospitalPorId(hospitalId);
            if (hospital == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró un hospital con el ID '" + hospitalId + "'.");
                return;
            }

            // Verificar si ya existe un médico con el mismo nombre y apellidos
            List<Medico> medicos = medicoApiClient.obtenerMedicos();
            boolean existe = medicos.stream()
                    .anyMatch(m -> m.getNombre().equalsIgnoreCase(nombre) && m.getApellidos().equalsIgnoreCase(apellidos));
            if (existe) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un médico con el nombre '" + nombre + " " + apellidos + "'.");
                return;
            }

            // Crear un nuevo médico
            Medico nuevoMedico = new Medico();
            nuevoMedico.setNombre(nombre);
            nuevoMedico.setApellidos(apellidos);
            nuevoMedico.setHospital(hospital);
            nuevoMedico.setPassword(password);

            // Guardar el médico a través de la API
            medicoApiClient.create(nuevoMedico);

            // Mostrar mensaje de éxito
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Médico '" + nombre + " " + apellidos + "' añadido correctamente.");

            // Cerrar la ventana actual y abrir la ventana de login
            Stage currentStage = (Stage) acept_add_medico_btn.getScene().getWindow();
            currentStage.close();
            abrirVentanaLogin();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El ID del hospital debe ser un número válido.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo añadir el médico: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelAddMedico(ActionEvent actionEvent) {
        // Cerrar la ventana actual y abrir la ventana de login
        Stage currentStage = (Stage) cancel_add_medico_btn.getScene().getWindow();
        currentStage.close();
        abrirVentanaLogin();
    }

    @FXML
    public void abrirVentanaAddHospital(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/HospitalAdd.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Añadir Hospital");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de añadir hospital: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para abrir la ventana de Login.fxml
    private void abrirVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/Login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Inicio de Sesión");
            stage.show(); // Mostrar sin esperar, ya que no es modal respecto a otra ventana cerrada

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de login: " + e.getMessage());
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