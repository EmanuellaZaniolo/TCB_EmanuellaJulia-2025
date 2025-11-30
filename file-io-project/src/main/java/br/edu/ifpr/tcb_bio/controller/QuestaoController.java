package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Questao;
import br.edu.ifpr.tcb_bio.modelo.dao.QuestaoDAO;

import java.util.ArrayList;

public class QuestaoController {

    private QuestaoDAO questaoDAO;

    public QuestaoController() {
        this.questaoDAO = new QuestaoDAO();
    }

    public int inserir(Questao questao, int idReino) {
        try {
            return questaoDAO.inserir(questao, idReino);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Questao> buscarPorReino(int idReino) {
        try {
            return questaoDAO.buscarPorReino(idReino);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Questao> listar() {
        try {
            return questaoDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Questao buscarPorId(int id) {
        try {
            return questaoDAO.buscarPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizar(int id, Questao questao) {
        try {
            questaoDAO.atualizar(id, questao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try {
            questaoDAO.deletar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
