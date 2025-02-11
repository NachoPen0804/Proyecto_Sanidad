package es.cheste.ad_sanidad_di;

import es.cheste.ad_sanidad_di.javaFX.JavaFxApp;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdSanidadDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdSanidadDiApplication.class, args);
		Application.launch(JavaFxApp.class, args);
	}
	

}
