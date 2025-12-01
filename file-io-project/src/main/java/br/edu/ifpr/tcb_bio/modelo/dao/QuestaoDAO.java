package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Questao;

import java.sql.*;
import java.util.ArrayList;

public class QuestaoDAO {

    // metodo para inserir uma nova questao no banco de dados
    public int inserir(Questao q, int idReino) throws Exception {
        String sql = "insert into questao(idReino, enunciado) values (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idReino);  // define o id do reino
            ps.setString(2, q.getEnunciado());  // define o enunciado da questao
            ps.executeUpdate();  // executa a insercao da questao

            try (ResultSet keys = ps.getGeneratedKeys()) {  // pega a chave gerada automaticamente
                if (keys.next()) return keys.getInt(1);  // retorna o id gerado da questao
            }
        }
        return -1;  // retorna -1 caso nao consiga inserir a questao
    }

    // metodo para buscar as questoes por id do reino
    public ArrayList<Questao> buscarPorReino(int idReino) throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();
        String sql = "select * from questao where idReino = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReino);  // define o id do reino
            try (ResultSet rs = ps.executeQuery()) {  // executa a consulta para pegar as questoes
                while (rs.next()) {  // enquanto houver resultados
                    Questao q = new Questao();  // cria um novo objeto Questao
                    q.setId(rs.getInt("id"));  // seta o id da questao
                    q.setEnunciado(rs.getString("enunciado"));  // seta o enunciado da questao
                    q.setIdReino(rs.getInt("idReino"));  // seta o id do reino
                    lista.add(q);  // adiciona a questao à lista
                }
            }
        }
        return lista;  // retorna a lista de questoes
    }

    // metodo para listar todas as questoes no banco de dados
    public ArrayList<Questao> listar() throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();
        String sql = "select * from questao";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {  // executa a consulta para pegar todas as questoes

            while (rs.next()) {  // enquanto houver resultados
                Questao q = new Questao();  // cria um novo objeto Questao
                q.setId(rs.getInt("id"));  // seta o id da questao
                q.setEnunciado(rs.getString("enunciado"));  // seta o enunciado da questao
                q.setIdReino(rs.getInt("idReino"));  // seta o id do reino
                lista.add(q);  // adiciona a questao à lista
            }
        }
        return lista;  // retorna a lista de questoes
    }

    // metodo para buscar uma questao pelo id
    public Questao buscarPorId(int id) throws Exception {
        String sql = "select * from questao where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // define o id da questao
            try (ResultSet rs = ps.executeQuery()) {  // executa a consulta para pegar a questao com o id fornecido
                if (rs.next()) {  // se encontrar a questao
                    Questao q = new Questao();  // cria um novo objeto Questao
                    q.setId(rs.getInt("id"));  // seta o id da questao
                    q.setEnunciado(rs.getString("enunciado"));  // seta o enunciado da questao
                    q.setIdReino(rs.getInt("idReino"));  // seta o id do reino
                    return q;  // retorna a questao encontrada
                }
            }
        }
        return null;  // retorna null caso nao encontre a questao
    }

    // metodo para atualizar uma questao no banco de dados
    public void atualizar(int id, Questao q) throws Exception {
        String sql = "update questao set enunciado=?, idReino=? where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, q.getEnunciado());  // seta o enunciado da questao
            ps.setInt(2, q.getIdReino());  // seta o id do reino
            ps.setInt(3, id);  // define o id da questao
            ps.executeUpdate();  // executa a atualizacao da questao
        }
    }

    // metodo para deletar uma questao do banco de dados
    public void deletar(int id) throws Exception {
        String sql = "delete from questao where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // define o id da questao a ser deletada
            ps.executeUpdate();  // executa a delecao da questao
        }
    }
}
