module org.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.clinica.controller to javafx.fxml;
    opens org.example.clinica.application to javafx.fxml;
    exports org.example.clinica.application;
    exports org.example.clinica.controller;
}
