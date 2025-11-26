package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Questao;

import java.sql.*;
import java.util.ArrayList;

public class QuestaoDAO {

    public void inserir(Questao q, int idReino) throws Exception {
        String sql = "INSERT INTO questao(idReino, enunciado) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReino);
            ps.setString(2, q.getEnunciado());
            ps.executeUpdate();
        }
    }
    public ArrayList<Questao> buscarPorReino(int idReino) throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE idReino = ?";
    
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
    
            ps.setInt(1, idReino);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Questao q = new Questao();
                q.setId(rs.getInt("id"));
                q.setEnunciado(rs.getString("enunciado"));
                lista.add(q);
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
                q.setEnunciado(rs.getString("enunciado"));
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
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Questao q = new Questao();
                q.setEnunciado(rs.getString("enunciado"));
                return q;
            }
        }
        return null;
    }

    public void atualizar(int id, Questao q) throws Exception {
        String sql = "UPDATE questao SET enunciado=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, q.getEnunciado());
            ps.setInt(2, id);
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
