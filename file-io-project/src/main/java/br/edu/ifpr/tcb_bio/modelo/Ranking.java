package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;
public class Ranking {
    private ArrayList<Cadatro> usuarios = new ArrayList<>();
    private ArrayList<Integer> pontuacao =  new ArrayList<>();

    public Ranking() {
    }

    public ArrayList<Cadatro> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Cadatro> usuarios) {
        this.usuarios = usuarios;
    }
    public ArrayList<Integer> getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(ArrayList<Integer> pontuacao) {
        this.pontuacao = pontuacao;
    }
    

}
