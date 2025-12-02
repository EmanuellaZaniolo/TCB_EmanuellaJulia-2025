package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class PerfilDAO {

    // metodo para inserir um novo perfil no banco de dados
    public int inserir(Perfil p, int idCadastro) throws Exception {
        String sql = "insert into perfil(idCadastro, totalAcertos) values (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idCadastro);  // define o id do cadastro no perfil
            ps.setInt(2, p.getTotalAcertos());  // define o total de acertos no perfil
            ps.executeUpdate();  // executa a consulta no banco de dados

            try (ResultSet keys = ps.getGeneratedKeys()) {  // pega o id gerado automaticamente
                if (keys.next()) {  // se o id foi gerado
                    int idGerado = keys.getInt(1);  // obtem o id gerado
                    p.setId(idGerado);  // seta o id no objeto perfil
                    return idGerado;  // retorna o id gerado
                }
            }
        }
        return -1;  // se não gerar um id, retorna -1
    }

    // metodo para buscar um perfil pelo id do cadastro
    public Perfil buscarPorCadastroId(int idCadastro) throws Exception {
        String sql = "select * from perfil where idCadastro = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCadastro);  // define o id do cadastro na consulta
            try (ResultSet rs = ps.executeQuery()) {  // executa a consulta e obtém o resultado
                if (rs.next()) {  // se o perfil for encontrado
                    Perfil p = new Perfil();  // cria um novo objeto perfil
                    if (columnExists(rs, "id")) {  // verifica se a coluna "id" existe no resultado
                        try { 
                            p.setId(rs.getInt("id"));  // seta o id do perfil
                        } catch (SQLException ignored) {}
                    }
                    p.setTotalAcertos(rs.getInt("totalAcertos"));  // seta o total de acertos no perfil
                    return p;  // retorna o perfil encontrado
                }
            }
        }
        return null;  // retorna null se o perfil não for encontrado
    }

    // metodo para atualizar os dados de um perfil no banco de dados
    public void atualizar(int id, Perfil p) throws Exception {
        String sql = "update perfil set totalAcertos=? where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getTotalAcertos());  // define o novo total de acertos no perfil
            ps.setInt(2, id);  // define o id do perfil a ser atualizado
            ps.executeUpdate();  // executa a atualização no banco de dados
        }
    }

   public ArrayList<Perfil> listar() throws Exception {
    ArrayList<Perfil> lista = new ArrayList<>();

    String sql = """
        SELECT p.id, p.totalAcertos,
               c.id AS cadastro_id, 
               c.nomePessoa
        FROM perfil p
        JOIN cadastro c ON p.idCadastro = c.id
    """;

    try (Connection con = ConnectionFactory.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Perfil p = new Perfil();
            p.setId(rs.getInt("id"));
            p.setTotalAcertos(rs.getInt("totalAcertos"));

            Cadastro cad = new Cadastro();
            cad.setId(rs.getInt("cadastro_id"));
            cad.setNomePessoa(rs.getString("nomePessoa"));

            p.setCadastro(cad); // AGORA O PERFIL TEM O NOME!

            lista.add(p);
        }
    }

    return lista;
}


    // metodo para verificar se uma coluna existe no resultado da consulta
    private boolean columnExists(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);  // tenta encontrar a coluna no resultado
            return true;  // retorna true se a coluna existe
        } catch (SQLException e) {
            return false;  // retorna false se a coluna não existe
        }
    }

    // metodo para deletar um perfil do banco de dados
   public void deletar(int id) throws Exception {
        String sql = "delete from perfil where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // define o id do perfil a ser deletado
            ps.executeUpdate();  // executa a consulta para deletar o perfil
        }
    }
}
