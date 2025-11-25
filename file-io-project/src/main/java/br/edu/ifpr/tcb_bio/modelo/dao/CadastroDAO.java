package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroDAO {

    public void inserir(Cadastro c) throws SQLException {
        String sql = "INSERT INTO cadastro (nomePessoa, nomeUsuario, email, senha, tipoUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
        //conecta ao banco
             PreparedStatement ps = con.prepareStatement(sql)) {
                // vai setando as informações do cadastro 
            ps.setString(1, c.getNomePessoa());
            ps.setString(2, c.getNomeUsuario());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getSenha());
            ps.setString(5, c.getTipoUsuario());
            
            ps.executeUpdate();//atualiza
        }
    }
    //busca por nome de usuario(user)
    public Cadastro buscarPorUsuario(String nomeUsuario) throws SQLException {
        String sql = "SELECT * FROM cadastro WHERE nomeUsuario = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {//retorna infos do cadastro
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
        return null;
    }
}
