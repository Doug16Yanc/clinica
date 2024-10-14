package org.example.clinica.application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.PacienteRepository;

import java.sql.SQLException;
import java.util.List;

public class VerConsultaApplication extends Application {

    private Paciente paciente;

    public VerConsultaApplication(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ver Consultas");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        try {
            listarConsultasPorPaciente(root, paciente);
        } catch (SQLException e) {
            mostrarAlertaErro("Erro ao buscar consultas: " + e.getMessage());
        }

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void listarConsultasPorPaciente(BorderPane root, Paciente paciente) throws SQLException {
        PacienteRepository pr = new PacienteRepository();
        List<Consulta> consultas = pr.pegarConsultasPorPaciente(paciente);

        if (consultas != null && !consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(consulta)).append("\n\n");
            }

            TextArea textArea = new TextArea(sb.toString());
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 17;");

            ScrollPane scrollPane = new ScrollPane(textArea);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefSize(700, 500);

            root.setCenter(scrollPane);
        } else {
            mostrarAlertaInformacao("Nenhuma consulta marcada ainda.", "Consultas por Paciente");
        }
    }

    public String mostraConsultaPaciente(Consulta consulta) {
        Medico medico = consulta.getMedico();
        return "----------------------------------------------------------------------\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Email: " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone: " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta: " + consulta.getId() + "\n" +
                "Motivo da consulta: " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta: " + consulta.getAgora() + "\n" +
                "Médico responsável: " + (medico != null ? medico.getNome() : "Não disponível") + "\n" +
                "Status: " + (consulta.getStatus() ? "Concluída" : "Pendente");
    }

    private void mostrarAlertaErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarAlertaInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
