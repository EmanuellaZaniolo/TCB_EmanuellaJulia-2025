package br.edu.ifpr.tcb_bio.view;

import br.edu.ifpr.tcb_bio.modelo.dao.CadastroDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import br.edu.ifpr.tcb_bio.modelo.*;

public class telaChat {

    private static App app = new App();   // AGORA O APP USA O DAO
    private static Ranking ranking = new Ranking();
    private static Perfil usuarioLogado;

    public static void main(String[] args) {
        JFrame janela = new JFrame("Byolia - Seja bem-vindo(a)! ");
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
        int altura = 840;

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
        botaoConfirmar.setBounds(270, 550, 200, 50);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(270, 600, 200, 50);

        // ------- TRECHO CORRIGIDO (CADASTRO AGORA VAI PARA O BANCO) -------
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
            cadastro.setEmail("sem-email");     // temporário
            cadastro.setTipoUsuario("ALUNO");   // padrão

            // SALVA NO BANCO
            app.cadastrarUsuario(cadastro);

            JOptionPane.showMessageDialog(janela, "Cadastro realizado com sucesso!");
            criarTelaInicial(janela);
        });
        // -----------------------------------------------------------------

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

    // ------------------------- Login -------------------------
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

        // ------- TRECHO CORRIGIDO (LOGIN COM BANCO) -------
        botaoConfirmar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (app.fazerLogin(usuario, senha)) {

                Cadastro c = app.buscarUsuario(usuario);
                usuarioLogado = new Perfil(c); // Agora cria perfil com dados do banco

                mostrarTelaReinos(janela);

            } else {
                JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos!");
            }
        });
        // -----------------------------------------------------

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

        ArrayList<Reino> reinos = criarReinosExemplo();

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
                mostrarTelaReinos(janela);
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
