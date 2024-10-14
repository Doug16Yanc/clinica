package org.example.clinica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;
import org.example.clinica.model.Medico;
import org.example.clinica.repository.ConsultaRepository;
import org.example.clinica.repository.MedicoRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RealizarConsultaController {
    private ArrayList<Medico> medicos;
    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    @FXML
    private ComboBox<Consulta> consultaComboBox;

    public RealizarConsultaController() {}


    @FXML
    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/medico-page.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
/*
    public void initialize() throws SQLException {
        List<Consulta> consultas = consultaRepository.buscarConsultasPorMedico();

        consultaComboBox.getItems().addAll(consultas);

        consultaComboBox.setCellFactory(param -> new ListCell<Consulta>() {
            @Override
            protected void updateItem(Consulta consulta, boolean empty) {
                super.updateItem(consulta, empty);
                if (consulta == null || empty) {
                    setText(null);
                } else {
                    setText(" " + consulta.getPaciente().getNome() + "  " + consulta.getId() + " - " + consulta.getAgora());
                }
            }
        });

        consultaComboBox.setButtonCell(new ListCell<Consulta>() {
            @Override
            protected void updateItem(Consulta consulta, boolean empty) {
                super.updateItem(consulta, empty);
                if (consulta == null || empty) {
                    setText(null);
                } else {
                    setText(" " + consulta.getPaciente().getNome() + "  " + consulta.getId() + " - " + consulta.getAgora());
                }
            }
        });
    } */

    @FXML
    private void realizarConsulta(ActionEvent actionEvent) {
        String consultaSelecionada = String.valueOf(consultaComboBox.getValue());

        if (consultaSelecionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione uma consulta válida.", ButtonType.OK);
            alert.show();
        } else {
            System.out.println("Consulta realizada para o paciente " + consultaSelecionada);
        }
    }
}
