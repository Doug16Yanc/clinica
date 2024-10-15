package org.example.clinica.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;
import org.example.clinica.model.Medico;
import org.example.clinica.repository.MedicoRepository;

import java.sql.SQLException;
import java.util.List;

public class VerHistoricoController extends Stage {

    @FXML
    private TextArea textArea;

    @FXML
    private Medico medicoLogado;

    public VerHistoricoController(Medico medico) {
        setTitle("Histórico de consultas de " + medico.getNome());
        setWidth(800);
        setHeight(600);

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 17px;");
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textArea);
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
        root.setBottom(buttonPanel);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root);
        setScene(scene);

        try {
            listarHistoricoDeConsultas(medico);
        } catch (SQLException e) {
            mostrarErro("Erro ao buscar histórico de consultas: " + e.getMessage());
        }

        show();
    }

    public void listarHistoricoDeConsultas(Medico medico) throws SQLException {
        MedicoRepository mr = new MedicoRepository();
        List<Consulta> consultas = mr.pegarConsultasPorMedico(medico);

        if (!consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(consulta)).append("\n\n");
            }
            textArea.setText(sb.toString());
        } else {
            mostrarAlerta("Nenhuma consulta marcada ainda.", "Histórico de Consultas");
        }
    }

    public String mostraConsultaPaciente(Consulta consulta) {
        return "----------------------------------------------------------------------\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Email: " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone: " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta: " + consulta.getId() + "\n" +
                "Motivo da consulta: " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta: " + consulta.getAgora() + "\n" +
                "Status da consulta: " + (consulta.getStatus() ? "Concluída" : "Pendente");
    }

    private void mostrarAlerta(String mensagem, String titulo) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void setMedicoLogado(Medico medicoLogado) {
        this.medicoLogado = medicoLogado;
    }
}
