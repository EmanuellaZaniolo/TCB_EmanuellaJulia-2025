package br.edu.ifpr.tcb_bio.view;

import br.edu.ifpr.tcb_bio.controller.AlternativaController;
import br.edu.ifpr.tcb_bio.controller.CadastroController;
import br.edu.ifpr.tcb_bio.controller.QuestaoController;
import br.edu.ifpr.tcb_bio.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class telaChat {

    // ----------------- CONTROLLERS -----------------
    private static CadastroController cadastroController = new CadastroController();
    private static QuestaoController questaoController = new QuestaoController();
    private static AlternativaController alternativaController = new AlternativaController();


    private static Perfil usuarioLogado;


    public static void main(String[] args) {

        JFrame janela = new JFrame("Byolia - Seja bem-vindo(a)!");
        janela.setSize(1000, 850);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        criarTelaInicial(janela);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    // ------------------------- Tela Inicial -------------------------
    public static void criarTelaInicial(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);

        int largura = 1000;
        int altura = 830;

        ImageIcon icon = new ImageIcon(telaChat.class.getResource("/imagem.png"));
        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        JLabel fundo = new JLabel(new ImageIcon(img));
        fundo.setBounds(0, 0, largura, altura);

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(270, 600, 200, 50);

        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBounds(545, 600, 200, 50);

        botaoLogin.addActionListener(e -> mostrarTelaLogin(janela));
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro(janela));

        janela.add(botaoLogin);
        janela.add(botaoCadastro);
        janela.add(fundo);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Cadastro -------------------------
    public static void mostrarTelaCadastro(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setBackground(Color.WHITE);

        JLabel labelTitulo = new JLabel(
                "Preencha os espaços abaixo para fazer o seu cadastro no Byolia",
                SwingConstants.CENTER
        );
        labelTitulo.setBounds(150, 90, 700, 50);

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();
        labelNome.setBounds(200, 200, 80, 50);
        campoNome.setBounds(280, 200, 480, 50);

        JLabel labelEmail = new JLabel("Email:");
        JTextField campoEmail = new JTextField();
        labelEmail.setBounds(200, 280, 80, 50);
        campoEmail.setBounds(280, 280, 480, 50);

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField();
        labelUsuario.setBounds(200, 360, 80, 50);
        campoUsuario.setBounds(280, 360, 480, 50);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        labelSenha.setBounds(200, 440, 80, 50);
        campoSenha.setBounds(280, 440, 480, 50);

        JButton botaoConfirmar = new JButton("Cadastrar");
        botaoConfirmar.setBounds(550, 600, 200, 40);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(270, 600, 200, 40);


        botaoConfirmar.addActionListener(e -> {

            Cadastro cadastro = new Cadastro();
            cadastro.setNomePessoa(campoNome.getText());
            cadastro.setEmail(campoEmail.getText());
            cadastro.setNomeUsuario(campoUsuario.getText());
            cadastro.setSenha(new String(campoSenha.getPassword()));
            cadastro.setTipoUsuario("ALUNO");

            String resultado = cadastroController.cadastrar(cadastro);
            JOptionPane.showMessageDialog(janela, resultado);

            if (resultado.equals("Cadastro realizado com sucesso!")) {
                criarTelaInicial(janela);
            }
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial(janela));

        janela.add(labelTitulo);
        janela.add(labelNome);     janela.add(campoNome);
        janela.add(labelEmail);    janela.add(campoEmail);
        janela.add(labelUsuario);  janela.add(campoUsuario);
        janela.add(labelSenha);    janela.add(campoSenha);
        janela.add(botaoConfirmar);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Login -------------------------
    public static void mostrarTelaLogin(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setBackground(Color.WHITE);

        JLabel labelTitulo = new JLabel(
                "Preencha os espaços abaixo para fazer o seu login no Byolia",
                SwingConstants.CENTER
        );
        labelTitulo.setBounds(150, 90, 700, 50);

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField campoUsuario = new JTextField();
        labelUsuario.setBounds(200, 200, 80, 50);
        campoUsuario.setBounds(280, 200, 480, 50);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        labelSenha.setBounds(200, 280, 80, 50);
        campoSenha.setBounds(280, 280, 480, 50);

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(310, 360, 200, 40);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(530, 360, 200, 40);


        botaoLogin.addActionListener(e -> {

            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            Cadastro c = cadastroController.login(usuario, senha);

            if (c != null) {

                usuarioLogado = new Perfil(c);

                if (c.getTipoUsuario().equals("ADMIN")) {
                    mostrarTelaAdmin(janela);
                } else {
                    mostrarTelaReinos(janela);
                }

            } else {
                JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos!");
            }
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial(janela));

        janela.add(labelTitulo);
        janela.add(labelUsuario); janela.add(campoUsuario);
        janela.add(labelSenha);   janela.add(campoSenha);
        janela.add(botaoLogin);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Admin -------------------------
    public static void mostrarTelaAdmin(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();

        JLabel titulo = new JLabel("Área Administrativa", SwingConstants.CENTER);
        titulo.setBounds(100, 50, 400, 30);

        JButton botaoQuestoes = new JButton("Gerenciar Questões");
        botaoQuestoes.setBounds(150, 120, 200, 40);

        JButton botaoReinos = new JButton("Voltar");
        botaoReinos.setBounds(150, 180, 200, 40);

        botaoReinos.addActionListener(e -> mostrarTelaReinos(janela));

        janela.add(titulo);
        janela.add(botaoQuestoes);
        janela.add(botaoReinos);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Reinos -------------------------
    public static void mostrarTelaReinos(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Escolha um Reino", SwingConstants.CENTER);
        titulo.setBounds(100, 50, 300, 30);
        janela.add(titulo);

        ArrayList<Reino> reinos = criarReinosExemplo();

        int y = 100;

        for (Reino reino : reinos) {
            JButton botao = new JButton(reino.getNomeReino());
            botao.setBounds(150, y, 200, 30);

            botao.addActionListener(e -> mostrarTelaQuestoes(janela, reino));

            janela.add(botao);
            y += 40;
        }

        JButton botaoPerfil = new JButton("Perfil");
        botaoPerfil.setBounds(200, y + 20, 100, 30);
        botaoPerfil.addActionListener(e -> mostrarTelaPerfil(janela));

        janela.add(botaoPerfil);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Questões (Banco) -------------------------
    public static void mostrarTelaQuestoes(JFrame janela, Reino reino) {

        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setBackground(Color.WHITE);

        ArrayList<Questao> questoes = questaoController.buscarPorReino(reino.getId());

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(janela, "Nenhuma questão cadastrada para este reino.");
            mostrarTelaReinos(janela);
            return;
        }

        // pega 1ª questão (pode randomizar)
        Questao q = questoes.get(0);

        // carregar alternativas
        ArrayList<Alternativa> alternativas =alternativaController.listarPorQuestao(q.getId());

        q.setAlternativas(alternativas);

        JLabel labelQuestao = new JLabel(q.getEnunciado(), SwingConstants.CENTER);
        labelQuestao.setBounds(50, 50, 400, 30);
        janela.add(labelQuestao);

        int y = 100;

        for (Alternativa alt : alternativas) {

            JButton botao = new JButton(alt.getTexto());
            botao.setBounds(150, y, 200, 30);

            botao.addActionListener(e -> {
                if (alt.isCorreta()) {
                    usuarioLogado.adicionarAcerto();
                    JOptionPane.showMessageDialog(janela, "Correto!");
                } else {
                    JOptionPane.showMessageDialog(janela, "Errado!");
                }
                mostrarTelaReinos(janela);
            });

            janela.add(botao);
            y += 40;
        }

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Perfil -------------------------
    public static void mostrarTelaPerfil(JFrame janela) {

        janela.getContentPane().removeAll();
        janela.repaint();

        JLabel titulo = new JLabel(
                "Perfil de " + usuarioLogado.getCadastro().getNomeUsuario(),
                SwingConstants.CENTER
        );
        titulo.setBounds(100, 50, 300, 30);

        JLabel acertos = new JLabel(
                "Acertos: " + usuarioLogado.getTotalAcertos(),
                SwingConstants.CENTER
        );
        acertos.setBounds(100, 100, 300, 30);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 150, 100, 30);
        botaoVoltar.addActionListener(e -> mostrarTelaReinos(janela));

        janela.add(titulo);
        janela.add(acertos);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Criar reinos temporários -------------------------
    private static ArrayList<Reino> criarReinosExemplo() {

        ArrayList<Reino> lista = new ArrayList<>();

        Reino animalia = new Reino();
        animalia.setId(1); // PRECISA TER ID !!!
        animalia.setNomeReino("Animalia");
        animalia.setDescricao("Reino Animal");

        lista.add(animalia);

        return lista;
    }
}
