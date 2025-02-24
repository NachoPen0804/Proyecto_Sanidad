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
	
	
}
