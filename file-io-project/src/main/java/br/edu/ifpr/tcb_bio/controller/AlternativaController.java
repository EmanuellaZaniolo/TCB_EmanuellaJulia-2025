package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.dao.AlternativaDAO;

import java.util.ArrayList;

public class AlternativaController {

    private AlternativaDAO alternativaDAO;

    public AlternativaController() {
        this.alternativaDAO = new AlternativaDAO();
    }

    public int cadastrar(Alternativa a, int idQuestao) {
        try {
            return alternativaDAO.inserir(a, idQuestao);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Alternativa> listarPorQuestao(int idQuestao) {
        try {
            return alternativaDAO.listarPorQuestao(idQuestao);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void atualizar(int id, Alternativa a) {
        try {
            alternativaDAO.atualizar(id, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try {
            alternativaDAO.deletar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
