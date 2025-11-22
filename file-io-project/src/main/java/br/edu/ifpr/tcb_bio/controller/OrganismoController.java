package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Organismo;
import br.edu.ifpr.tcb_bio.modelo.dao.OrganismoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrganismoController {

    private OrganismoDAO organismoDAO;

    public OrganismoController() {
        this.organismoDAO = new OrganismoDAO();
    }

    public void inserir(Organismo organismo) {
        try {
            organismoDAO.inserir(organismo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Organismo> listar() {
        try {
            return organismoDAO.listar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Organismo buscarPorId(int id) {
        try {
            return organismoDAO.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Organismo organismo) {
        try {
            organismoDAO.atualizar(organismo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try {
            organismoDAO.deletar(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
