package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;

public class CadastroDAO {

    public int inserir(Cadastro c) throws SQLException {
        String sql = "INSERT INTO cadastro (nomePessoa, nomeUsuario, email, senha, tipoUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNomePessoa());
            ps.setString(2, c.getNomeUsuario());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getSenha());
            ps.setString(5, c.getTipoUsuario());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public Cadastro buscarPorUsuario(String nomeUsuario) throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE nomeUsuario = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cadastro c = new Cadastro();
                    c.setId(rs.getInt("id"));
                    c.setNomePessoa(rs.getString("nomePessoa"));
                    c.setNomeUsuario(rs.getString("nomeUsuario"));
                    c.setEmail(rs.getString("email"));
                    c.setSenha(rs.getString("senha"));
                    c.setTipoUsuario(rs.getString("tipoUsuario"));
                    return c;
                }
            }
        }
        return null;
    }
}
