package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;
import br.edu.ifpr.tcb_bio.modelo.dao.PerfilDAO;

public class CadastroController {

    private CadastroDAO cadastroDAO = new CadastroDAO();
    private PerfilDAO perfilDAO = new PerfilDAO();

    // metodo para cadastrar um novo usuario
    public String cadastrar(Cadastro c) {
        try {
            // verifica se os campos obrigatorios estao preenchidos
            if (c.getNomePessoa() == null || c.getNomePessoa().isEmpty() ||
                c.getNomeUsuario() == null || c.getNomeUsuario().isEmpty() ||
                c.getSenha() == null || c.getSenha().isEmpty()) {
                return "preencha todos os campos!";
            }

            // verifica se o usuario ja existe
            Cadastro existente = cadastroDAO.buscarPorUsuario(c.getNomeUsuario());
            if (existente != null) return "usuario ja existente!";

            // insere o cadastro
            int idGerado = cadastroDAO.inserir(c);

            if (idGerado > 0) {

                // ----------------- CRIA PERFIL AUTOMATICAMENTE -----------------
                Perfil p = new Perfil();
                p.setTotalAcertos(0);   // valor inicial

                // vincula o perfil ao cadastro criado
                perfilDAO.inserir(p, idGerado);

                return "cadastro realizado com sucesso!";
            } 
            else return "erro ao cadastrar";

        } catch (Exception e) {
            e.printStackTrace();
            return "erro ao cadastrar: " + e.getMessage();
        }
    }

    // metodo para fazer login
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
