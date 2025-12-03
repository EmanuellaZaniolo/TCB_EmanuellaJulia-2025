package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import br.edu.ifpr.tcb_bio.modelo.PQ;

public class PQ_DAO {

    public void salvar(PQ pq) throws Exception {
        String sql = "INSERT INTO perfil_questao (id_perfil, id_questao) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pq.getIdPerfil());
            ps.setInt(2, pq.getIdQuestao());
            ps.executeUpdate();
        }
    }

    public ArrayList<PQ> listarPorPerfil(int id_perfil) throws Exception {
        ArrayList<PQ> lista = new ArrayList<>();
        String sql = "SELECT * FROM perfil_questao WHERE id_perfil = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_perfil);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PQ(
                        rs.getInt("id_perfil"),
                        rs.getInt("id_questao")
                    ));
                }
            }
        }
        return lista;
    }

    public ArrayList<PQ> listarPorQuestao(int id_questao) throws Exception {
        ArrayList<PQ> lista = new ArrayList<>();
        String sql = "SELECT * FROM perfil_questao WHERE id_questao = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_questao);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PQ(
                        rs.getInt("id_perfil"),
                        rs.getInt("id_questao")
                    ));
                }
            }
        }
        return lista;
    }
}
