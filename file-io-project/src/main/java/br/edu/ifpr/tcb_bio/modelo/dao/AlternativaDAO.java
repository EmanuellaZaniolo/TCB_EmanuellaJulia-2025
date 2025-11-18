package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AlternativaDAO {

    public void inserir(Alternativa a, int idQuestao) throws Exception {
        String sql = "INSERT INTO alternativa(idQuestao, texto, correta) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idQuestao);
            ps.setString(2, a.getTexto());
            ps.setBoolean(3, a.isCorreta());
            ps.executeUpdate();
        }
    }

    public ArrayList<Alternativa> listar() throws Exception {
        ArrayList<Alternativa> lista = new ArrayList<>();
        String sql = "SELECT * FROM alternativa";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alternativa a = new Alternativa();
                a.setTexto(rs.getString("texto"));
                a.setCorreta(rs.getBoolean("correta"));
                lista.add(a);
            }
        }
        return lista;
    }

    public Alternativa buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM alternativa WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Alternativa a = new Alternativa();
                a.setTexto(rs.getString("texto"));
                a.setCorreta(rs.getBoolean("correta"));
                return a;
            }
        }
        return null;
    }

    public void atualizar(int id, Alternativa a) throws Exception {
        String sql = "UPDATE alternativa SET texto=?, correta=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getTexto());
            ps.setBoolean(2, a.isCorreta());
            ps.setInt(3, id);
            ps.executeUpdate();
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
