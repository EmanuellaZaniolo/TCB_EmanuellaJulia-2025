package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Classe;
import br.edu.ifpr.tcb_bio.modelo.dao.ClasseDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClasseController {

    private ClasseDAO classeDAO;

    public ClasseController() {
        this.classeDAO = new ClasseDAO();
    }

    // Lista todas as classes
    public ArrayList<Classe> listarClasses() {
        try {
            return classeDAO.listar();
        } catch (SQLException e) {
            System.out.println("Erro ao listar classes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Busca uma classe pelo ID
    public Classe buscarClassePorId(int id) {
        try {
            return classeDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar classe: " + e.getMessage());
            return null;
        }
    }

    // Inserir - usado apenas pelo programador/professor
    public void inserirClasse(Classe c) {
        try {
            classeDAO.inserir(c);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir classe: " + e.getMessage());
        }
    }

    // Atualizar - também apenas pelo professor
    public void atualizarClasse(Classe c) {
        try {
            classeDAO.atualizar(c);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar classe: " + e.getMessage());
        }
    }

    // Deletar classe - só o professor
    public void deletarClasse(int id) {
        try {
            classeDAO.deletar(id);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar classe: " + e.getMessage());
        }
    }
}

