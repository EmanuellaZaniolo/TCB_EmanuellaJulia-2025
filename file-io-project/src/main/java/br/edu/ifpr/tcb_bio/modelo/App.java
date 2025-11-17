package br.edu.ifpr.tcb_bio.modelo;

import java.util.ArrayList;
public class App {
ArrayList<Cadastro> cadastros = new ArrayList<>();

public Cadastro buscaUsuario(String idCadastro ){
    for (Cadastro cadastro: cadastros) {
       if (cadastro.getIdCadastro()==idCadastro){
          return cadastro;
       }
    }
    return null;
 }

}