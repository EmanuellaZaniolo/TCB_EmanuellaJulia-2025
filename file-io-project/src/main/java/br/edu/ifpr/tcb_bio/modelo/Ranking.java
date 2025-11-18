package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;

public class Ranking {

    private ArrayList<Perfil> perfis;

    public Ranking() {
        perfis = new ArrayList<>();
    }

    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }

    // Adiciona um perfil no ranking
    public void adicionarPerfil(Perfil perfil) {
        perfis.add(perfil);
    }

    // Retorna o perfil com mais acertos (simples)
    public Perfil getMelhorPerfil() {
        if (perfis.isEmpty()) {
            return null;
        }

        Perfil melhor = perfis.get(0);
        for (Perfil p : perfis) {
            if (p.getTotalAcertos() > melhor.getTotalAcertos()) {
                melhor = p;
            }
        }
        return melhor;
    }
}
