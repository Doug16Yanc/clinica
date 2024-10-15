package org.example.clinica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.clinica.application.LoginPacienteApplication;

import java.io.IOException;

public class InitialController {
    @FXML
    public Button pacienteBtn;
    @FXML
    public Button medicoBtn;
    @FXML
    private Label welcomeText;


    @FXML
    public void pacienteBtn(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(LoginPacienteApplication.class.getResource("/org/example/clinica/login-paciente.fxml"));
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
    public void medicoBtn(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/clinica/login-medico.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sair(ActionEvent actionEvent) {
        System.exit(0);
    }
}
