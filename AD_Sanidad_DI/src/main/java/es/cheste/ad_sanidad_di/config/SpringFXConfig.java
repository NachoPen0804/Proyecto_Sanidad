package es.cheste.ad_sanidad_di.config;

import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration(proxyBeanMethods = false)
@Lazy
public class SpringFXConfig {
	@Bean
	public FXMLLoader fxmlLoader() {
		return new FXMLLoader();
	}
}