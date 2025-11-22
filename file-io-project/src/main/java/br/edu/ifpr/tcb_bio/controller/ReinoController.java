package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Reino;
import br.edu.ifpr.tcb_bio.modelo.dao.ReinoDAO;

import java.util.ArrayList;

public class ReinoController {

    private ReinoDAO reinoDAO;

    public ReinoController() {
        reinoDAO = new ReinoDAO();
    }

    public void inserir(Reino r) throws Exception {
        reinoDAO.inserir(r);
    }

    public ArrayList<Reino> listar() throws Exception {
        return reinoDAO.listar();
    }

    public Reino buscarPorId(int id) throws Exception {
        return reinoDAO.buscarPorId(id);
    }

    public void atualizar(int id, Reino r) throws Exception {
        reinoDAO.atualizar(id, r);
    }

    public void deletar(int id) throws Exception {
        reinoDAO.deletar(id);
    }
}
