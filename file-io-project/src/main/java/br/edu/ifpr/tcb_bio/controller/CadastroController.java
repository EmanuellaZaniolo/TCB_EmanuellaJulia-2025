package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;

public class CadastroController {

    private CadastroDAO cadastroDAO = new CadastroDAO();

    public String cadastrar(Cadastro c) {
        try {
            if (c.getNomePessoa() == null || c.getNomePessoa().isEmpty() ||
                c.getNomeUsuario() == null || c.getNomeUsuario().isEmpty() ||
                c.getSenha() == null || c.getSenha().isEmpty()) {
                return "Preencha todos os campos!";
            }
            Cadastro existente = cadastroDAO.buscarPorUsuario(c.getNomeUsuario());
            if (existente != null) return "Usuário já existente!";

            int idGerado = cadastroDAO.inserir(c);
            if (idGerado > 0) return "Cadastro realizado com sucesso!";
            else return "Erro ao cadastrar";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar: " + e.getMessage();
        }
    }

    public Cadastro login(String usuario, String senha) {
        try {
            Cadastro c = cadastroDAO.buscarPorUsuario(usuario);
            if (c == null) return null;
            if (!c.getSenha().equals(senha)) return null;
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
