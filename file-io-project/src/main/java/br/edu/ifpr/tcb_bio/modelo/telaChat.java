package br.edu.ifpr.tcb_bio.modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class telaChat {

    private static App app = new App();
    private static Ranking ranking = new Ranking();
    private static Perfil usuarioLogado;

    public static void main(String[] args) {
        JFrame janela = new JFrame("Byolia - Estudo dirigido de biologia");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        criarTelaInicial(janela);

        janela.setVisible(true);
    }

    // ------------------------- Tela Inicial -------------------------
    public static void criarTelaInicial(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Bem-vindo ao Byolia", SwingConstants.CENTER);
        titulo.setBounds(100, 100, 300, 30);
        janela.add(titulo);

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(150, 200, 90, 30);
        janela.add(botaoLogin);

        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBounds(260, 200, 90, 30);
        janela.add(botaoCadastro);

        botaoLogin.addActionListener(e -> mostrarTelaLogin(janela));
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro(janela));

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Cadastro -------------------------
    public static void mostrarTelaCadastro(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(100, 50, 80, 25);
        JTextField campoNome = new JTextField();
        campoNome.setBounds(180, 50, 200, 25);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(100, 90, 80, 25);
        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(180, 90, 200, 25);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(100, 130, 80, 25);
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(180, 130, 200, 25);

        JButton botaoConfirmar = new JButton("Cadastrar");
        botaoConfirmar.setBounds(150, 180, 100, 30);
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(260, 180, 100, 30);

        botaoConfirmar.addActionListener(e -> {
            String nome = campoNome.getText();
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Preencha todos os campos!");
                return;
            }

            Cadastro cadastro = new Cadastro();
            cadastro.setNomePessoa(nome);
            cadastro.setNomeUsuario(usuario);
            cadastro.setSenha(senha);

            app.cadastrarUsuario(cadastro);

            Perfil perfil = new Perfil(cadastro);
            ranking.adicionarPerfil(perfil);

            JOptionPane.showMessageDialog(janela, "Cadastro realizado com sucesso!");
            criarTelaInicial(janela);
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial(janela));

        janela.add(labelNome);
        janela.add(campoNome);
        janela.add(labelUsuario);
        janela.add(campoUsuario);
        janela.add(labelSenha);
        janela.add(campoSenha);
        janela.add(botaoConfirmar);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Login -------------------------
    public static void mostrarTelaLogin(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(100, 50, 80, 25);
        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(180, 50, 200, 25);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(100, 90, 80, 25);
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(180, 90, 200, 25);

        JButton botaoConfirmar = new JButton("Login");
        botaoConfirmar.setBounds(150, 140, 100, 30);
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(260, 140, 100, 30);

        botaoConfirmar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (app.fazerLogin(usuario, senha)) {
                usuarioLogado = ranking.getPerfis().get(app.getCadastros().indexOf(app.buscarUsuario(usuario)));
                mostrarTelaReinos(janela);
            } else {
                JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos!");
            }
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial(janela));

        janela.add(labelUsuario);
        janela.add(campoUsuario);
        janela.add(labelSenha);
        janela.add(campoSenha);
        janela.add(botaoConfirmar);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Reinos -------------------------
    public static void mostrarTelaReinos(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Escolha um Reino", SwingConstants.CENTER);
        titulo.setBounds(100, 50, 300, 30);
        janela.add(titulo);

        ArrayList<Reino> reinos = criarReinosExemplo(); // exemplo de reinos

        int y = 100;
        for (Reino reino : reinos) {
            JButton botaoReino = new JButton(reino.getNomeReino());
            botaoReino.setBounds(150, y, 200, 30);
            botaoReino.addActionListener(e -> mostrarTelaQuestoes(janela, reino));
            janela.add(botaoReino);
            y += 40;
        }

        JButton botaoPerfil = new JButton("Perfil");
        botaoPerfil.setBounds(200, y + 20, 100, 30);
        botaoPerfil.addActionListener(e -> mostrarTelaPerfil(janela));
        janela.add(botaoPerfil);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Questões -------------------------
    public static void mostrarTelaQuestoes(JFrame janela, Reino reino) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        // Criar questão de exemplo
        Questao q = new Questao();
        q.setEnunciado("Qual característica do reino " + reino.getNomeReino() + "?");
        q.setReino(reino);
        q.adicionarAlternativa(new Alternativa("Alternativa 1", false));
        q.adicionarAlternativa(new Alternativa("Alternativa 2", true));
        q.adicionarAlternativa(new Alternativa("Alternativa 3", false));

        JLabel labelQuestao = new JLabel(q.getEnunciado(), SwingConstants.CENTER);
        labelQuestao.setBounds(50, 50, 400, 30);
        janela.add(labelQuestao);

        int y = 100;
        for (Alternativa alt : q.getAlternativas()) {
            JButton botaoAlt = new JButton(alt.getTexto());
            botaoAlt.setBounds(150, y, 200, 30);
            botaoAlt.addActionListener(e -> {
                if (alt.isCorreta()) {
                    usuarioLogado.adicionarAcerto();
                    JOptionPane.showMessageDialog(janela, "Correto!");
                } else {
                    JOptionPane.showMessageDialog(janela, "Errado!");
                }
                mostrarTelaReinos(janela); // volta para reinos
            });
            janela.add(botaoAlt);
            y += 40;
        }

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, y, 100, 30);
        botaoVoltar.addActionListener(e -> mostrarTelaReinos(janela));
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Perfil -------------------------
    public static void mostrarTelaPerfil(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Perfil de " + usuarioLogado.getCadastro().getNomeUsuario(), SwingConstants.CENTER);
        titulo.setBounds(100, 50, 300, 30);
        janela.add(titulo);

        JLabel acertos = new JLabel("Acertos: " + usuarioLogado.getTotalAcertos(), SwingConstants.CENTER);
        acertos.setBounds(100, 100, 300, 30);
        janela.add(acertos);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 150, 100, 30);
        botaoVoltar.addActionListener(e -> mostrarTelaReinos(janela));
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Criar reinos de exemplo -------------------------
    private static ArrayList<Reino> criarReinosExemplo() {
        ArrayList<Reino> reinos = new ArrayList<>();
        Reino animalia = new Reino();
        animalia.setNomeReino("Animalia");
        animalia.setDescricao("Reino Animal");
        reinos.add(animalia);

        Reino plantae = new Reino();
        plantae.setNomeReino("Plantae");
        plantae.setDescricao("Reino Plantae");
        reinos.add(plantae);

        return reinos;
    }
}
