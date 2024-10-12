package org.example.clinica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMedicoController {
    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(LoginPacienteApplication.class.getResource("medico-page.fxml"));
            Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stageAtual.close();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void close(ActionEvent actionEvent) {
    }
}
