package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Reino;

import java.sql.*;
import java.util.ArrayList;

public class ReinoDAO {

    public ArrayList<Reino> listar() throws Exception {
        ArrayList<Reino> lista = new ArrayList<>();
        String sql = "SELECT * FROM reino";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reino r = new Reino();
                // leitura tolerante de nomes de coluna
                r.setId(getIntSafe(rs, "id", -1));
                r.setNomeReino(getStringSafe(rs, "nomeReino", "nome"));
                r.setDescricao(getStringSafe(rs, "descricao", "descr"));
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
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reino r = new Reino();
                    r.setId(getIntSafe(rs, "id", -1));
                    r.setNomeReino(getStringSafe(rs, "nomeReino", "nome"));
                    r.setDescricao(getStringSafe(rs, "descricao", "descr"));
                    return r;
                }
            }
        }
        return null;
    }

    private String getStringSafe(ResultSet rs, String primary, String fallback) {
        try {
            return rs.getString(primary);
        } catch (SQLException e) {
            try {
                return rs.getString(fallback);
            } catch (SQLException ex) {
                return null;
            }
        }
    }

    private int getIntSafe(ResultSet rs, String primary, int fallback) {
        try {
            return rs.getInt(primary);
        } catch (SQLException e) {
            return fallback;
        }
    }
}
