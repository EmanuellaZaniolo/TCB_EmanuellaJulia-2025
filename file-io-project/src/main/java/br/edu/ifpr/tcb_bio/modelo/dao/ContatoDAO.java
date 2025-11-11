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
        String sqlEndereco = "INSERT INTO enderecos (rua,numero,cidade,estado) VALUES(?,?,?,?,?)";
        try {//posicoes atributos na tabela 0,1,2 ...
            PreparedStatement psEndereco = 
                      con.prepareStatement(sqlEndereco,Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, contato.getCadastro().getNomePessoa());   
            psEndereco.setString(2, contato.getCadastro().getNomeUsuario());
            psEndereco.setString(3, contato.getCadastro().getEmail());
            psEndereco.setString(4,contato.getCadastro().getSenha());
            psEndereco.setString(5,contato.getCadastro().getIdCadastro());

            psEndereco.executeUpdate();

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            //para o resultSet posicoes atributos na tabela 1,2,3...
            if(rs.next()) idEndereco=rs.getInt(1);//pega o primeiro atributo da tabela

            //inserir contato
            String sqlContato = 
            "INSERT INTO contatos(nome,celular,email,id_endereco) VALUES (?,?,?,?)";
            PreparedStatement psContato = con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.setInt(4, idEndereco);
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso!");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
