package org.example.clinica;

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

import java.io.IOException;
import java.util.Objects;

public class LoginPacienteController {
    @FXML
    public TextField emailField;
    @FXML
    public TextField senhaField;
    @FXML
    public Button btnPacientLogin;
    @FXML
    public Button btnIrRegistrar;
    @FXML
    public Button btnSair;

    public void login(ActionEvent actionEvent) {
        String email = emailField.getText();
        String senha = senhaField.getText();

        if (email.trim().isEmpty() || senha.trim().isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
        } else {
            showAlert("Sucesso", "Login realizado com sucesso!", Alert.AlertType.INFORMATION);

        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void irRegistrar(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration-paciente.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao abrir o stage: " + e.getMessage());
        }
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("initial-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}