package br.edu.ifpr.tcb_bio.modelo;

public class PQ {
    private int idPerfil;
    private int idQuestao;

    public PQ(int idPerfil, int idQuestao) {
        this.idPerfil = idPerfil;
        this.idQuestao = idQuestao;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public int getIdQuestao() {
        return idQuestao;
    }
}
