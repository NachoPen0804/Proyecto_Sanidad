package es.cheste.ad_sanidad_di.controller;


import es.cheste.ad_sanidad_di.api.PacienteApiClient;
import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PanelMedicoController {
	@javafx.fxml.FXML
	private AnchorPane main_form;
	@javafx.fxml.FXML
	private Circle top_profile;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private Button profile_btn;
	@javafx.fxml.FXML
	private Button patients_btn;
	@FXML
	private TableColumn<Visita, Long> id_tabla;
	@FXML
	private TableView<Visita> tablaCitas;

	@FXML
	private TableColumn<Visita, String> id_paciente_tabla;
	@FXML
	private TableColumn<Visita, String> id_medico_tabla;
	@FXML
	private TableColumn<Visita, LocalDate> fecha_tabla;

	private final VisitaApiCliente visitaApi = new VisitaApiCliente();
	private final PacienteApiClient pacienteApi = new PacienteApiClient();
	@FXML
	private Label num_citas_totales;
	@FXML
	private AnchorPane panel_view_pacientes;
	@FXML
	private Button pacientes_add_btn;
	@FXML
	private Button citas_delete_btn;
	@FXML
	private TableColumn<Paciente, Long> id_paciente_tabla_panel_paciente;
	@FXML
	private Button pacientes_delete_btn;
	@FXML
	private TableColumn<Paciente, String> pueblo_tabla;
	@FXML
	private TableColumn<Paciente, String> nombre_paciente_tabla_panel_paciente;
	@FXML
	private Button citas_add_btn;
	@FXML
	private Button pacientes_edit_btn;
	@FXML
	private TableColumn<Paciente, String> apellidos_paciente_tabla_panel_paciente;
	@FXML
	private Button citas_btn;
	@FXML
	private Label num_total_pacientes;
	@FXML
	private AnchorPane panel_view_medicos;
	@FXML
	private Label nombre_medico;
	@FXML
	private Label nombre_medico_superior;

	private long id_medico;
	@FXML
	private TableView tablaPacientes;
	@FXML
	private Button cerrarSesion_btn;
	@FXML
	private Label label_nombre_hospital_perfil;
	@FXML
	private Label label_apellidos_perfil;
	@FXML
	private AnchorPane panel_perfil;
	@FXML
	private Button edit_profile_btn;
	@FXML
	private Label label_nombre_perfil;
	@FXML
	private Label id_medico_Perfil;
	@FXML
	private Label label_id_perfil;
	@FXML
	private Label label_pueblo_hospital_perfil;

	private Medico medicoiniciado;


	@Deprecated
	public void pacientes_lista(ActionEvent actionEvent) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/es/cheste/ad_sanidad_di/prueba.fxml"));

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Nueva Ventana");

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@FXML
	public void initialize() {
		id_tabla.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_paciente_tabla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre().toUpperCase()));
		id_medico_tabla.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getNombre().toUpperCase() + " " + cellData.getValue().getMedico().getApellidos().toUpperCase()));
		fecha_tabla.setCellValueFactory(new PropertyValueFactory<>("fecha"));

		id_paciente_tabla_panel_paciente.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombre_paciente_tabla_panel_paciente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		apellidos_paciente_tabla_panel_paciente.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
		pueblo_tabla.setCellValueFactory(new PropertyValueFactory<>("pueblo_residencia"));


		cargarDatosCitas();
		cargarDatosPacientes();
	}

	private void cargarDatosCitas() {

		List<Visita> visitas = visitaApi.obtenerVisitas();
		ObservableList<Visita> visitasObservableList = FXCollections.observableArrayList(visitas);
		tablaCitas.setItems(visitasObservableList);
	}

	private void cargarDatosPacientes() {
		List<Paciente> pacientes = pacienteApi.obtenerPacientes();
		ObservableList<Paciente> pacienteObservableList = FXCollections.observableArrayList(pacientes);
		tablaPacientes.setItems(pacienteObservableList);
	}


	@FXML
	public void abrirVentanaAddPaciente(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PacienteAdd.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Eliminar Paciente");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}


	@FXML
	public void abrirVentanaDeletePaciente(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PacienteDelete.fxml"));
		Parent root = loader.load();

		EliminarPacienteController controller = loader.getController();
		controller.setPanelMedicoController(this);

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Eliminar Paciente");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	@FXML
	public void abrirVentanaDeleteCita(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/CitaDelete.fxml"));
		Parent root = loader.load();

		EliminarCitaController controller = loader.getController();
		controller.setPanelMedicoController(this);

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Eliminar Cita");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	public void setId_medico(long id_medicoLogin) {
		id_medico_Perfil.setText(String.valueOf(id_medicoLogin));
	}

	public void cargarMedico(Medico medico) {
		medicoiniciado = medico;
		label_nombre_perfil.setText(medico.getNombre());
		label_apellidos_perfil.setText(medico.getApellidos());
		label_id_perfil.setText(String.valueOf(medico.getId()));
		label_nombre_hospital_perfil.setText(medico.getHospital().getNombre());
		label_pueblo_hospital_perfil.setText(medico.getHospital().getLocalidad());
	}


	@FXML
	public void editarDatosPerfil() {

	}

	@FXML
	public void abrirVentanaAddCita(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/CitaAdd.fxml"));
		Parent root = loader.load();

		CrearCitaController controller = loader.getController();
		controller.setIdMedico(this.id_medico);
		controller.setPanelMedicoController(this);

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Añadir Cita");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	public void actualizarTablaCitas() {
		cargarDatosCitas();
	}

	public void actualizarTablaPacientes() {
		cargarDatosPacientes();
	}

	@FXML
	public void abrirVentanaEditPaciente(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PacienteEdit.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Eliminar Paciente");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();


	}

	@FXML
	public void mostrarPacientes(ActionEvent actionEvent) {
		panel_view_medicos.setVisible(false);
		panel_view_pacientes.setVisible(true);
		panel_perfil.setVisible(false);
		actualizarTablaPacientes();
		actualizarTablaCitas();
	}

	@FXML
	public void editarPerfil() {
		panel_view_medicos.setVisible(false);
		panel_view_pacientes.setVisible(false);
		panel_perfil.setVisible(true);

	}

	@FXML
	public void mostrarCitas(ActionEvent actionEvent) {
		panel_view_medicos.setVisible(true);
		panel_perfil.setVisible(false);
		panel_view_pacientes.setVisible(false);
		actualizarTablaPacientes();
		actualizarTablaCitas();
	}

	public void setNombreMedico(String nombre) {
		nombre_medico.setText(nombre);
	}

	public void setNombreMedicoSuperior(String nombre) {
		nombre_medico_superior.setText(nombre);
	}

	@FXML
	public void cerrarSesion(ActionEvent actionEvent) {
		Stage stage = (Stage) cerrarSesion_btn.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/Login.fxml"));
			Parent root = loader.load();

			Stage loginStage = new Stage();
			loginStage.setScene(new Scene(root));
			loginStage.setTitle("Inicio de Sesión");
			loginStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
