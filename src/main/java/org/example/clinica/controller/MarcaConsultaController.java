package org.example.clinica.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.clinica.model.Consulta;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.ConsultaRepository;
import org.example.clinica.repository.MedicoRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MarcaConsultaController {

    @FXML
    private List<Medico> medicos;

    @FXML
    private final MedicoRepository medicoRepository = new MedicoRepository();

    @FXML
    private final ConsultaRepository consultaRepository = new ConsultaRepository();

    @FXML
    private Paciente pacienteLogado;

    @FXML
    private ComboBox<Medico> medicoComboBox;

    @FXML
    private TextField motivoField;

    public MarcaConsultaController() {}

    public void initialize() throws SQLException {
        List<Medico> medicos = medicoRepository.buscarTodosOsMedicos();

        medicoComboBox.getItems().addAll(medicos);

        medicoComboBox.setCellFactory(param -> new ListCell<Medico>() {
            @Override
            protected void updateItem(Medico medico, boolean empty) {
                super.updateItem(medico, empty);
                if (medico == null || empty) {
                    setText(null);
                } else {
                    setText(medico.getNome() + " - " + medico.getEspecialidade() + " - CRM: " + medico.getCRM());
                }
            }
        });

        medicoComboBox.setButtonCell(new ListCell<Medico>() {
            @Override
            protected void updateItem(Medico medico, boolean empty) {
                super.updateItem(medico, empty);
                if (medico == null || empty) {
                    setText(null);
                } else {
                    setText(medico.getNome() + " - " + medico.getEspecialidade() + " - CRM: " + medico.getCRM());
                }
            }
        });
    }



    @FXML
    private void agendarConsulta(ActionEvent actionEvent) throws SQLException {
        LocalDateTime agora = LocalDateTime.now();
        Timestamp timestampAgora = Timestamp.valueOf(agora);
        Medico medicoSelecionado = medicoComboBox.getValue();
        String motivo = motivoField.getText();
        Paciente pacienteLogado = this.pacienteLogado;

        Consulta consulta = new Consulta(motivo, timestampAgora, pacienteLogado, medicoSelecionado, false);

        if (medicoSelecionado == null || motivo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione um médico e preencha o motivo da consulta.", ButtonType.OK);
            alert.show();
        } else {
            consultaRepository.adicionarConsulta(consulta);
            System.out.println("Consulta marcada com: " + medicoSelecionado + " | Motivo: " + motivo);
        }
    }


    @FXML
    public void sair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/clinica/paciente-page.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
