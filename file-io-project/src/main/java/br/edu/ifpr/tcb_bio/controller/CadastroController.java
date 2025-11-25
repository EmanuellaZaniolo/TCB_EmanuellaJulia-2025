package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;

public class CadastroController {

    private CadastroDAO cadastroDAO = new CadastroDAO();

    public String cadastrar(Cadastro c) {
        String respostaAoUser=null;
        try {
            if (c.getNomePessoa().isEmpty() ||
                c.getNomeUsuario().isEmpty() ||
                c.getSenha().isEmpty()) {
                    respostaAoUser="Preencha todos os campos!";
                return respostaAoUser;
            }

            if (c.getTipoUsuario() == null || c.getTipoUsuario().isEmpty()) {
                respostaAoUser="Selecione o tipo do usuário!";
                return respostaAoUser;
            }

            Cadastro existente = cadastroDAO.buscarPorUsuario(c.getNomeUsuario());
            if (existente != null) {
                respostaAoUser="Usuário já existente!";
                return respostaAoUser;
            }

            cadastroDAO.inserir(c);
            respostaAoUser="Cadastro realizado com sucesso!!!";
            return respostaAoUser;
        }
        catch (Exception e) {
            e.printStackTrace();
            respostaAoUser="Erro ao cadastrar: " + e.getMessage();
            return respostaAoUser;
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
