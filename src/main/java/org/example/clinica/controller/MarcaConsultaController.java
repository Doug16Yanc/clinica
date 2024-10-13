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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.clinica.model.Medico;
import org.example.clinica.repository.MedicoRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class MarcaConsultaController {

    private ArrayList<Medico> medicos;
    private MedicoRepository medicoRepository;

    @FXML
    private ComboBox<String> medicoComboBox;

    @FXML
    private TextField motivoField;

    public MarcaConsultaController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }


    public void initialize() throws SQLException {
        medicos = (ArrayList<Medico>) medicoRepository.buscarTodosOsMedicos();
        for (Medico medico : medicos) {
            medicoComboBox.getItems().add(medico.getNome() + " - " + medico.getEspecialidade() + " - " + medico.getCRM());
        }
    }

    @FXML
    private void marcarConsulta(ActionEvent actionEvent) {
        String medicoSelecionado = medicoComboBox.getValue();
        String motivo = motivoField.getText();

        if (medicoSelecionado == null || motivo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione um médico e preencha o motivo da consulta.", ButtonType.OK);
            alert.show();
        } else {
            System.out.println("Consulta marcada com: " + medicoSelecionado + " | Motivo: " + motivo);
        }
    }

    @FXML
    private void voltar(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/paciente-page.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/paciente-page.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
