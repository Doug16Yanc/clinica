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
import org.example.clinica.proofs.ConsultaRealizadaProof;
import org.example.clinica.repository.ConsultaRepository;
import org.example.clinica.repository.MedicoRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class RealizarConsultaController {
    @FXML
    private final MedicoRepository medicoRepository = new MedicoRepository();
    @FXML
    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    @FXML
    private ComboBox<Consulta> consultaComboBox;

    @FXML
    private Medico medicoLogado;

    public RealizarConsultaController() {}

    public void initialize(Medico medico) throws SQLException {
        this.medicoLogado = medico;

        if (medicoLogado == null) {
            new LoginMedicoController().showAlert("Falha", "Médico é nulo", Alert.AlertType.ERROR);
        }
        List<Consulta> consultas = medicoRepository.pegarConsultasPorMedico(medicoLogado);

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
                    setText(" " + consulta.getPaciente().getNome() + " - " + consulta.getId());
                }
            }
        });
    }

    public void sair(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/org/example/clinica/medico-page.fxml")));
        Parent root = loader.load();

        MedicoPageController medicoPageController = loader.getController();
        medicoPageController.setMedicoLogado(medicoLogado);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void realizarConsulta(ActionEvent actionEvent) throws SQLException {
        Consulta consultaSelecionada = consultaComboBox.getValue();

        if (consultaSelecionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione uma consulta válida.", ButtonType.OK);
            alert.show();
        } else {
            ConsultaRealizadaProof consultaRealizadaProof = new ConsultaRealizadaProof(consultaSelecionada);
            consultaRealizadaProof.show();
            consultaSelecionada.setStatus(true);
            consultaRepository.realizarConsulta(consultaSelecionada);
            //consultaRepository.removerConsulta(consultaSelecionada);
        }
    }
}
