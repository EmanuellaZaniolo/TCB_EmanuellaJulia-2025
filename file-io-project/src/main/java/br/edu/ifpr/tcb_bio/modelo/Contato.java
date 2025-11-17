package br.edu.ifpr.tcb_bio.modelo;

public class Contato {
    private int id;
    private String nome;
    private String celular;
    private String email;
     private Cadastro cadastro;
   
    public Contato(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Cadastro getCadastro() {
        return cadastro;
    }
    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }
    
}
