package es.cheste.ad_sanidad_di.controller;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class PanelMedicoController {
	@javafx.fxml.FXML
	private Label dashboard_tA;
	@javafx.fxml.FXML
	private TextField appointment_appointmentID;
	@javafx.fxml.FXML
	private Button appointment_insertBtn;
	@javafx.fxml.FXML
	private Label patients_PI_mobileNumber;
	@javafx.fxml.FXML
	private TextField appointment_mobileNumber;
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private Button appointments_btn;
	@javafx.fxml.FXML
	private Circle profile_circleImage;
	@javafx.fxml.FXML
	private TextField profile_name;
	@javafx.fxml.FXML
	private Label date_time;
	@javafx.fxml.FXML
	private TextArea profile_address;
	@javafx.fxml.FXML
	private TableColumn appointments_col_dateModify;
	@javafx.fxml.FXML
	private Label nav_username;
	@javafx.fxml.FXML
	private TextField profile_email;
	@javafx.fxml.FXML
	private TableColumn appointments_col_appointmentID;
	@javafx.fxml.FXML
	private TableColumn appointments_col_dateDelete;
	@javafx.fxml.FXML
	private DatePicker appointment_schedule;
	@javafx.fxml.FXML
	private TableColumn dashboad_col_name;
	@javafx.fxml.FXML
	private AnchorPane dashboard_form;
	@javafx.fxml.FXML
	private Label patients_PA_patientID;
	@javafx.fxml.FXML
	private TextField patients_password;
	@javafx.fxml.FXML
	private TextField patients_mobileNumber;
	@javafx.fxml.FXML
	private TableView appointments_tableView;
	@javafx.fxml.FXML
	private Label patients_PA_dateCreated;
	@javafx.fxml.FXML
	private Button appointment_clearBtn;
	@javafx.fxml.FXML
	private TableColumn appointments_col_gender;
	@javafx.fxml.FXML
	private TableColumn dashboad_col_appointmentID;
	@javafx.fxml.FXML
	private Label patients_PA_password;
	@javafx.fxml.FXML
	private Label profile_label_email;
	@javafx.fxml.FXML
	private Circle top_profile;
	@javafx.fxml.FXML
	private TextField appointment_description;
	@javafx.fxml.FXML
	private TextField profile_doctorID;
	@javafx.fxml.FXML
	private TableColumn appointments_col_status;
	@javafx.fxml.FXML
	private TextField appointment_treatment;
	@javafx.fxml.FXML
	private ComboBox patients_gender;
	@javafx.fxml.FXML
	private TableColumn dashboad_col_appointmentDate;
	@javafx.fxml.FXML
	private ComboBox appointment_status;
	@javafx.fxml.FXML
	private ComboBox profile_gender;
	@javafx.fxml.FXML
	private Label patients_PI_gender;
	@javafx.fxml.FXML
	private Label nav_adminID;
	@javafx.fxml.FXML
	private AnchorPane patients_form;
	@javafx.fxml.FXML
	private Button patients_PI_recordBtn;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private TableView dashboad_tableView;
	@javafx.fxml.FXML
	private ComboBox profile_specialized;
	@javafx.fxml.FXML
	private Label profile_label_name;
	@javafx.fxml.FXML
	private TextField profile_mobileNumber;
	@javafx.fxml.FXML
	private Button appointment_updateBtn;
	@javafx.fxml.FXML
	private ComboBox profile_status;
	@javafx.fxml.FXML
	private TableColumn appointments_col_date;
	@javafx.fxml.FXML
	private TextField appointment_diagnosis;
	@javafx.fxml.FXML
	private TableColumn appointments_col_description;
	@javafx.fxml.FXML
	private Label top_username;
	@javafx.fxml.FXML
	private Button appointment_deleteBtn;
	@javafx.fxml.FXML
	private Button profile_btn;
	@javafx.fxml.FXML
	private Button patients_btn;
	@javafx.fxml.FXML
	private Label patients_PI_address;
	@javafx.fxml.FXML
	private Label dashboard_TP;
	@javafx.fxml.FXML
	private Button patients_PI_addBtn;
	@javafx.fxml.FXML
	private Label profile_label_dateCreated;
	@javafx.fxml.FXML
	private TableColumn dashboad_col_description;
	@javafx.fxml.FXML
	private Button profile_importBtn;
	@javafx.fxml.FXML
	private Label dashboard_AP;
	@javafx.fxml.FXML
	private AnchorPane appointments_form;
	@javafx.fxml.FXML
	private TextArea appointment_address;
	@javafx.fxml.FXML
	private TextField appointment_name;
	@javafx.fxml.FXML
	private Button dashboard_btn;
	@javafx.fxml.FXML
	private Label dashboard_IP;
	@javafx.fxml.FXML
	private Button logout_btn;
	@javafx.fxml.FXML
	private ComboBox appointment_gender;
	@javafx.fxml.FXML
	private TextField patients_patientName;
	@javafx.fxml.FXML
	private TableColumn appointments_col_contactNumber;
	@javafx.fxml.FXML
	private Label profile_label_doctorID;
	@javafx.fxml.FXML
	private Button patients_confirmBtn;
	@javafx.fxml.FXML
	private AnchorPane profile_form;
	@javafx.fxml.FXML
	private Label patients_PI_patientName;
	@javafx.fxml.FXML
	private TextField patients_patientID;
	@javafx.fxml.FXML
	private TableColumn appointments_col_name;
	@javafx.fxml.FXML
	private TextArea patients_address;
	@javafx.fxml.FXML
	private Button profile_updateBtn;

	
}
