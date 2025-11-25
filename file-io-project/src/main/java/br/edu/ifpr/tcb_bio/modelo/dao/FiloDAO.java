package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;
import br.edu.ifpr.tcb_bio.modelo.Filo;

import java.sql.*;
import java.util.ArrayList;

public class FiloDAO {
    //insere o nome e descrição de um filo com o id
    public void inserir(Filo f, int idReino) throws Exception {
        String sql = "INSERT INTO filo(idReino, nome, descricao) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReino);
            ps.setString(2, f.getNomeFilo());
            ps.setString(3, f.getDescricao());
            ps.executeUpdate();
        }
    }
//apenas lista os nomes e descrições ja inseridas
    public ArrayList<Filo> listar() throws Exception {
        ArrayList<Filo> lista = new ArrayList<>();
        String sql = "SELECT * FROM filo";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Filo f = new Filo();
                f.setNomeFilo(rs.getString("nome"));
                f.setDescricao(rs.getString("descricao"));
                lista.add(f);
            }
        }
        return lista;
    }
//busca usando o id e retorna nome e descrição
    public Filo buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM filo WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Filo f = new Filo();
                f.setNomeFilo(rs.getString("nome"));
                f.setDescricao(rs.getString("descricao"));
                return f;
            }
        }
        return null;
    }
//atualiza o nome ou descrição de um filo usando o id pra buscar 
    public void atualizar(int id, Filo f) throws Exception {
        String sql = "UPDATE filo SET nome=?, descricao=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getNomeFilo());
            ps.setString(2, f.getDescricao());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }
// deleta o filo usando o id pra buscar 
    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM filo WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
