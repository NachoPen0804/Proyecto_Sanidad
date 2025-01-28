module es.cheste.proyecto_sanidad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.proyecto_sanidad to javafx.fxml;
    exports es.cheste.proyecto_sanidad;
}