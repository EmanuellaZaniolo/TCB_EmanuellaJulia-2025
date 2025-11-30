package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Reino;
import br.edu.ifpr.tcb_bio.modelo.dao.ReinoDAO;

import java.util.ArrayList;

public class ReinoController {

    private ReinoDAO reinoDAO;

    public ReinoController() {
        this.reinoDAO = new ReinoDAO();
    }

    public ArrayList<Reino> listar() throws Exception {
        return reinoDAO.listar();
    }

    public Reino buscarPorId(int id) throws Exception {
        return reinoDAO.buscarPorId(id);
    }
}
