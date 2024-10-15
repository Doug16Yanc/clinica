package org.example.clinica.controller;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.PacienteRepository;

import java.sql.SQLException;
import java.util.List;


public class VerConsultaController extends Stage {

    @FXML
    private TextArea consultasArea;

    @FXML
    private Paciente pacienteLogado;

    public VerConsultaController(Paciente pacienteLogado) {
        setTitle("Consultas do paciente " + pacienteLogado.getNome());
        setWidth(800);
        setHeight(600);

        consultasArea = new TextArea();
        consultasArea.setEditable(false);
        consultasArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 17px;");

        ScrollPane scrollPane = new ScrollPane(consultasArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button fecharButton = new Button("Fechar");
        fecharButton.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 20px; -fx-background-color: #FF001A; -fx-text-fill: white;");
        fecharButton.setPadding(new Insets(10, 50, 10, 50));
        fecharButton.setOnAction(e -> close());

        HBox buttonPanel = new HBox();
        buttonPanel.setPadding(new Insets(10));
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.getChildren().add(fecharButton);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(buttonPanel);

        Scene scene = new Scene(root);
        setScene(scene);
        show();

        try {
            listarConsultasPorPaciente(pacienteLogado);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao buscar consultas: " + e.getMessage());
        }
    }

    private void listarConsultasPorPaciente(Paciente paciente) throws SQLException {
        PacienteRepository pr = new PacienteRepository();
        List<Consulta> consultas = pr.pegarConsultasPorPaciente(paciente);

        if (consultas != null && !consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(gerarDetalhesConsulta(consulta)).append("\n\n");
            }
            consultasArea.setText(sb.toString());
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Consultas por Paciente", "Nenhuma consulta marcada ainda.");
        }
    }

    private String gerarDetalhesConsulta(Consulta consulta) {
        return "----------------------------------------------------------------------\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Email: " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone: " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta: " + consulta.getId() + "\n" +
                "Motivo da consulta: " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta: " + consulta.getAgora() + "\n" +
                "Médico responsável: " + consulta.getMedico().getNome() + "\n" +
                "Status: " + (consulta.getStatus() ? "Concluída" : "Pendente");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }
}
