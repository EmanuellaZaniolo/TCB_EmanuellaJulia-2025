package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;

public class Reino {

    private String nomeReino;
    private String descricao;
    private ArrayList<Filo> filos;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reino() {
        filos = new ArrayList<>();
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

    public ArrayList<Filo> getFilos() {
        return filos;
    }

    public void setFilos(ArrayList<Filo> filos) {
        this.filos = filos;
    }

    // Adiciona um filo à lista
    public void adicionarFilo(Filo filo) {
        filos.add(filo);
    }
}
