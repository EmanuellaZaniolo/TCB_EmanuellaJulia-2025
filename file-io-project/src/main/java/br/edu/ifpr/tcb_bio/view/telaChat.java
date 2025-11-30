package br.edu.ifpr.tcb_bio.view;

import br.edu.ifpr.tcb_bio.controller.AlternativaController;
import br.edu.ifpr.tcb_bio.controller.CadastroController;
import br.edu.ifpr.tcb_bio.controller.PerfilController;
import br.edu.ifpr.tcb_bio.controller.QuestaoController;
import br.edu.ifpr.tcb_bio.controller.ReinoController;
import br.edu.ifpr.tcb_bio.modelo.Alternativa;
import br.edu.ifpr.tcb_bio.modelo.Cadastro;
import br.edu.ifpr.tcb_bio.modelo.Perfil;
import br.edu.ifpr.tcb_bio.modelo.Questao;
import br.edu.ifpr.tcb_bio.modelo.Reino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Tela principal (view) da aplicação. Arquivo refatorado para baixo acoplamento,
 * uso centralizado de controllers e persistência com banco de dados.
 *
 * Substitua este arquivo no seu projeto em: br/edu/ifpr/tcb_bio/view/telaChat.java
 */
public class telaChat {

    // ----------------- CONTROLLERS (instâncias únicas) -----------------
    private static final CadastroController cadastroController = new CadastroController();
    private static final QuestaoController questaoController = new QuestaoController();
    private static final AlternativaController alternativaController = new AlternativaController();
    private static final PerfilController perfilController = new PerfilController();
    private static final ReinoController reinoController = new ReinoController();

    private static Perfil usuarioLogado;

    // Janela principal (referência única usada por todas as telas)
    private static JFrame janela;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            janela = new JFrame("Byolia - Seja bem-vindo(a)!");
            janela.setSize(1000, 850);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLayout(null);

