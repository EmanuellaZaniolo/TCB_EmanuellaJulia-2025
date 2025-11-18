package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;

public class Questao {

    private String enunciado;
    private ArrayList<Alternativa> alternativas;
    private Reino reino;

    public Questao() {
        alternativas = new ArrayList<>();
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public ArrayList<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Reino getReino() {
        return reino;
    }

    public void setReino(Reino reino) {
        this.reino = reino;
    }

    public void adicionarAlternativa(Alternativa alt) {
        alternativas.add(alt);
    }
}
