package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.PQ;
import br.edu.ifpr.tcb_bio.modelo.dao.PQ_DAO;
public class PQ_controller{


private PQ_DAO pqDao = new PQ_DAO();


 // salva que um perfil respondeu uma questao
 public void registrarResposta(int id_perfil, int id_questao) {
    try {
        PQ pq = new  PQ(id_perfil, id_questao);
        pqDao.salvar(pq);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}