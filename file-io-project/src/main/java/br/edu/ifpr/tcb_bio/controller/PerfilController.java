package br.edu.ifpr.tcb_bio.controller;

import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.dao.PerfilDAO;

import java.util.ArrayList;

public class PerfilController {

    private PerfilDAO perfilDAO;  // cria uma instância de PerfilDAO para manipulação dos dados no banco

    // Construtor que inicializa a instância de PerfilDAO
    public PerfilController() {
        this.perfilDAO = new PerfilDAO();
    }

    // Método para inserir um novo perfil no banco
    public int inserir(Perfil perfil, int idCadastro) {
        try {
            return perfilDAO.inserir(perfil, idCadastro);  // tenta inserir o perfil no banco e retorna o id gerado
        } catch (Exception e) {
            e.printStackTrace();  // se ocorrer um erro, imprime o erro
            return -1;  // se não conseguir inserir, retorna -1
        }
    }

    // Método para buscar um perfil pelo idCadastro
    public Perfil buscarPorCadastroId(int idCadastro) {
        try {
            return perfilDAO.buscarPorCadastroId(idCadastro);  // tenta buscar o perfil pelo idCadastro no banco
        } catch (Exception e) {
            e.printStackTrace();  // se ocorrer um erro, imprime o erro
            return null;  // se não encontrar o perfil, retorna null
        }
    }

    // Método para atualizar um perfil existente no banco
    public void atualizar(int id, Perfil perfil) {
        try {
            perfilDAO.atualizar(id, perfil);  // tenta atualizar os dados do perfil no banco
        } catch (Exception e) {
            e.printStackTrace();  // se ocorrer um erro, imprime o erro
        }
    }

    // Método para listar todos os perfis cadastrados
    public ArrayList<Perfil> listar() {
        try {
            return perfilDAO.listar();  // tenta buscar e retornar todos os perfis cadastrados no banco
        } catch (Exception e) {
            e.printStackTrace();  // se ocorrer um erro, imprime o erro
            return new ArrayList<>();  // se não conseguir listar, retorna uma lista vazia
        }
    }

    // Método para deletar um perfil pelo id
    public void deletar(int id) {
        try {
            perfilDAO.deletar(id);  // tenta deletar o perfil no banco pelo id
        } catch (Exception e) {
            e.printStackTrace();  // se ocorrer um erro, imprime o erro
        }
    }
}
