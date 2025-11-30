package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Questao;

import java.sql.*;
import java.util.ArrayList;

public class QuestaoDAO {

    public int inserir(Questao q, int idReino) throws Exception {
        String sql = "INSERT INTO questao(idReino, enunciado) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idReino);
            ps.setString(2, q.getEnunciado());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public ArrayList<Questao> buscarPorReino(int idReino) throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE idReino = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReino);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Questao q = new Questao();
                    q.setId(rs.getInt("id"));
                    q.setEnunciado(rs.getString("enunciado"));
                    q.setIdReino(rs.getInt("idReino"));
                    lista.add(q);
                }
            }
        }
        return lista;
    }

    public ArrayList<Questao> listar() throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();
        String sql = "SELECT * FROM questao";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Questao q = new Questao();
                q.setId(rs.getInt("id"));
                q.setEnunciado(rs.getString("enunciado"));
                q.setIdReino(rs.getInt("idReino"));
                lista.add(q);
            }
        }
        return lista;
    }

    public Questao buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM questao WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Questao q = new Questao();
                    q.setId(rs.getInt("id"));
                    q.setEnunciado(rs.getString("enunciado"));
                    q.setIdReino(rs.getInt("idReino"));
                    return q;
                }
            }
        }
        return null;
    }

    public void atualizar(int id, Questao q) throws Exception {
        String sql = "UPDATE questao SET enunciado=?, idReino=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, q.getEnunciado());
            ps.setInt(2, q.getIdReino());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM questao WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
