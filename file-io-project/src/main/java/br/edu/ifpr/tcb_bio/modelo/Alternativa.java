package br.edu.ifpr.tcb_bio.modelo;

public class Alternativa {

    private String texto;
    private boolean correta;
    private int id;
    private int idQuestao;

    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alternativa() {

    }

    public Alternativa(String texto, boolean correta) {
        this.texto = texto;
        this.correta = correta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
}
