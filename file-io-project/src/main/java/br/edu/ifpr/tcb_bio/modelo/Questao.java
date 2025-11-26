package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;

public class Questao {

    private String enunciado;
    private ArrayList<Alternativa> alternativas;
    private int idReino;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

  

    public int getIdReino() {
        return idReino;
    }
    
    public void setIdReino(int idReino) {
        this.idReino = idReino;
    }
    

    public void adicionarAlternativa(Alternativa alt) {
        alternativas.add(alt);
    }
}