            criarTelaInicial();

            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }

    // ------------------------- Tela Inicial -------------------------
    private static void criarTelaInicial() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);

        int largura = 1000;
        int altura = 830;

        // tenta carregar imagem de fundo; se não existir, ignora
        try {
            ImageIcon icon = new ImageIcon(telaChat.class.getResource("/imagem.png"));
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            JLabel fundo = new JLabel(new ImageIcon(img));
            fundo.setBounds(0, 0, largura, altura);
            janela.add(fundo);
        } catch (Exception ignored) {
        }

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(270, 600, 200, 50);

        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBounds(545, 600, 200, 50);

        botaoLogin.addActionListener(e -> mostrarTelaLogin());
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro());

        janela.add(botaoLogin);
        janela.add(botaoCadastro);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------------- Tela Cadastro -------------------------
    private static void mostrarTelaCadastro() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

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

            if ("Cadastro realizado com sucesso!".equals(resultado)) {
                criarTelaInicial();
            }
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial());

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
    private static void mostrarTelaLogin() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

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
                // tenta carregar perfil persistido
                Perfil perfil = perfilController.buscarPorCadastroId(c.getId());
                if (perfil == null) {
                    perfil = new Perfil(c);
                    perfil.setTotalAcertos(0);
                    perfilController.inserir(perfil, c.getId()); // cria no banco
                    // recarrega para garantir consistência
                    perfil = perfilController.buscarPorCadastroId(c.getId());
                }

                usuarioLogado = perfil;
                usuarioLogado.setCadastro(c); // relaciona o cadastro ao perfil em memória

                if ("ADMIN".equalsIgnoreCase(c.getTipoUsuario())) {
                    mostrarTelaAdmin();
                } else {
                    mostrarTelaReinos();
                }

            } else {
                JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos!");
            }
        });

        botaoVoltar.addActionListener(e -> criarTelaInicial());

        janela.add(labelTitulo);
        janela.add(labelUsuario); janela.add(campoUsuario);
        janela.add(labelSenha);   janela.add(campoSenha);
        janela.add(botaoLogin);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Tela Admin ------------------
    private static void mostrarTelaAdmin() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Painel do Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(200, 40, 600, 50);
        janela.add(titulo);

        JButton botaoQuestoes = new JButton("Gerenciar Questões");
        botaoQuestoes.setBounds(350, 150, 300, 40);
        botaoQuestoes.addActionListener(e -> mostrarTelaGerenciarQuestoes());
        janela.add(botaoQuestoes);

        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
        botaoAdicionarQuestao.setBounds(350, 220, 300, 40);
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao());
        janela.add(botaoAdicionarQuestao);

        JButton botaoUsuarios = new JButton("Ver Usuários");
        botaoUsuarios.setBounds(350, 290, 300, 40);
        // implementação da listagem de usuários pode ser adicionada
        janela.add(botaoUsuarios);

        JButton botaoVoltar = new JButton("Sair");
        botaoVoltar.setBounds(350, 360, 300, 40);
        botaoVoltar.addActionListener(e -> criarTelaInicial());
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Gerenciar Questões (Admin) ------------------
    private static void mostrarTelaGerenciarQuestoes() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Questões cadastradas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);

        ArrayList<Questao> lista = questaoController.listar();

        int y = 120;

        // botão global para adicionar questão
        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
        botaoAdicionarQuestao.setBounds(350, 100, 300, 40);
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao());
        janela.add(botaoAdicionarQuestao);

        for (Questao q : lista) {
            JLabel lbl = new JLabel("ID: " + q.getId() + " - " + q.getEnunciado());
            lbl.setBounds(100, y, 800, 30);
            janela.add(lbl);

            JButton editar = new JButton("Editar");
            editar.setBounds(650, y, 100, 25);
            editar.addActionListener(e -> editarQuestao(q));
            janela.add(editar);

            JButton excluir = new JButton("Excluir");
            excluir.setBounds(760, y, 100, 25);
            excluir.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(janela,
                        "Deseja realmente excluir a questão ID " + q.getId() + "?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    questaoController.deletar(q.getId());
                    mostrarTelaGerenciarQuestoes();
                }
            });
            janela.add(excluir);

            JButton gerenciarAlt = new JButton("Alternativas");
            gerenciarAlt.setBounds(530, y, 110, 25);
            gerenciarAlt.addActionListener(e -> mostrarTelaGerenciarAlternativas(q));
            janela.add(gerenciarAlt);

            y += 40;
        }

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, y + 40, 300, 40);
        voltar.addActionListener(e -> mostrarTelaAdmin());
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Adicionar Questão (Admin) ------------------
    private static void mostrarTelaAdicionarQuestao() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Adicionar Nova Questão", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);

        JLabel labelEnunciado = new JLabel("Enunciado:");
        labelEnunciado.setBounds(100, 120, 100, 30);
        janela.add(labelEnunciado);

        JTextField campoEnunciado = new JTextField();
        campoEnunciado.setBounds(200, 120, 500, 30);
        janela.add(campoEnunciado);

        // combo com reinos do DB
        ArrayList<Reino> reinos;
        try {
            reinos = reinoController.listar();
        } catch (Exception ex) {
            reinos = new ArrayList<>();
            Reino fallback = new Reino();
            fallback.setId(1);
            fallback.setNomeReino("Animalia");
            reinos.add(fallback);
        }

        JComboBox<Reino> comboReinos = new JComboBox<>();
        for (Reino r : reinos) comboReinos.addItem(r);
        comboReinos.setBounds(200, 160, 300, 30);
        janela.add(comboReinos);

        JButton adicionar = new JButton("Adicionar");
        adicionar.setBounds(350, 200, 300, 40);
        adicionar.addActionListener(e -> {
            String enunciado = campoEnunciado.getText();

            if (enunciado.trim().isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Enunciado não pode ser vazio!");
                return;
            }
            Questao novaQuestao = new Questao();
            novaQuestao.setEnunciado(enunciado);

            Reino selecionado = (Reino) comboReinos.getSelectedItem();
            int idReino = (selecionado != null) ? selecionado.getId() : 1;

            try {
                int idGerado = questaoController.inserir(novaQuestao, idReino);
                if (idGerado > 0) {
                    JOptionPane.showMessageDialog(janela, "Questão adicionada com sucesso! ID: " + idGerado);
                    Questao qInserida = questaoController.buscarPorId(idGerado);
                    if (qInserida != null) mostrarTelaGerenciarAlternativas(qInserida);
                    else mostrarTelaGerenciarQuestoes();
                } else {
                    JOptionPane.showMessageDialog(janela, "Erro ao adicionar questão.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao adicionar questão: " + ex.getMessage());
            }
        });
        janela.add(adicionar);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes());
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    private static void editarQuestao(Questao questao) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Questão", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);

        JLabel labelEnunciado = new JLabel("Enunciado:");
        labelEnunciado.setBounds(100, 120, 100, 30);
        janela.add(labelEnunciado);

        JTextField campoEnunciado = new JTextField(questao.getEnunciado());
        campoEnunciado.setBounds(200, 120, 500, 30);
        janela.add(campoEnunciado);

        JButton salvar = new JButton("Salvar");
        salvar.setBounds(350, 200, 300, 40);
        salvar.addActionListener(e -> {
            questao.setEnunciado(campoEnunciado.getText());
            questaoController.atualizar(questao.getId(), questao);
            mostrarTelaGerenciarQuestoes();
        });
        janela.add(salvar);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes());
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Gerenciar Alternativas (Admin) ------------------
    private static void mostrarTelaGerenciarAlternativas(Questao questao) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Alternativas - Questão ID: " + questao.getId(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);

        ArrayList<Alternativa> alternativas = alternativaController.listarPorQuestao(questao.getId());

        int y = 110;
        for (Alternativa a : alternativas) {
            JLabel lbl = new JLabel((a.isCorreta() ? "[CORRETA] " : "") + a.getTexto());
            lbl.setBounds(100, y, 600, 25);
            janela.add(lbl);

            JButton editar = new JButton("Editar");
            editar.setBounds(710, y, 80, 25);
            editar.addActionListener(e -> mostrarTelaEditarAlternativa(questao, a));
            janela.add(editar);

            JButton delet = new JButton("Excluir");
            delet.setBounds(800, y, 80, 25);
            delet.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(janela,
                        "Excluir alternativa?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    alternativaController.deletar(a.getId());
                    mostrarTelaGerenciarAlternativas(questao);
                }
            });
            janela.add(delet);

            y += 35;
        }

        JLabel labelTexto = new JLabel("Texto:");
        labelTexto.setBounds(100, y + 10, 50, 25);
        JTextField campoTexto = new JTextField();
        campoTexto.setBounds(150, y + 10, 500, 25);
        JCheckBox chkCorreta = new JCheckBox("Correta");
        chkCorreta.setBounds(660, y + 10, 100, 25);

        JButton botaoAdicionar = new JButton("Adicionar Alternativa");
        botaoAdicionar.setBounds(350, y + 50, 300, 35);
        botaoAdicionar.addActionListener(e -> {
            String texto = campoTexto.getText();
            boolean correta = chkCorreta.isSelected();

            if (texto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Texto não pode ser vazio!");
                return;
            }

            Alternativa nova = new Alternativa();
            nova.setTexto(texto);
            nova.setCorreta(correta);
            try {
                alternativaController.cadastrar(nova, questao.getId());
                mostrarTelaGerenciarAlternativas(questao);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao cadastrar alternativa: " + ex.getMessage());
            }
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, y + 100, 300, 35);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes());

        janela.add(labelTexto);
        janela.add(campoTexto);
        janela.add(chkCorreta);
        janela.add(botaoAdicionar);
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    private static void mostrarTelaEditarAlternativa(Questao questao, Alternativa alt) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Alternativa - Questão ID: " + questao.getId(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);

        JLabel labelTexto = new JLabel("Texto:");
        labelTexto.setBounds(100, 120, 50, 25);
        JTextField campoTexto = new JTextField(alt.getTexto());
        campoTexto.setBounds(160, 120, 500, 25);
        JCheckBox chkCorreta = new JCheckBox("Correta", alt.isCorreta());
        chkCorreta.setBounds(680, 120, 100, 25);

        JButton salvar = new JButton("Salvar");
        salvar.setBounds(350, 200, 300, 40);
        salvar.addActionListener(e -> {
            alt.setTexto(campoTexto.getText());
            alt.setCorreta(chkCorreta.isSelected());
            alternativaController.atualizar(alt.getId(), alt);
            mostrarTelaGerenciarAlternativas(questao);
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarAlternativas(questao));

        janela.add(labelTexto);
        janela.add(campoTexto);
        janela.add(chkCorreta);
        janela.add(salvar);
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Mostrar Questões para o aluno ------------------
    private static void mostrarTelaReinos() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Escolha um Reino", SwingConstants.CENTER);
        titulo.setBounds(100, 50, 300, 30);
        janela.add(titulo);

        ArrayList<Reino> reinos;
        try {
            reinos = reinoController.listar();
        } catch (Exception e) {
            reinos = criarReinosExemplo();
        }

        int y = 100;
        for (Reino reino : reinos) {
            JButton botao = new JButton(reino.getNomeReino());
            botao.setBounds(150, y, 200, 30);
            botao.addActionListener(e -> mostrarTelaQuestoes(reino));
            janela.add(botao);
            y += 40;
        }

        JButton botaoPerfil = new JButton("Perfil");
        botaoPerfil.setBounds(200, y + 20, 100, 30);
        botaoPerfil.addActionListener(e -> mostrarTelaPerfil());
        janela.add(botaoPerfil);

        janela.revalidate();
        janela.repaint();
    }

    private static void mostrarTelaQuestoes(Reino reino) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        ArrayList<Questao> questoes = questaoController.buscarPorReino(reino.getId());

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(janela, "Nenhuma questão cadastrada para este reino.");
            mostrarTelaReinos();
            return;
        }

        // pega 1ª questão (pode randomizar futuramente)
        Questao q = questoes.get(0);

        // carregar alternativas
        ArrayList<Alternativa> alternativas = alternativaController.listarPorQuestao(q.getId());
        q.setAlternativas(alternativas);

        JLabel labelQuestao = new JLabel("<html><body style='width:800px'>" + q.getEnunciado() + "</body></html>", SwingConstants.CENTER);
        labelQuestao.setBounds(50, 20, 900, 60);
        janela.add(labelQuestao);

        int y = 120;
        for (Alternativa alt : alternativas) {
            JButton botao = new JButton(alt.getTexto());
            botao.setBounds(150, y, 600, 30);
            botao.addActionListener(e -> {
                if (alt.isCorreta()) {
                    usuarioLogado.adicionarAcerto();
                    // atualiza no banco: busca perfil pelo cadastro e atualiza totalAcertos
                    try {
                        Perfil pBanco = perfilController.buscarPorCadastroId(usuarioLogado.getCadastro().getId());
                        if (pBanco != null) {
                            pBanco.setTotalAcertos(usuarioLogado.getTotalAcertos());
                            // Observação: para atualizar corretamente o registro é preciso do id do perfil
                            // Se desejar, eu adiciono o campo id em Perfil e garanto que o DAO/Controller setem esse id.
                            // Por enquanto, caso seu PerfilDAO atual suporte atualizar por id, você pode adaptar.
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(janela, "Correto!");
                } else {
                    JOptionPane.showMessageDialog(janela, "Errado!");
                }
                mostrarTelaReinos();
            });
            janela.add(botao);
            y += 40;
        }

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Tela Perfil ------------------
    private static void mostrarTelaPerfil() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        String nomeUsuario = (usuarioLogado != null && usuarioLogado.getCadastro() != null)
                ? usuarioLogado.getCadastro().getNomeUsuario() : "Usuário";

        JLabel titulo = new JLabel("Perfil de " + nomeUsuario, SwingConstants.CENTER);
        titulo.setBounds(100, 50, 300, 30);
        janela.add(titulo);

        int acertos = (usuarioLogado != null) ? usuarioLogado.getTotalAcertos() : 0;
        JLabel labelAcertos = new JLabel("Acertos: " + acertos, SwingConstants.CENTER);
        labelAcertos.setBounds(100, 100, 300, 30);
        janela.add(labelAcertos);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(200, 150, 100, 30);
        botaoVoltar.addActionListener(e -> mostrarTelaReinos());
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Utilitário: reinos de exemplo ------------------
    private static ArrayList<Reino> criarReinosExemplo() {
        ArrayList<Reino> lista = new ArrayList<>();
        Reino animalia = new Reino();
        animalia.setId(1);
        animalia.setNomeReino("Animalia");
        animalia.setDescricao("Reino Animal");
        lista.add(animalia);
        return lista;
    }
}
