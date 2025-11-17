package br.edu.ifpr.tcb_bio.modelo;

public class Cadastro {
    private String nomePessoa;
    private String senha;
    private String email;
    private String nomeUsuario;
    private String idCadastro;
    
    public Cadastro(){
        
    } 
    public String getIdCadastro() {
        return idCadastro;
    }
    public void setIdCadastro(String idCadastro) {
        this.idCadastro = idCadastro;
    }
    public String getNomePessoa() {
        return nomePessoa;
    }
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
}
