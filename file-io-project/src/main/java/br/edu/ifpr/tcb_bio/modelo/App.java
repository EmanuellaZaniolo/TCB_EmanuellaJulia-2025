package br.edu.ifpr.tcb_bio.modelo;

import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;

public class App {

    private CadastroDAO cadastroDAO = new CadastroDAO();

    public void cadastrarUsuario(Cadastro c) {
        try {
            cadastroDAO.inserir(c); // salva no banco
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cadastro buscarUsuario(String usuario) {
        try {
            return cadastroDAO.buscarPorUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean fazerLogin(String usuario, String senha) {
        try {
            Cadastro c = cadastroDAO.buscarPorUsuario(usuario);

            if (c != null && c.getSenha().equals(senha)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
