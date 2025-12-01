package br.edu.ifpr.tcb_bio.modelo;



public class Reino {

    private String nomeReino;
    private String descricao;
  
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getNomeReino() {
        return nomeReino;
    }

    public void setNomeReino(String nomeReino) {
        this.nomeReino = nomeReino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nomeReino;
    }
}
