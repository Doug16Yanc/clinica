package org.example.clinica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void pacienteBtn(ActionEvent actionEvent) {
    }

    public void medicoBtn(ActionEvent actionEvent) {
    }
}
