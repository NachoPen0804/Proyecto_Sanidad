package es.cheste.ad_sanidad_di.controller;

import es.cheste.ad_sanidad_di.api.HospitalApiClient;
import es.cheste.ad_sanidad_di.api.MedicoApiClient;
import es.cheste.ad_sanidad_di.model.Hospital;
import es.cheste.ad_sanidad_di.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarMedicoController {

    @FXML
    private TextField medico_apellidos_text;
    @FXML
    private TextField login_showPassword;
    @FXML
    private Button acept_edit_medico_btn;
    @FXML
    private Button cancel_edit_medico_btn;
    @FXML
    private TextField medico_nombre_text;
    @FXML
    private TextField medico_id_hospital_text;
    @FXML
    private PasswordField medico_password;
    @FXML
    private PasswordField medico_confirm_password;

    private MedicoApiClient medicoApiClient = new MedicoApiClient();
    private HospitalApiClient hospitalApiClient = new HospitalApiClient(); // Nuevo cliente
    private PanelMedicoController panelMedicoController;
    private Medico medicoSeleccionado;

    public void setMedicoSeleccionado(Medico medico) {
        this.medicoSeleccionado = medico;
        cargarDatosMedico();
    }

    public void setPanelMedicoController(PanelMedicoController controller) {
        this.panelMedicoController = controller;
    }

    private void cargarDatosMedico() {
        if (medicoSeleccionado != null) {
            medico_nombre_text.setText(medicoSeleccionado.getNombre());
            medico_apellidos_text.setText(medicoSeleccionado.getApellidos());
            medico_id_hospital_text.setText(String.valueOf(medicoSeleccionado.getHospital().getId()));
            medico_password.setText(medicoSeleccionado.getPassword());
            medico_confirm_password.setText(medicoSeleccionado.getPassword());
            login_showPassword.setText(medicoSeleccionado.getPassword());
        }
    }

    @FXML
    public void modificarMedico(ActionEvent actionEvent) {
        if (medico_nombre_text.getText().isEmpty() || medico_apellidos_text.getText().isEmpty() ||
                medico_id_hospital_text.getText().isEmpty() || medico_password.getText().isEmpty() ||
                medico_confirm_password.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        if (!medico_password.getText().equals(medico_confirm_password.getText())) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.");
            return;
        }

        long idHospital;
        try {
            idHospital = Long.parseLong(medico_id_hospital_text.getText());
            System.out.println("ID del hospital ingresado: " + idHospital);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID del hospital debe ser un número válido.");
            return;
        }

        Hospital hospital = hospitalApiClient.obtenerHospitalPorId(idHospital);
        if (hospital == null) {
            mostrarAlerta("Error", "No se encontró un hospital con el ID proporcionado: " + idHospital);
            return;
        }

        medicoSeleccionado.setNombre(medico_nombre_text.getText());
        medicoSeleccionado.setApellidos(medico_apellidos_text.getText());
        medicoSeleccionado.setHospital(hospital);
        medicoSeleccionado.setPassword(medico_password.getText());

        System.out.println("Hospital asignado a medicoSeleccionado: " + medicoSeleccionado.getHospital()); // Depuración
        medicoApiClient.update(medicoSeleccionado);

        mostrarAlerta("Éxito", "Médico modificado correctamente.");
        if (panelMedicoController != null) {
            panelMedicoController.cargarMedico(medicoSeleccionado);
        }

        Stage stage = (Stage) acept_edit_medico_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelarModificarMedico(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel_edit_medico_btn.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}