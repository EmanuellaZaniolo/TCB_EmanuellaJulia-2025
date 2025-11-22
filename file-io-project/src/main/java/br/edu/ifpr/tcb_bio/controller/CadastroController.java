package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;

public class CadastroController {

    private CadastroDAO cadastroDAO = new CadastroDAO();

    public String cadastrar(Cadastro c) {
        try {
            if (c.getNomePessoa().isEmpty() ||
                c.getNomeUsuario().isEmpty() ||
                c.getSenha().isEmpty()) {

                return "Preencha todos os campos!";
            }

            if (c.getTipoUsuario() == null || c.getTipoUsuario().isEmpty()) {
                return "Selecione o tipo do usuário!";
            }

            Cadastro existente = cadastroDAO.buscarPorUsuario(c.getNomeUsuario());
            if (existente != null) {
                return "Usuário já existe!";
            }

            cadastroDAO.inserir(c);
            return "Cadastro realizado com sucesso!";
        }
        catch (Exception e) {
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
