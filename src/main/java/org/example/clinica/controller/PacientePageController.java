package org.example.clinica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.clinica.model.Paciente;

import java.io.IOException;
import java.util.Objects;

public class PacientePageController {
    private Paciente pacienteLogado;

    public void setPacienteLogado(Paciente paciente) {
        this.pacienteLogado = paciente;
    }


    @FXML
    public void marcarConsulta(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LoginMedicoController.class.getResource("/org/example/clinica/marca-consulta.fxml")));
        Parent root = loader.load();

        MarcaConsultaController marcaConsultaController = loader.getController();
        marcaConsultaController.setPacienteLogado(pacienteLogado);

        Stage stageAtual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageAtual.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void verConsultas(ActionEvent actionEvent) throws IOException {

       VerConsultaController verConsultaController = new VerConsultaController(pacienteLogado);
       verConsultaController.setPacienteLogado(pacienteLogado);
    }

    @FXML
    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/initial-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
