package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AlternativaDAO {

    public int inserir(Alternativa a, int idQuestao) throws Exception {
        String insertSql = "INSERT INTO alternativa(idQuestao, texto, correta) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);
            try {
                if (a.isCorreta()) {
                    // marca todas as outras como falsas antes de inserir
                    try (PreparedStatement psFalse = con.prepareStatement(
                            "UPDATE alternativa SET correta = false WHERE idQuestao = ?")) {
                        psFalse.setInt(1, idQuestao);
                        psFalse.executeUpdate();
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, idQuestao);
                    ps.setString(2, a.getTexto());
                    ps.setBoolean(3, a.isCorreta());
                    ps.executeUpdate();

                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        int idGerado = -1;
                        if (keys.next()) {
                            idGerado = keys.getInt(1);
                        }
                        con.commit();
                        return idGerado;
                    }
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public ArrayList<Alternativa> listarPorQuestao(int idQuestao) throws Exception {
        ArrayList<Alternativa> lista = new ArrayList<>();
        String sql = "SELECT * FROM alternativa WHERE idQuestao = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idQuestao);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alternativa a = new Alternativa();
                    a.setId(rs.getInt("id"));
                    a.setTexto(rs.getString("texto"));
                    a.setCorreta(rs.getBoolean("correta"));
                    a.setIdQuestao(rs.getInt("idQuestao"));
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public void atualizar(int id, Alternativa a) throws Exception {
        String updateSql = "UPDATE alternativa SET texto=?, correta=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);
            try {
                if (a.isCorreta()) {
                    // busca idQuestao para apagar outras corretas
                    int idQuestao = a.getIdQuestao();
                    try (PreparedStatement psFalse = con.prepareStatement(
                            "UPDATE alternativa SET correta = false WHERE idQuestao = ?")) {
                        psFalse.setInt(1, idQuestao);
                        psFalse.executeUpdate();
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                    ps.setString(1, a.getTexto());
                    ps.setBoolean(2, a.isCorreta());
                    ps.setInt(3, id);
                    ps.executeUpdate();
                }
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM alternativa WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
