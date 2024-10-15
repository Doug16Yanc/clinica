package org.example.clinica.proofs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.clinica.model.Consulta;

public class ConsultaRealizadaProof extends Stage {

    private TextArea consultaRealizada;

    public ConsultaRealizadaProof(Consulta consulta) {
        setTitle("Finalização de Consulta");
        setWidth(500);
        setHeight(400);

        consultaRealizada = new TextArea();
        consultaRealizada.setEditable(false);
        consultaRealizada.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 18px;");
        consultaRealizada.setText(comprovarConsulta(consulta));

        ScrollPane scrollPane = new ScrollPane(consultaRealizada);
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

    private String comprovarConsulta(Consulta consulta) {
        return "\nConsulta realizada com sucesso para " + consulta.getPaciente().getNome()  +
                "\nPaciente relatou " + consulta.getMotivo() +
                "\nIdentificador da consulta : " + consulta.getId() +
                "\nAtenção às contraindicações medicamentosas.";
    }
}
