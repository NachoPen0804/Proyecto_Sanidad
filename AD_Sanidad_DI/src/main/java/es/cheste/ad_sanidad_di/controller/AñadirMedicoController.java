package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    }

    @javafx.fxml.FXML
    public void addMedico(ActionEvent actionEvent) {
    }
}
