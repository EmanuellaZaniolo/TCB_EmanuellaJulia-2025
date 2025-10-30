import java.util.ArrayList;
public class Ranking {
    private ArrayList<Cadastro> usuarios = new ArrayList<>();
    private ArrayList<Integer> pontuacao =  new ArrayList<>();

    public Ranking() {
    }

    public ArrayList<Cadastro> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Cadastro> usuarios) {
        this.usuarios = usuarios;
    }
    public ArrayList<Integer> getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(ArrayList<Integer> pontuacao) {
        this.pontuacao = pontuacao;
    }
    

}
