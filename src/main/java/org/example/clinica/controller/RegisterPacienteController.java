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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.clinica.application.LoginPacienteApplication;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.PacienteRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class RegisterPacienteController {
    @FXML
    public AnchorPane loginPacienteForm;
    @FXML
    public TextField nomeField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField telefoneField;
    @FXML
    public TextField senhaField;
    @FXML
    public TextField cpfField;
    @FXML
    public Button btnRegistroPaciente;
    @FXML
    public Button btnCancelar;

    public void register(ActionEvent actionEvent) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String senha = senhaField.getText();
        String cpf = cpfField.getText();

        if (nome.trim().isEmpty() || email.trim().isEmpty() || telefone.trim().isEmpty() || senha.trim().isEmpty()) {
            showAlert("Mensagem", "Preencha todos os campos", Alert.AlertType.ERROR);
            return;
        }

        Paciente paciente = new Paciente(nome, email, telefone, senha, cpf);

        try {
            PacienteRepository pacienteRepository = new PacienteRepository();
            pacienteRepository.cadastrarPaciente(paciente);
            showAlert("Sucesso", "Paciente cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            Parent root = FXMLLoader.load(LoginPacienteApplication.class.getResource("/org/example/clinica/login-paciente.fxml"));
            Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stageAtual.close();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

        /*

        if (nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty() || telefone.trim().isEmpty() || cpf.trim().isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
        } else {
            showAlert("Sucesso", "Registro realizado com sucesso!", Alert.AlertType.INFORMATION);

            Paciente paciente = new Paciente(new Random().nextInt(0, 1000000000), nome, email, senha, telefone, cpf);

            showAlert("Paciente",
                    paciente.getId() + "\n"
                            + paciente.getNome() + "\n"
                            + paciente.getEmail() + "\n"
                            + paciente.getSenha() + "\n"
                            + paciente.getTelefone() + "\n"
                            + paciente.getCPF()
                    , Alert.AlertType.INFORMATION);
        } */

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/login-paciente.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
