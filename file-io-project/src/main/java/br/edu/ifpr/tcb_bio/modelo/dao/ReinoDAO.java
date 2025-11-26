package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Reino;

import java.sql.*;
import java.util.ArrayList;

public class ReinoDAO {

    public void inserir(Reino r) throws Exception {
        String sql = "INSERT INTO reino(nome, descricao) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNomeReino());
            ps.setString(2, r.getDescricao());
            ps.executeUpdate();
        }
    }

    public ArrayList<Reino> listar() throws Exception {
        ArrayList<Reino> lista = new ArrayList<>();
        String sql = "SELECT * FROM reino";
    
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
    
            while (rs.next()) {
                Reino r = new Reino();
                r.setId(rs.getInt("id"));               
                r.setNomeReino(rs.getString("nome"));
                r.setDescricao(rs.getString("descricao"));
                lista.add(r);
            }
        }
        return lista;
    }
    
    public Reino buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM reino WHERE id = ?";
    
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
    
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                Reino r = new Reino();
                r.setId(rs.getInt("id"));  
                r.setNomeReino(rs.getString("nome"));
                r.setDescricao(rs.getString("descricao"));
                return r;
            }
        }
        return null;
    }
    
    public void atualizar(int id, Reino r) throws Exception {
        String sql = "UPDATE reino SET nome=?, descricao=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNomeReino());
            ps.setString(2, r.getDescricao());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM reino WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
