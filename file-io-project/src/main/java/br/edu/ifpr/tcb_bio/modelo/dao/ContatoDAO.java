package br.edu.ifpr.tcb_bio.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpr.tcb_bio.modelo.Contato;

//data access object
public class ContatoDAO {

    public void salvar(Contato contato){
        Connection con = ConnectionFactory.getConnection();

        //inserir o endereco primeiro
        String sqlCadastro = "INSERT INTO enderecos (rua,numero,cidade,estado) VALUES(?,?,?,?,?,?)";
        try {//posicoes atributos na tabela 0,1,2 ...
            PreparedStatement psCadastro = 
                      con.prepareStatement(sqlCadastro,Statement.RETURN_GENERATED_KEYS);
            psCadastro.setString(1, contato.getCadastro().getNomePessoa());   
            psCadastro.setString(2, contato.getCadastro().getNomeUsuario());
            psCadastro.setString(3, contato.getCadastro().getEmail());
            psCadastro.setString(4,contato.getCadastro().getSenha());
            psCadastro.setString(5,contato.getCadastro().getIdCadastro());

            psCadastro.executeUpdate();

            ResultSet rs = psCadastro.getGeneratedKeys();
            int idEndereco = 0;
            //para o resultSet posicoes atributos na tabela 1,2,3...
            if(rs.next()) idEndereco=rs.getInt(1);//pega o primeiro atributo da tabela

            //inserir contato
            String sqlContato = 
            "INSERT INTO contatos(nome,celular,email,id_endereco) VALUES (?,?,?)";
            PreparedStatement psContato = con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso!");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
