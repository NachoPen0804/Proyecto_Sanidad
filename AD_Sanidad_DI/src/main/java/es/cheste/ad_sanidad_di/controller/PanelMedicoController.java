package es.cheste.ad_sanidad_di.controller;


import es.cheste.ad_sanidad_di.api.VisitaApiCliente;
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
	private Label nav_adminID;
	@javafx.fxml.FXML
	private Label current_form;
	@javafx.fxml.FXML
	private Button profile_btn;
	@javafx.fxml.FXML
	private Button patients_btn;
	@javafx.fxml.FXML
	private Button logout_btn;
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
	@FXML
	private Label num_citas_totales;
	@FXML
	private AnchorPane panel_view_pacientes;
	@FXML
	private Button pacientes_add_btn;
	@FXML
	private Button citas_delete_btn;
	@FXML
	private TableColumn id_paciente_tabla_panel_paciente;
	@FXML
	private Button pacientes_delete_btn;
	@FXML
	private TableColumn pueblo_tabla;
	@FXML
	private TableColumn nombre_paciente_tabla_panel_paciente;
	@FXML
	private Button citas_add_btn;
	@FXML
	private Button pacientes_edit_btn;
	@FXML
	private TableColumn apellidos_paciente_tabla_panel_paciente;
	@FXML
	private TableView tablaCitas1;
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


		cargarDatos();
	}

	private void cargarDatos() {

		List<Visita> visitas = visitaApi.obtenerVisitas();
		ObservableList<Visita> visitasObservableList = FXCollections.observableArrayList(visitas);
		tablaCitas.setItems(visitasObservableList);
	}

	

	@FXML
	public void abrirVentanaAddPaciente(ActionEvent actionEvent) {
	}

	@FXML
	public void mostrarPacientes(ActionEvent actionEvent) {
		panel_view_medicos.setVisible(false);
		panel_view_pacientes.setVisible(true);
	}

	@FXML
	public void abrirVentanaDeletePaciente(ActionEvent actionEvent) {
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
	@FXML
	public void abrirVentanaAddCita(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/CitaAdd.fxml"));
		Parent root = loader.load();

		CrearCitaController controller = loader.getController();
		controller.setIdMedico(id_medico);
		controller.setPanelMedicoController(this); 

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Añadir Cita");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	public void actualizarTablaCitas() {
		cargarDatos();
	}

	@FXML
	public void abrirVentanaEditPaciente(ActionEvent actionEvent) {
	}

	@FXML
	public void mostrarCitas(ActionEvent actionEvent) {
		panel_view_medicos.setVisible(true);
		panel_view_pacientes.setVisible(false);
	}

	public void setNombreMedico(String nombre) {
		nombre_medico.setText(nombre);
	}

	public void setNombreMedicoSuperior(String nombre) {
		nombre_medico_superior.setText(nombre);
	}
}
