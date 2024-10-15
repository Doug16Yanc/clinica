package org.example.clinica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.clinica.model.Medico;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MedicoPageController {

    @FXML
    private Medico medicoLogado;

    public void setMedicoLogado(Medico medico) {
        this.medicoLogado = medico;
    }

    @FXML
    public void realizarConsulta(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/org/example/clinica/realizar-consulta.fxml")));
        Parent root = loader.load();

        RealizarConsultaController realizarConsultaController = loader.getController();
        realizarConsultaController.initialize(medicoLogado);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void verHistorico(ActionEvent actionEvent) throws IOException {

        VerHistoricoController verHistoricoController = new VerHistoricoController(medicoLogado);
        verHistoricoController.setMedicoLogado(medicoLogado);
    }

    @FXML
    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/initial-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Medico getMedicoLogado() {
        return medicoLogado;
    }
}
