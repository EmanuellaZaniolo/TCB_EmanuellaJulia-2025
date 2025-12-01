package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Reino;

import java.sql.*;
import java.util.ArrayList;

public class ReinoDAO {

    // metodo para listar todos os reinos no banco de dados
    public ArrayList<Reino> listar() throws Exception {
        ArrayList<Reino> lista = new ArrayList<>();
        String sql = "select * from reino";  // consulta para pegar todos os reinos
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {  // executa a consulta e obtém o resultado
            while (rs.next()) {  // enquanto houver resultados
                Reino r = new Reino();  // cria um novo objeto Reino
                r.setId(getIntSafe(rs, "id", -1));  // seta o id do reino
                r.setNomeReino(getStringSafe(rs, "nomeReino", "nome"));  // seta o nome do reino
                r.setDescricao(getStringSafe(rs, "descricao", "descr"));  // seta a descrição do reino
                lista.add(r);  // adiciona o reino à lista
            }
        }
        return lista;  // retorna a lista de reinos
    }

    // metodo para buscar um reino pelo id
    public Reino buscarPorId(int id) throws Exception {
        String sql = "select * from reino where id = ?";  // consulta para buscar um reino pelo id
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);  // define o id do reino a ser buscado
            try (ResultSet rs = ps.executeQuery()) {  // executa a consulta
                if (rs.next()) {  // se encontrar o reino
                    Reino r = new Reino();  // cria um novo objeto Reino
                    r.setId(getIntSafe(rs, "id", -1));  // seta o id do reino
                    r.setNomeReino(getStringSafe(rs, "nomeReino", "nome"));  // seta o nome do reino
                    r.setDescricao(getStringSafe(rs, "descricao", "descr"));  // seta a descrição do reino
                    return r;  // retorna o reino encontrado
                }
            }
        }
        return null;  // retorna null caso não encontre o reino
    }

    // metodo auxiliar para ler uma string de forma segura do ResultSet
    private String getStringSafe(ResultSet rs, String primary, String fallback) {
        try {
            return rs.getString(primary);  // tenta obter a string usando o nome da coluna primary
        } catch (SQLException e) {
            try {
                return rs.getString(fallback);  // caso não encontre, tenta usar o nome da coluna fallback
            } catch (SQLException ex) {
                return null;  // retorna null se não encontrar nenhuma das colunas
            }
        }
    }

    // metodo auxiliar para ler um inteiro de forma segura do ResultSet
    private int getIntSafe(ResultSet rs, String primary, int fallback) {
        try {
            return rs.getInt(primary);  // tenta obter o inteiro usando o nome da coluna primary
        } catch (SQLException e) {
            return fallback;  // retorna o valor fallback caso haja algum erro
        }
    }
}
