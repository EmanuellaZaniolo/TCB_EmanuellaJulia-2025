package br.edu.ifpr.tcb_bio.modelo.dao;

import java.sql.*;
import java.util.ArrayList;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

public class QADAO {

    public void inserir(int idQuestao, int idAlternativa) throws SQLException {
        String sql = "INSERT INTO questao_alternativa (id_questao, id_alternativa) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idQuestao);
            stmt.setInt(2, idAlternativa);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Integer> listarAlternativasDaQuestao(int idQuestao) throws SQLException {
        String sql = "SELECT id_alternativa FROM questao_alternativa WHERE id_questao = ?";
        ArrayList<Integer> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idQuestao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("id_alternativa"));
            }
        }
        return lista;
    }

    public void deletarPorQuestao(int idQuestao) throws SQLException {
        String sql = "DELETE FROM questao_alternativa WHERE id_questao = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idQuestao);
            stmt.executeUpdate();
        }
    }
}
