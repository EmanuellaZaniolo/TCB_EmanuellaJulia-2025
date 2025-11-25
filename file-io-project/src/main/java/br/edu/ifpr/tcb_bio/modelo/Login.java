package br.edu.ifpr.tcb_bio.modelo;

public class Login {

    private String nomeUsuario;
    private String senha;


    public Login() {

    }

    public Login(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    public boolean validar(Cadastro cadastro) {
        if (cadastro == null) {
            return false;
        }
        return cadastro.getNomeUsuario().equals(this.nomeUsuario)
               && cadastro.getSenha().equals(this.senha);
    }

    // verifica se o usuário é admin ou aluno, essa validação vai ser necessária para deixar possível certas funçoẽs ao usuário
    public boolean ehAdmin(Cadastro cadastro){
        if(cadastro.getTipoUsuario() == "ADMIN"){
            return true;

        }
        return false;
    }


}
