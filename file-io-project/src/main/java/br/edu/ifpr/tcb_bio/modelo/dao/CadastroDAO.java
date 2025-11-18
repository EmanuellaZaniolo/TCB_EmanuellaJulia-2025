package br.edu.ifpr.tcb_bio.modelo.dao;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class CadastroDAO {

    public void inserir(Cadastro c) throws Exception {
        String sql = "INSERT INTO cadastro(nome, usuario, senha) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNomePessoa());
            ps.setString(2, c.getNomeUsuario());
            ps.setString(3, c.getSenha());
            ps.executeUpdate();
        }
    }

    public ArrayList<Cadastro> listar() throws Exception {
        ArrayList<Cadastro> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cadastro c = new Cadastro();
                c.setNomePessoa(rs.getString("nome"));
                c.setNomeUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                lista.add(c);
            }
        }
        return lista;
    }

    public Cadastro buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM cadastro WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cadastro c = new Cadastro();
                c.setNomePessoa(rs.getString("nome"));
                c.setNomeUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                return c;
            }
        }
        return null;
    }

    public void atualizar(int id, Cadastro c) throws Exception {
        String sql = "UPDATE cadastro SET nome=?, usuario=?, senha=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNomePessoa());
            ps.setString(2, c.getNomeUsuario());
            ps.setString(3, c.getSenha());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM cadastro WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
