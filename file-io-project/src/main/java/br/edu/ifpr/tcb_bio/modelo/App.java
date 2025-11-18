package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;

public class App {

    private ArrayList<Cadastro> cadastros;

    public App() {
        cadastros = new ArrayList<>();
    }

    // Cadastrar novo usuário
    public void cadastrarUsuario(Cadastro cadastro) {
        cadastros.add(cadastro);
    }

    // Buscar um usuário pelo nome de usuário
    public Cadastro buscarUsuario(String nomeUsuario) {

        for (Cadastro cadastro : cadastros) {
            if (cadastro.getNomeUsuario().equals(nomeUsuario)) {
                return cadastro;
            }
        }
        return null;
    }

    // Fazer login (compara usuário e senha)
    public boolean fazerLogin(String nomeUsuario, String senha) {

        for (Cadastro cadastro : cadastros) {
            if (cadastro.getNomeUsuario().equals(nomeUsuario) &&
                cadastro.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // Retorna a lista completa de usuários (para Ranking)
    public ArrayList<Cadastro> getCadastros() {
        return cadastros;
    }
}
