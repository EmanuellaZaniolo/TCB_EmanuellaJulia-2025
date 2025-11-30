package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class RankingDAO {

    public ArrayList<Perfil> listarRanking() throws SQLException {
        String sql = ""
            + "SELECT c.nomePessoa as nome, p.totalAcertos "
            + "FROM perfil p "
            + "JOIN cadastro c ON p.idCadastro = c.id "
            + "ORDER BY p.totalAcertos DESC";

        ArrayList<Perfil> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setTotalAcertos(rs.getInt("totalAcertos"));
                lista.add(perfil);
            }
        }
        return lista;
    }
}
