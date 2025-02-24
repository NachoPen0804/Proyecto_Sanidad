package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AñadirMedicoController {
    @javafx.fxml.FXML
    private TextField medico_apellidos_text;
    @javafx.fxml.FXML
    private TextField login_showPassword;
    @javafx.fxml.FXML
    private AnchorPane login_form;
    @javafx.fxml.FXML
    private TextField medico_hospital_text;
    @javafx.fxml.FXML
    private Button cancel_add_medico_btn;
    @javafx.fxml.FXML
    private Button view_add_hospital_btn;
    @javafx.fxml.FXML
    private Button acept_add_medico_btn;
    @javafx.fxml.FXML
    private TextField medico_nombre_text;
    @javafx.fxml.FXML
    private PasswordField medico_password;
    @javafx.fxml.FXML
    private PasswordField medico_confirm_password;

    @javafx.fxml.FXML
    public void cancelAddMedico(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void abrirVentanaAddHospital(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/HospitalAdd.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) view_add_hospital_btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de Médico");
        } catch (Exception e) {
            mostrarError("Error", "No se pudo cargar la ventana de registro de médico");
        }
        
    }

    @javafx.fxml.FXML
    public void addMedico(ActionEvent actionEvent) {
    }
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
