package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.dao.PerfilDAO;

import java.util.ArrayList;

public class PerfilController {

    private PerfilDAO perfilDAO;

    public PerfilController() {
        this.perfilDAO = new PerfilDAO();
    }

    public void inserir(Perfil perfil, int idCadastro) {
        try {
            perfilDAO.inserir(perfil, idCadastro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Perfil> listar() {
        try {
            return perfilDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Perfil buscarPorId(int id) {
        try {
            return perfilDAO.buscarPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(int id, Perfil perfil) {
        try {
            perfilDAO.atualizar(id, perfil);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try {
            perfilDAO.deletar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
