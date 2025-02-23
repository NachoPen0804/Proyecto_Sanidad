package es.cheste.ad_sanidad_di;

import es.cheste.ad_sanidad_di.javaFX.JavaFxApp;
import es.cheste.ad_sanidad_di.model.Usuario;
import es.cheste.ad_sanidad_di.repository.UsuarioRepository;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdSanidadDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdSanidadDiApplication.class, args);
		Application.launch(JavaFxApp.class, args);
		
	}
	
	@Bean
	public CommandLineRunner CargarUsuarios(UsuarioRepository repository) {
		return (args) -> {
			repository.save(new Usuario("admin", "1234"));
			repository.save(new Usuario("user", "user123"));
		};
	}
}
