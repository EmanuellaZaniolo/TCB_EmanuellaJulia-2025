package br.edu.ifpr.tcb_bio.modelo;

public class Perfil {

    private int id; // novo campo
    private Cadastro cadastro;
    private int totalAcertos;

    public Perfil() {
    }

    public Perfil(Cadastro cadastro) {
        this.cadastro = cadastro;
        this.totalAcertos = 0;
    }

    public Perfil(int id, Cadastro cadastro, int totalAcertos) {
        this.id = id;
        this.cadastro = cadastro;
        this.totalAcertos = totalAcertos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public void adicionarAcerto() {
        totalAcertos++;
    }
}
