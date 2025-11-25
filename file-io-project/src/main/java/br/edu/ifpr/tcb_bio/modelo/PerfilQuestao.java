
package br.edu.ifpr.tcb_bio.modelo;


public class PerfilQuestao {
    private int id_perfil;
    private int id_questao;
    private int id_alternativa;

    public PerfilQuestao(){

    }

    public int getId_perfil() {
        return id_perfil;
    }
    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
    public int getId_questao() {
        return id_questao;
    }
    public void setId_questao(int id_questao) {
        this.id_questao = id_questao;
    }
    public int getId_alternativa() {
        return id_alternativa;
    }
    public void setId_alternativa(int id_alternativa) {
        this.id_alternativa = id_alternativa;
    }
}
