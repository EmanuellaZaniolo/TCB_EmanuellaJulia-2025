package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AlternativaDAO {
//insere as alternativas para uma questao, falando se é true ou false para "isCorreta"
    public void inserir(Alternativa a, int idQuestao) throws Exception {
        String sql = "INSERT INTO alternativa(idQuestao, texto, correta) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idQuestao);
            ps.setString(2, a.getTexto());
            ps.setBoolean(3, a.isCorreta());
            ps.executeUpdate();
        }
    }
    public ArrayList<Alternativa> listarPorQuestao(int idQuestao) throws Exception {
        ArrayList<Alternativa> lista = new ArrayList<>();
        String sql = "SELECT * FROM alternativa WHERE idQuestao = ?";
    
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
    
            ps.setInt(1, idQuestao);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Alternativa a = new Alternativa();
                a.setId(rs.getInt("id"));
                a.setTexto(rs.getString("texto"));
                a.setCorreta(rs.getBoolean("correta"));
                lista.add(a);
            }
        }
        return lista;
    }
    
// apenas lsista as alternativas falando se é correta ou não
    public ArrayList<Alternativa> listar() throws Exception {
        ArrayList<Alternativa> lista = new ArrayList<>();
        String sql = "SELECT * FROM alternativa";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alternativa a = new Alternativa();
                a.setTexto(rs.getString("texto"));
                a.setCorreta(rs.getBoolean("correta"));
                lista.add(a);
            }
        }
        return lista;
    }
//proucura uma alternativa usando o id atribuido a ela
    public Alternativa buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM alternativa WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Alternativa a = new Alternativa();
                a.setTexto(rs.getString("texto"));
                a.setCorreta(rs.getBoolean("correta"));
                return a;
            }
        }
        return null;
    }
// troca o texto da alternativa usando o id para buscar 
    public void atualizar(int id, Alternativa a) throws Exception {
        String sql = "UPDATE alternativa SET texto=?, correta=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getTexto());
            ps.setBoolean(2, a.isCorreta());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }
// deleta usando o id pra buscar
    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM alternativa WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            //aqui ele atualiza 
            ps.executeUpdate();
        }
    }
}
