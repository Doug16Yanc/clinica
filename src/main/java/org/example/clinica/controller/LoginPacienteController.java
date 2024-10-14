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
import org.example.clinica.application.LoginPacienteApplication;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.PacienteRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPacienteController {
    @FXML
    public TextField emailField;
    @FXML
    public TextField senhaField;

    @FXML
    public Button btnIrRegistrar;
    @FXML
    public Button btnSair;

    private final PacienteRepository pacienteRepository = new PacienteRepository();

    public void login(ActionEvent actionEvent) {

        try {
            Paciente paciente = pacienteRepository.buscarPaciente(emailField.getText(), senhaField.getText());

            if (paciente != null) {

                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LoginMedicoController.class.getResource("/org/example/clinica/paciente-page.fxml")));
                Parent root = loader.load();


                PacientePageController pacientePageController = loader.getController();
                pacientePageController.setPacienteLogado(paciente);

                Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                showAlert("Sucesso", "Seja bem-vindo(a), caríssimo(a) " + paciente.getNome() + "!", Alert.AlertType.INFORMATION);

            } else {
                showAlert("Falha", "Credenciais inválidas!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/registration-paciente.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao abrir o stage: " + e.getMessage());
        }
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/initial-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}