package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Questao;
import br.edu.ifpr.tcb_bio.modelo.dao.QuestaoDAO;

import java.util.ArrayList;

public class QuestaoController {

    private QuestaoDAO questaoDAO;  // Instância de QuestaoDAO, responsável por realizar operações no banco de dados

    // Construtor da classe, que inicializa a instância do QuestaoDAO
    public QuestaoController() {
        this.questaoDAO = new QuestaoDAO();
    }

    // Método para inserir uma nova questão no banco de dados
    public int inserir(Questao questao, int idReino) {
        try {
            return questaoDAO.inserir(questao, idReino);  // Chama o método de inserção no DAO e retorna o ID gerado
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
            return -1;  // Retorna -1 em caso de erro
        }
    }

    // Método para buscar questões relacionadas a um determinado Reino
    public ArrayList<Questao> buscarPorReino(int idReino) {
        try {
            return questaoDAO.buscarPorReino(idReino);  // Chama o método de busca no DAO e retorna a lista de questões
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
            return new ArrayList<>();  // Retorna uma lista vazia caso ocorra um erro
        }
    }

    // Método para listar todas as questões cadastradas no banco de dados
    public ArrayList<Questao> listar() {
        try {
            return questaoDAO.listar();  // Chama o método de listagem no DAO e retorna a lista de todas as questões
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
            return new ArrayList<>();  // Retorna uma lista vazia caso ocorra um erro
        }
    }

    // Método para buscar uma questão pelo seu ID
    public Questao buscarPorId(int id) {
        try {
            return questaoDAO.buscarPorId(id);  // Chama o método de busca por ID no DAO e retorna a questão encontrada
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
            return null;  // Retorna null caso não encontre a questão ou ocorra um erro
        }
    }

    // Método para atualizar os dados de uma questão no banco de dados
    public void atualizar(int id, Questao questao) {
        try {
            questaoDAO.atualizar(id, questao);  // Chama o método de atualização no DAO para atualizar a questão
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
        }
    }

    // Método para deletar uma questão do banco de dados
    public void deletar(int id) {
        try {
            questaoDAO.deletar(id);  // Chama o método de exclusão no DAO para deletar a questão
        } catch (Exception e) {
            e.printStackTrace();  // Se ocorrer um erro, imprime a stack trace para identificar o problema
        }
    }
}
