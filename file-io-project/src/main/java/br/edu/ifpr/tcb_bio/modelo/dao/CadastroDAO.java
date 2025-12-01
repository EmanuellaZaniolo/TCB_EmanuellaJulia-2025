package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;

public class CadastroDAO {

    // metodo para inserir um novo cadastro no banco de dados
    public int inserir(Cadastro c) throws SQLException {
        String sql = "insert into cadastro (nomePessoa, nomeUsuario, email, senha, tipoUsuario) values (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNomePessoa());  // seta o nome da pessoa
            ps.setString(2, c.getNomeUsuario());  // seta o nome de usuario
            ps.setString(3, c.getEmail());  // seta o email
            ps.setString(4, c.getSenha());  // seta a senha
            ps.setString(5, c.getTipoUsuario());  // seta o tipo de usuario

            ps.executeUpdate();  // executa a insercao no banco

            try (ResultSet keys = ps.getGeneratedKeys()) {  // pega a chave gerada automaticamente
                if (keys.next()) return keys.getInt(1);  // retorna o id gerado
            }
        }
        return -1;  // retorna -1 caso nao tenha conseguido inserir
    }

    // metodo para buscar um cadastro pelo nome de usuario
    public Cadastro buscarPorUsuario(String nomeUsuario) throws SQLException {
        String sql = "select * from cadastro where nomeUsuario = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);  // define o nome do usuario na consulta
            try (ResultSet rs = ps.executeQuery()) {  // executa a consulta
                if (rs.next()) {  // se encontrar o usuario
                    Cadastro c = new Cadastro();  // cria novo objeto cadastro
                    c.setId(rs.getInt("id"));  // seta o id
                    c.setNomePessoa(rs.getString("nomePessoa"));  // seta nome da pessoa
                    c.setNomeUsuario(rs.getString("nomeUsuario"));  // seta nome de usuario
                    c.setEmail(rs.getString("email"));  // seta email
                    c.setSenha(rs.getString("senha"));  // seta senha
                    c.setTipoUsuario(rs.getString("tipoUsuario"));  // seta tipo de usuario
                    return c;  // retorna o cadastro encontrado
                }
            }
        }
        return null;  // retorna null caso nao encontre o usuario
    }
}
