package br.edu.ifpr.tcb_bio.modelo;

public class Organismo {

    private String nomeOrganismo;
    private String descricao;
    private Classe classe;
    private Filo filo;
    private Reino reino;

    public Organismo() {

    }

    public String getNomeOrganismo() {
        return nomeOrganismo;
    }

    public void setNomeOrganismo(String nomeOrganismo) {
        this.nomeOrganismo = nomeOrganismo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Filo getFilo() {
        return filo;
    }

    public void setFilo(Filo filo) {
        this.filo = filo;
    }

    public Reino getReino() {
        return reino;
    }

    public void setReino(Reino reino) {
        this.reino = reino;
    }
}
