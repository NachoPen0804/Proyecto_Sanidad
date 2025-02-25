package es.cheste.ad_sanidad_di.javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class JavaFxApp extends Application {
	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/es/cheste/ad_sanidad_di/Login.fxml"));
		stage.setScene(new Scene(root, 330,550));
		stage.setResizable(false);
		stage.setTitle("Inicio de Sesión");
		stage.initStyle(StageStyle.UNDECORATED);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
