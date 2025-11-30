package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class PerfilDAO {

    public int inserir(Perfil p, int idCadastro) throws Exception {
        String sql = "INSERT INTO perfil(idCadastro, totalAcertos) VALUES (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idCadastro);
            ps.setInt(2, p.getTotalAcertos());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int idGerado = keys.getInt(1);
                    p.setId(idGerado); // seta id no objeto recebido
                    return idGerado;
                }
            }
        }
        return -1;
    }

    public Perfil buscarPorCadastroId(int idCadastro) throws Exception {
        String sql = "SELECT * FROM perfil WHERE idCadastro = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCadastro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Perfil p = new Perfil();
                    // Se existir coluna id, lê e seta
                    if (columnExists(rs, "id")) {
                        try { p.setId(rs.getInt("id")); } catch (SQLException ignored) {}
                    }
                    p.setTotalAcertos(rs.getInt("totalAcertos"));
                    return p;
                }
            }
        }
        return null;
    }

    public void atualizar(int id, Perfil p) throws Exception {
        String sql = "UPDATE perfil SET totalAcertos=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getTotalAcertos());
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public ArrayList<Perfil> listar() throws Exception {
        ArrayList<Perfil> lista = new ArrayList<>();
        String sql = "SELECT * FROM perfil";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Perfil p = new Perfil();
                if (columnExists(rs, "id")) {
                    try { p.setId(rs.getInt("id")); } catch (SQLException ignored) {}
                }
                p.setTotalAcertos(rs.getInt("totalAcertos"));
                lista.add(p);
            }
        }
        return lista;
    }

    private boolean columnExists(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
