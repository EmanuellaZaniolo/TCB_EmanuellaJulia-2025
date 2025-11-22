package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Filo;
import br.edu.ifpr.tcb_bio.modelo.dao.FiloDAO;

import java.util.ArrayList;

public class FiloController {

    private FiloDAO filoDAO;

    public FiloController() {
        this.filoDAO = new FiloDAO();
    }
    public ArrayList<Filo> listarFilos() {
        try {
            return filoDAO.listar();
        } catch (Exception e) {
            System.out.println("Erro ao listar filos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public Filo buscarFiloPorId(int id) {
        try {
            return filoDAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar filo: " + e.getMessage());
            return null;
        }
    }
    public void inserirFilo(Filo f, int idReino) {
        try {
            filoDAO.inserir(f, idReino);
        } catch (Exception e) {
            System.out.println("Erro ao inserir filo: " + e.getMessage());
        }
    }
    public void atualizarFilo(int id, Filo novoFilo) {
        try {
            filoDAO.atualizar(id, novoFilo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar filo: " + e.getMessage());
        }
    }
    public void deletarFilo(int id) {
        try {
            filoDAO.deletar(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar filo: " + e.getMessage());
        }
    }

}
