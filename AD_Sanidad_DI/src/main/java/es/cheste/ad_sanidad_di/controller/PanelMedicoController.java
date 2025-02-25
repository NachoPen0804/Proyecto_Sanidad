package es.cheste.ad_sanidad_di.controller;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

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
	private TableView<Paciente> tablaPacientes;
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
	@FXML
	private Button imprimir_btn;
	@FXML
	private Button imprimir_btn1;

	@FXML
	private TableColumn<Paciente, String> telefono_tabla;

	@FXML
	private TableColumn<Visita, String> hora_tabla;
	@FXML
	private TableColumn<Visita, String> minutos_tabla;

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
		telefono_tabla.setCellValueFactory(new PropertyValueFactory<>("telefono")); // Nueva columna

		hora_tabla.setCellValueFactory(data -> new SimpleStringProperty(String.format("%02d", data.getValue().getHora()))); // Cambiado a "hora_tabla"
		minutos_tabla.setCellValueFactory(data -> new SimpleStringProperty(String.format("%02d", data.getValue().getMinuto())));
		final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		centerColumn(id_tabla, null);
		centerColumn(id_paciente_tabla, null);
		centerColumn(id_medico_tabla, null);
		centerColumn(fecha_tabla, item -> ((LocalDate)item).format(dateFormat));
		centerColumn(hora_tabla, null);
		centerColumn(minutos_tabla, null);

		// Para tablaPacientes
		centerColumn(id_paciente_tabla_panel_paciente, null);
		centerColumn(nombre_paciente_tabla_panel_paciente, null);
		centerColumn(apellidos_paciente_tabla_panel_paciente, null);
		centerColumn(pueblo_tabla, null);
		centerColumn(telefono_tabla, null);
		cargarDatosCitas();
		cargarDatosPacientes();
	}

	private void cargarDatosCitas() {

		List<Visita> visitas = visitaApi.obtenerVisitas();
		ObservableList<Visita> visitasObservableList = FXCollections.observableArrayList(visitas);
		tablaCitas.setItems(visitasObservableList);
		num_citas_totales.setText(String.valueOf(tablaCitas.getItems().size()));
		num_total_pacientes.setText(String.valueOf(tablaPacientes.getItems().size()));
	}

	private void cargarDatosPacientes() {
		List<Paciente> pacientes = pacienteApi.obtenerPacientes();
		ObservableList<Paciente> pacienteObservableList = FXCollections.observableArrayList(pacientes);
		tablaPacientes.setItems(pacienteObservableList);
		num_citas_totales.setText(String.valueOf(tablaCitas.getItems().size()));
		num_total_pacientes.setText(String.valueOf(tablaPacientes.getItems().size()));
	}


	@FXML
	public void abrirVentanaAddPaciente(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/PacienteAdd.fxml"));
		Parent root = loader.load();

		AñadirPacienteController controller = loader.getController();
		controller.setPanelMedicoController(this);

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Añadir Paciente");
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
	public void editarDatosPerfil() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/ad_sanidad_di/MedicoEdit.fxml"));
		Parent root = loader.load();

		ModificarMedicoController controller = loader.getController();
		controller.setMedicoSeleccionado(medicoiniciado);
		controller.setPanelMedicoController(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Editar paciente");
		stage.initModality(Modality.APPLICATION_MODAL);

		stage.showAndWait();

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
			loginStage.initStyle(StageStyle.UNDECORATED);
			loginStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void imprimirListaCitas(ActionEvent actionEvent) {
		generarPdfConTablas();
	}

	public void generarPdfConTablas() {
		String destino = "citas_pacientes.pdf";
		try {
			PdfWriter writer = new PdfWriter(destino);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);

			Table tablaCitasPdf = new Table(new float[]{1, 3, 3, 2});
			tablaCitasPdf.addCell(new Cell().add(new Paragraph("ID")));
			tablaCitasPdf.addCell(new Cell().add(new Paragraph("Paciente")));
			tablaCitasPdf.addCell(new Cell().add(new Paragraph("Medico")));
			tablaCitasPdf.addCell(new Cell().add(new Paragraph("Fecha")));

			for (Visita visita : tablaCitas.getItems()) {
				tablaCitasPdf.addCell(new Cell().add(new Paragraph(String.valueOf(visita.getId()))));
				tablaCitasPdf.addCell(new Cell().add(new Paragraph(visita.getPaciente().getNombre())));
				tablaCitasPdf.addCell(new Cell().add(new Paragraph(visita.getMedico().getNombre())));
				tablaCitasPdf.addCell(new Cell().add(new Paragraph(visita.getFecha().toString())));
			}

			Table tablaPacientesPdf = new Table(new float[]{1, 3, 3, 3, 3}); // Añadimos una columna para teléfono
			tablaPacientesPdf.addCell(new Cell().add(new Paragraph("ID")));
			tablaPacientesPdf.addCell(new Cell().add(new Paragraph("Nombre")));
			tablaPacientesPdf.addCell(new Cell().add(new Paragraph("Apellidos")));
			tablaPacientesPdf.addCell(new Cell().add(new Paragraph("Pueblo Residencia")));
			tablaPacientesPdf.addCell(new Cell().add(new Paragraph("Teléfono"))); // Nueva columna

			for (Paciente paciente : tablaPacientes.getItems()) {
				tablaPacientesPdf.addCell(new Cell().add(new Paragraph(String.valueOf(paciente.getId()))));
				tablaPacientesPdf.addCell(new Cell().add(new Paragraph(paciente.getNombre())));
				tablaPacientesPdf.addCell(new Cell().add(new Paragraph(paciente.getApellidos())));
				tablaPacientesPdf.addCell(new Cell().add(new Paragraph(paciente.getPueblo_residencia())));
				tablaPacientesPdf.addCell(new Cell().add(new Paragraph(paciente.getTelefono() != null ? paciente.getTelefono() : ""))); // Nueva columna
			}

			document.add(new Paragraph("Citas:"));
			document.add(tablaCitasPdf);
			document.add(new Paragraph("\nPacientes:"));
			document.add(tablaPacientesPdf);

			document.close();
			System.out.println("PDF creado en: " + destino);
			File archivoPdf = new File(destino);
			if (archivoPdf.exists() && Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(archivoPdf);
			} else {
				System.out.println("No se puede abrir el PDF automáticamente. Abre manualmente en: " + destino);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private <S, T> void centerColumn(TableColumn<S, T> column, Function<T, String> formatter) {
		column.setCellFactory(tc -> {
			TableCell<S, T> cell = new TableCell<S, T>() {
				@Override
				protected void updateItem(T item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						String text = formatter != null ? formatter.apply(item) : item.toString();
						setText(text);
						setAlignment(Pos.CENTER);
					}
				}
			};
			return cell;
		});
	}

}
