package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class PerfilDAO {

    public void inserir(Perfil p, int idCadastro) throws Exception {
        String sql = "INSERT INTO perfil(idCadastro, totalAcertos) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCadastro);
            ps.setInt(2, p.getTotalAcertos());
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
                p.setTotalAcertos(rs.getInt("totalAcertos"));
                lista.add(p);
            }
        }
        return lista;
    }

    public Perfil buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM perfil WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Perfil p = new Perfil();
                p.setTotalAcertos(rs.getInt("totalAcertos"));
                return p;
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

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM perfil WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}