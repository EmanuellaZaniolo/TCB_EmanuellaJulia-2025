package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Ranking;
import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.dao.RankingDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RankingController {

    private RankingDAO rankingDAO;

    public RankingController() {
        rankingDAO = new RankingDAO();
    }

    // Retorna o ranking completo já ordenado (DAO faz a ordenação)
    public Ranking listarRanking() throws SQLException {

        // Pega a lista pronta do DAO
        ArrayList<Perfil> listaPerfis = rankingDAO.listarRanking();

        // Cria objeto Ranking e coloca a lista dentro
        Ranking ranking = new Ranking();
        ranking.setPerfis(listaPerfis);

        return ranking;
    }
}
