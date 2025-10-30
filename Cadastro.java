public class Cadastro {
    private String nome;
    private String usuario; //Nome da conta. Esse nome não pode se repetir entre os usuários.
    private String email;
    private String senha;
    private int idCadastro;
    public int getIdCadastro() {
        return idCadastro;
    }
    public void setIdCadastro(int idCadastro) {
        this.idCadastro = idCadastro;
    }
    public Cadastro(){

    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
   
}
