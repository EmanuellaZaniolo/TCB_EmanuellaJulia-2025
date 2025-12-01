package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AlternativaDAO {

    // metodo para inserir uma nova alternativa no banco de dados
    public int inserir(Alternativa a, int idQuestao) throws Exception {
        String insertSql = "insert into alternativa(idQuestao, texto, correta) values (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);  // desliga commit automatico para controlar transacao
            try {
                if (a.isCorreta()) {  // se a alternativa for correta
                    // marca todas as outras alternativas da mesma questao como falsas
                    try (PreparedStatement psFalse = con.prepareStatement(
                            "update alternativa set correta = false where idQuestao = ?")) {
                        psFalse.setInt(1, idQuestao);
                        psFalse.executeUpdate();
                    }
                }

                // insere a alternativa no banco de dados
                try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, idQuestao);
                    ps.setString(2, a.getTexto());
                    ps.setBoolean(3, a.isCorreta());
                    ps.executeUpdate();

                    try (ResultSet keys = ps.getGeneratedKeys()) {  // pega id gerado automaticamente
                        int idGerado = -1;
                        if (keys.next()) {
                            idGerado = keys.getInt(1);  // obtem id gerado
                        }
                        con.commit();  // confirma transacao
                        return idGerado;  // retorna id da alternativa inserida
                    }
                }
            } catch (Exception e) {
                con.rollback();  // desfaz transacao em caso de erro
                throw e;
            } finally {
                con.setAutoCommit(true);  // liga novamente commit automatico
            }
        }
    }

    // metodo para listar todas as alternativas de uma questao
    public ArrayList<Alternativa> listarPorQuestao(int idQuestao) throws Exception {
        ArrayList<Alternativa> lista = new ArrayList<>();
        String sql = "select * from alternativa where idQuestao = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idQuestao);  // define o id da questao na consulta
            try (ResultSet rs = ps.executeQuery()) {  // executa consulta
                while (rs.next()) {  // percorre cada resultado
                    Alternativa a = new Alternativa();  // cria novo objeto alternativa
                    a.setId(rs.getInt("id"));  // seta id
                    a.setTexto(rs.getString("texto"));  // seta texto da alternativa
                    a.setCorreta(rs.getBoolean("correta"));  // seta se eh correta ou nao
                    a.setIdQuestao(rs.getInt("idQuestao"));  // seta id da questao
                    lista.add(a);  // adiciona alternativa na lista
                }
            }
        }
        return lista;  // retorna lista de alternativas
    }

    // metodo para atualizar os dados de uma alternativa
    public void atualizar(int id, Alternativa a) throws Exception {
        String updateSql = "update alternativa set texto=?, correta=? where id=?";
        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);  // desliga commit automatico
            try {
                if (a.isCorreta()) {  // se a alternativa for correta
                    int idQuestao = a.getIdQuestao();  // pega id da questao
                    // marca todas as outras alternativas como falsas
                    try (PreparedStatement psFalse = con.prepareStatement(
                            "update alternativa set correta = false where idQuestao = ?")) {
                        psFalse.setInt(1, idQuestao);
                        psFalse.executeUpdate();
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                    ps.setString(1, a.getTexto());  // seta novo texto
                    ps.setBoolean(2, a.isCorreta());  // seta se e correta ou nao
                    ps.setInt(3, id);  // seta id da alternativa a ser atualizada
                    ps.executeUpdate();  // executa atualizacao
                }
                con.commit();  // confirma transacao
            } catch (Exception e) {
                con.rollback();  // desfaz transacao em caso de erro
                throw e;
            } finally {
                con.setAutoCommit(true);  // liga novamente commit automatico
            }
        }
    }

    // metodo para deletar uma alternativa pelo id
    public void deletar(int id) throws Exception {
        String sql = "delete from alternativa where id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);  // define id da alternativa a ser deletada
            ps.executeUpdate();  // executa exclusao
        }
    }
}
