package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Classe;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ClasseDAO {

    public void inserir(Classe classe) throws SQLException {
        String sql = "INSERT INTO classe (nome, id_filo) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, classe.getNomeClasse());
            stmt.setInt(2, classe.getFilo().getId());

            stmt.executeUpdate();
        }
    }

    public ArrayList<Classe> listar() throws SQLException {
        String sql = "SELECT * FROM classe";
        ArrayList<Classe> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Classe c = new Classe();
                c.setId(rs.getInt("id_classe"));
                c.setNomeClasse(rs.getString("nome"));
                lista.add(c);
            }
        }
        return lista;
    }

    public Classe buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM classe WHERE id_classe = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Classe c = new Classe();
                c.setId(rs.getInt("id_classe"));
                c.setNomeClasse(rs.getString("nome"));
                return c;
            }
        }
        return null;
    }

    public void atualizar(Classe classe) throws SQLException {
        String sql = "UPDATE classe SET nome = ?, id_filo = ? WHERE id_classe = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, classe.getNomeClasse());
            stmt.setInt(2, classe.getFilo().getId());
            stmt.setInt(3, classe.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM classe WHERE id_classe = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
