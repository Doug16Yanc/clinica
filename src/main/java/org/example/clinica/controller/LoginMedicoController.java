package org.example.clinica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.clinica.application.LoginMedicoApplication;
import org.example.clinica.application.LoginPacienteApplication;

import java.io.IOException;
import java.util.Objects;

public class LoginMedicoController {
    @FXML
    public TextField crm;
    @FXML
    public TextField senha;
    @FXML
    public Button entrar;
    @FXML
    public Button sair;
    @FXML

    public static void login(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(LoginPacienteApplication.class.getResource("/org/example/clinica/medico-page.fxml"));
            Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stageAtual.close();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void entrar(ActionEvent actionEvent) {
        String entry = crm.getText();
        String pass = senha.getText();

        if (entry.trim().isEmpty() || pass.trim().isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
        } else {
            showAlert("Sucesso", "Login realizado com sucesso!", Alert.AlertType.INFORMATION);
            login(actionEvent);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/initial-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
