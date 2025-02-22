package es.cheste.ad_sanidad_di;

import es.cheste.ad_sanidad_di.javaFX.JavaFxApp;
import es.cheste.ad_sanidad_di.model.*;
import es.cheste.ad_sanidad_di.repository.*;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class AdSanidadDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdSanidadDiApplication.class, args);
		Application.launch(JavaFxApp.class, args);
		
	}

	@Bean
	public CommandLineRunner CargarUsuariosMedicosHospitales(UsuarioRepository repositoryUsers, MedicoRepository repositoryMedic, HospitalRepository repositoryHospitals, PacienteRepository repositoryPacients, VisitaRepository repositoryVisitas) {
		return (args) -> {
			repositoryUsers.save(new Usuario("admin", "1234"));
			repositoryUsers.save(new Usuario("user", "user123"));

			String hospitalNombre = "La Prueba";
			if (!repositoryHospitals.existsByNombre(hospitalNombre)) {
				Hospital hospital = new Hospital(hospitalNombre, "Cheste");
				repositoryHospitals.save(hospital);

				Medico medico = new Medico("Pablo", "Prueba Prueba", hospital, "prueba");
				repositoryMedic.save(medico);

				Paciente paciente = new Paciente("Pedro", "Prueba Prueba", "Cheste", "prueba");
				repositoryPacients.save(paciente);

				repositoryVisitas.save(new Visita(paciente, medico, LocalDate.of(2025, 2, 22)));
			} else {
				System.out.println("El hospital con el nombre " + hospitalNombre + " ya existe.");
			}
		
		};
		
	}
}
