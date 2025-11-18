package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Perfil;

import java.sql.*;
import java.util.ArrayList;

public class RankingDAO {

    public ArrayList<Perfil> listarRanking() throws SQLException {
        String sql = """
            SELECT c.nome, p.total_acertos 
            FROM perfil p
            JOIN cadastro c ON p.id_cadastro = c.id_cadastro
            ORDER BY total_acertos DESC
        """;

        ArrayList<Perfil> lista = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setTotalAcertos(rs.getInt("total_acertos"));
                lista.add(perfil);
            }
        }
        return lista;
    }
}
