package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Organismo;

import java.sql.*;
import java.util.ArrayList;

public class OrganismoDAO {

    public void inserir(Organismo org) throws SQLException {
        String sql = "INSERT INTO organismo (nome, descricao, id_classe) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, org.getNomeOrganismo());
            stmt.setString(2, org.getDescricao());
            stmt.setInt(3, org.getClasse().getId());

            stmt.executeUpdate();
        }
    }

    public ArrayList<Organismo> listar() throws SQLException {
        String sql = "SELECT * FROM organismo";
        ArrayList<Organismo> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Organismo o = new Organismo();
                o.setId(rs.getInt("id_organismo"));
                o.setNomeOrganismo(rs.getString("nome"));
                o.setDescricao(rs.getString("descricao"));
                lista.add(o);
            }
        }
        return lista;
    }

    public Organismo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM organismo WHERE id_organismo = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Organismo o = new Organismo();
                o.setId(rs.getInt("id_organismo"));
                o.setNomeOrganismo(rs.getString("nome"));
                o.setDescricao(rs.getString("descricao"));
                return o;
            }
        }
        return null;
    }

    public void atualizar(Organismo org) throws SQLException {
        String sql = "UPDATE organismo SET nome = ?, descricao = ?, id_classe = ? WHERE id_organismo = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, org.getNomeOrganismo());
            stmt.setString(2, org.getDescricao());
            stmt.setInt(3, org.getClasse().getId());
            stmt.setInt(4, org.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM organismo WHERE id_organismo = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
