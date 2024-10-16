package org.example.clinica.repository;

import org.example.clinica.model.Consulta;
import org.example.clinica.model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaRepository {

    public void adicionarConsulta(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (ID, MOTIVO, AGORA, PACIENTE_ID, MEDICO_ID, STATUS) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, consulta.getId().toString());
            stmt.setString(2, consulta.getMotivo());
            stmt.setTimestamp(3, consulta.getAgora());
            stmt.setInt(4, consulta.getPaciente().getId());
            stmt.setInt(5, consulta.getMedico().getId());
            stmt.setBoolean(6, consulta.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar consulta: " + e.getMessage());
            throw new SQLException("Erro ao tentar adicionar consulta no banco de dados.", e);
        }
    }

    public List<Consulta> buscarConsultasPorMedico(int medicoId) throws SQLException {
        String sql = """
        SELECT c.*, p.id as paciente_id, p.nome as paciente_nome, p.email as paciente_email, p.telefone as paciente_telefone
        FROM consulta c
        JOIN paciente p ON c.paciente_id = p.id
        WHERE c.medico_id = ?
        """;

        List<Consulta> consultas = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, medicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(UUID.fromString(rs.getString("id")));
                consulta.setMotivo(rs.getString("motivo"));
                consulta.setAgora(rs.getTimestamp("agora"));

                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                paciente.setEmail(rs.getString("paciente_email"));
                paciente.setTelefone(rs.getString("paciente_telefone"));
                consulta.setPaciente(paciente);
                consulta.setStatus(rs.getBoolean("status"));

                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultas: " + e.getMessage());
            throw new SQLException("Erro ao tentar buscar consultas no banco de dados.", e);
        }

        return consultas;
    }

    public void realizarConsulta(Consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET STATUS = ? WHERE ID = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setString(2, consulta.getId().toString());
            stmt.executeUpdate();
            consulta.setStatus(true);
        } catch (SQLException e) {
            System.err.println("Erro ao realizar consulta: " + e.getMessage());
            throw new SQLException("Erro ao tentar realizar a consulta no banco de dados.", e);
        }
    }

   /* public void atualizarConsulta(Consulta consulta) throws SQLException {
        String sql = "UPDATE CONSULTA SET STATUS = ? WHERE ID = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setString(1, consulta.getId().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
            throw new SQLException("Erro ao tentar atualizar consulta do banco de dados.", e);
        }
    }*/
}