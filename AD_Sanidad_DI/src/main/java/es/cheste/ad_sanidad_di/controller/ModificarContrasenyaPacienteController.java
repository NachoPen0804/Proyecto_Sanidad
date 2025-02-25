package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ModificarContrasenyaPacienteController {

    @FXML
    private Button acept_edit_passw_paciente_btn;
    @FXML
    private Button cancel_edit_passw_paciente_btn;
    @FXML
    private PasswordField new_passw_paciente_text;
    @FXML
    private PasswordField confirm_passw_paciente_text;

    private Paciente paciente; // Para almacenar el paciente actual
    private final PacienteApiClient pacienteApi = new PacienteApiClient(); // Cliente API para pacientes

    // Método para establecer el paciente desde el controlador padre
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @FXML
    public void modificarContrasenyaPaciente(ActionEvent actionEvent) {
        String nuevaContrasena = new_passw_paciente_text.getText();
        String confirmarContrasena = confirm_passw_paciente_text.getText();

        // Validaciones
        if (nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        if (!nuevaContrasena.equals(confirmarContrasena)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }
        

        try {
            // Actualizar la contraseña del paciente a través de la API
            paciente.setPassword(nuevaContrasena); // Asumimos que Paciente tiene un setter para contraseña
            pacienteApi.actualizarPaciente(paciente); // Método hipotético para actualizar en la API

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "La contraseña ha sido actualizada correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar la contraseña: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelarModificarContrasenyaPaciente(ActionEvent actionEvent) {
        cerrarVentana();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) cancel_edit_passw_paciente_btn.getScene().getWindow();
        stage.close();
    }
}