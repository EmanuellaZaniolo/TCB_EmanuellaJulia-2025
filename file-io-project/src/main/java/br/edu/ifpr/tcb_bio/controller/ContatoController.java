package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Contato;
import br.edu.ifpr.tcb_bio.modelo.dao.ContatoDAO;


public class ContatoController{
    private ContatoDAO dao;

     public ContatoController() {
        this.dao = new ContatoDAO();
    }

    public void cadastrarContato(Contato contato){
        if(contato.getNome() == null  || contato.getNome().isEmpty()){
            System.out.println("Nome não pode ser vazio");
            return;
        }

        dao.salvar(contato);
        
    }


}