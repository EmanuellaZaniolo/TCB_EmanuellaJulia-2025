package br.edu.ifpr.tcb_bio.modelo;

public class PQ {
    private int id_perfil;
    private int id_questao;

    public PQ(int id_perfil, int id_questao) {
        this.id_perfil = id_perfil;
        this.id_questao = id_questao;
    }

    public int getIdPerfil() {
        return id_perfil;
    }

    public int getIdQuestao() {
        return id_questao;
    }
}
