package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Alternativa;

import br.edu.ifpr.tcb_bio.modelo.dao.AlternativaDAO;


import java.util.ArrayList;

public class AlternativaController {

    private AlternativaDAO alternativaDAO = new AlternativaDAO();

    public ArrayList<Alternativa> listarPorQuestao(int idQuestao) {
        try {
            return alternativaDAO.listarPorQuestao(idQuestao);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    

    public String cadastrar(Alternativa a, int idQuestao) {
        try {
            if (a.getTexto() == null || a.getTexto().trim().isEmpty()) {
                return "O texto da alternativa não pode ficar vazio!";
            }

            alternativaDAO.inserir(a, idQuestao);
            return "Alternativa cadastrada com sucesso!";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar alternativa: " + e.getMessage();
        }
    }
    public ArrayList<Alternativa> listar() {
        try {
            return alternativaDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Alternativa buscar(int id) {
        try {
            return alternativaDAO.buscarPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String atualizar(int id, Alternativa a) {
        try {
            if (a.getTexto() == null || a.getTexto().trim().isEmpty()) {
                return "O texto da alternativa não pode ficar vazio!";
            }

            alternativaDAO.atualizar(id, a);
            return "Alternativa atualizada com sucesso!";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar alternativa: " + e.getMessage();
        }
    }
    public String deletar(int id) {
        try {
            alternativaDAO.deletar(id);
            return "Alternativa removida com sucesso!";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Erro ao deletar alternativa: " + e.getMessage();
        }
    }
}
