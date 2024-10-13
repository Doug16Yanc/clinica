module org.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.clinica to javafx.fxml;
    exports org.example.clinica.controller;
    opens org.example.clinica.controller to javafx.fxml;
    exports org.example.clinica.application;
    opens org.example.clinica.application to javafx.fxml;
}