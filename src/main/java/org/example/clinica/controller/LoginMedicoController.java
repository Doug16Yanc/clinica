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
import org.example.clinica.model.Medico;
import org.example.clinica.repository.MedicoRepository;

import java.io.IOException;
import java.sql.SQLException;
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
    private static final MedicoRepository medicoRepository = new MedicoRepository();

    public void login(ActionEvent actionEvent) {

        try {
            Medico medico = medicoRepository.buscarMedico(crm.getText(), senha.getText());

            if (medico != null) {

                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LoginMedicoController.class.getResource("/org/example/clinica/medico-page.fxml")));
                Parent root = loader.load();

                MedicoPageController medicoPageController = loader.getController();
                medicoPageController.setMedicoLogado(medico);

                Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                showAlert("Sucesso", "Seja bem-vindo(a) " + medico.getNome() + "!" +
                        "\nO Ministério da Saúde alerta para o surto de arboviroses!", Alert.AlertType.INFORMATION);

            } else {
                showAlert("Falha", "Credenciais inválidas!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    void showAlert(String title, String message, Alert.AlertType alertType) {
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
