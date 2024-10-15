package org.example.clinica.proofs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;

public class ComprovanteConsultaProof extends Stage {

    private TextArea comprovanteArea;

    public ComprovanteConsultaProof(Consulta consulta) {
        setTitle("Comprovante de Consulta");
        setWidth(700);
        setHeight(500);

        comprovanteArea = new TextArea();
        comprovanteArea.setEditable(false);
        comprovanteArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 18px;");
        comprovanteArea.setText(gerarComprovante(consulta));

        ScrollPane scrollPane = new ScrollPane(comprovanteArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button fecharButton = new Button("Fechar");
        fecharButton.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 20px; -fx-background-color: #FF001A; -fx-text-fill: white;");
        fecharButton.setPadding(new Insets(17, 65, 17, 65));
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
    }

    private String gerarComprovante(Consulta consulta) {
        return "\tCOMPROVANTE DE CONSULTA.\n\n" +
                "Código da consulta: " + consulta.getId() + "\n" +
                "Data e hora marcada: " + consulta.getAgora() + "\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Paciente relata: " + consulta.getMotivo() + "\n" +
                "Identificador do(a) médico(a): " + consulta.getMedico().getId() + "\n" +
                "Nome do(a) médico(a): " + consulta.getMedico().getNome();
    }
}
