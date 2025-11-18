package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import java.sql.*;

public class CadastroDAO {

    public void inserir(Cadastro c) throws SQLException {
        String sql = "INSERT INTO cadastro (nome, usuario, senha) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNomePessoa());
            stmt.setString(2, c.getNomeUsuario());
            stmt.setString(3, c.getSenha());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) c.setId(rs.getInt(1));
        }
    }

    public Cadastro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE id_cadastro = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cadastro c = new Cadastro();
                c.setId(rs.getInt("id_cadastro"));
                c.setNomePessoa(rs.getString("nome"));
                c.setNomeUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                return c;
            }
        }
        return null;
    }

    // 🔥 AQUI: método que o controller usa
    public Cadastro buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE usuario = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cadastro c = new Cadastro();
                c.setId(rs.getInt("id_cadastro"));
                c.setNomePessoa(rs.getString("nome"));
                c.setNomeUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                return c;
            }
        }
        return null;
    }
}
