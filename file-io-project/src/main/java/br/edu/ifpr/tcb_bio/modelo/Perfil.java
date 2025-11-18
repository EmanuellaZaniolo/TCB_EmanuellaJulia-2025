package br.edu.ifpr.tcb_bio.modelo;

public class Perfil {

    private Cadastro cadastro;
    private int totalAcertos;

    public Perfil() {

    }

    public Perfil(Cadastro cadastro) {
        this.cadastro = cadastro;
        this.totalAcertos = 0;
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

    // Incrementa o total de acertos
    public void adicionarAcerto() {
        totalAcertos++;
    }
}
