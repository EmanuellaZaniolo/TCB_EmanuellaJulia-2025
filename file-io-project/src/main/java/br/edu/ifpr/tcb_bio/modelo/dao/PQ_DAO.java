package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import br.edu.ifpr.tcb_bio.modelo.PQ;

public class PQ_DAO {

    public void salvar(PQ pq) throws Exception {
        String sql = "INSERT INTO perfil_questao (idPerfil, idQuestao) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pq.getIdPerfil());
            ps.setInt(2, pq.getIdQuestao());
            ps.executeUpdate();
        }
    }

    public ArrayList<PQ> listarPorPerfil(int idPerfil) throws Exception {
        ArrayList<PQ> lista = new ArrayList<>();
        String sql = "SELECT * FROM perfil_questao WHERE idPerfil = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPerfil);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PQ(
                        rs.getInt("idPerfil"),
                        rs.getInt("idQuestao")
                    ));
                }
            }
        }
        return lista;
    }

    public ArrayList<PQ> listarPorQuestao(int idQuestao) throws Exception {
        ArrayList<PQ> lista = new ArrayList<>();
        String sql = "SELECT * FROM perfil_questao WHERE idQuestao = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idQuestao);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PQ(
                        rs.getInt("idPerfil"),
                        rs.getInt("idQuestao")
                    ));
                }
            }
        }
        return lista;
    }
}
