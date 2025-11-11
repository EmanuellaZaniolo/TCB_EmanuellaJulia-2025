package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;
public class App {
ArrayList<Cadatro> cadastros = new ArrayList<>();

public Cadatro buscaUsuario(String idCadastro ){
    for (Cadatro cadastro: cadastros) {
       if (cadastro.getIdCadastro()==idCadastro){
          return cadastro;
       }
    }
    return null;
 }

}