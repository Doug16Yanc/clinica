package org.example.clinica.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RealizarConsultaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPacienteApplication.class.getResource("/org/example/clinica/realizar-consulta.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Teic Gra Medicina");
        stage.setScene(scene);
        stage.show();
    }
}
