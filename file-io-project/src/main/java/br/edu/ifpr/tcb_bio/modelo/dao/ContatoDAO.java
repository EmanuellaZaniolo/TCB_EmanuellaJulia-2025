package br.edu.ifpr.tcb_bio.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;

//data access object
public class ContatoDAO {

    public void salvar(Cadastro cadastro){
        Connection con = ConnectionFactory.getConnection();

        //inserir o endereco primeiro
        String sqlCadastro = "INSERT INTO enderecos (rua,numero,cidade,estado) VALUES(?,?,?,?,?,?)";
        try {//posicoes atributos na tabela 0,1,2 ...
            PreparedStatement psCadastro = 
                      con.prepareStatement(sqlCadastro,Statement.RETURN_GENERATED_KEYS);
            psCadastro.setString(1, cadastro.getNomePessoa());   
            psCadastro.setString(2, cadastro.getNomeUsuario());
            psCadastro.setString(3, cadastro.getEmail());
            psCadastro.setString(4,cadastro.getSenha());

            psCadastro.executeUpdate();

            ResultSet rs = psCadastro.getGeneratedKeys();
            int idEndereco = 0;
            //para o resultSet posicoes atributos na tabela 1,2,3...
            if(rs.next()) idEndereco=rs.getInt(1);//pega o primeiro atributo da tabela

            //inserir cadastro
            String sqlCadastro = 
            "INSERT INTO cadastro(nome,email,id_endereco) VALUES (?,?,?)";
            PreparedStatement psCadastro = con.prepareStatement(sqlCadastro);
            psCadastro.setString(1, cadastro.getNomePessoa());
            psCadastro.setString(2, cadastro.get);
            psCadastro.setString(3, cadastro.getEmail());
            psCadastro.executeUpdate();
            System.out.println("Contato inserido com sucesso!");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
