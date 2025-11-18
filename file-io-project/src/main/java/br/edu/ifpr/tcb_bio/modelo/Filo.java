package br.edu.ifpr.tcb_bio.modelo;

public class Filo {
  
    private String nomeFilo;
    private String descricao;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Filo() {

    }

    public String getNomeFilo() {
        return nomeFilo;
    }

    public void setNomeFilo(String nomeFilo) {
        this.nomeFilo = nomeFilo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
