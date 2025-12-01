package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;

public class CadastroController {

    private CadastroDAO cadastroDAO = new CadastroDAO();

    // metodo para cadastrar um novo usuario
    public String cadastrar(Cadastro c) {
        try {
            // verifica se os campos obrigatorios estao preenchidos
            if (c.getNomePessoa() == null || c.getNomePessoa().isEmpty() ||
                c.getNomeUsuario() == null || c.getNomeUsuario().isEmpty() ||
                c.getSenha() == null || c.getSenha().isEmpty()) {
                return "preencha todos os campos!";  // retorna mensagem de erro se algum campo estiver vazio
            }

            // verifica se o usuario ja existe
            Cadastro existente = cadastroDAO.buscarPorUsuario(c.getNomeUsuario());
            if (existente != null) return "usuario ja existente!";  // retorna mensagem de erro se o usuario ja estiver cadastrado

            // tenta inserir o novo cadastro no banco
            int idGerado = cadastroDAO.inserir(c);
            if (idGerado > 0) return "cadastro realizado com sucesso!";  // sucesso no cadastro
            else return "erro ao cadastrar";  // falha no cadastro
        } catch (Exception e) {
            e.printStackTrace();
            return "erro ao cadastrar: " + e.getMessage();  // caso ocorra algum erro, retorna a mensagem de erro
        }
    }

    // metodo para fazer login
    public Cadastro login(String usuario, String senha) {
        try {
            // tenta buscar o cadastro com o nome de usuario fornecido
            Cadastro c = cadastroDAO.buscarPorUsuario(usuario);
            if (c == null) return null;  // se nao encontrar o usuario, retorna null
            if (!c.getSenha().equals(senha)) return null;  // se a senha nao for correta, retorna null
            return c;  // se login for bem-sucedido, retorna o objeto Cadastro do usuario
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // caso ocorra algum erro, retorna null
        }
    }
}
