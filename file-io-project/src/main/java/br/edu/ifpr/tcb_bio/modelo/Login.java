package br.edu.ifpr.tcb_bio.modelo;

public class Login {
    private String senha;
    private String nomeUsuario;
    private int idLogin;
    private Cadastro cadastro;// para verificar e a senha donlogin e do cadastroi são iguais para asssim logar


    public Login(){

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
