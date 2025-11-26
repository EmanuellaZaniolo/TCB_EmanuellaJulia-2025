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

    //daqui pra baixo vai ser só coisas que o admin vai poder fazer
    // ------------------tela admin--------------// 
    public static void mostrarTelaAdmin(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);
    
        // cabeçalho da tela
        JLabel titulo = new JLabel("Painel do Administrador", SwingConstants.CENTER); 
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(200, 40, 600, 50); 
        janela.add(titulo); // adiciona o título à tela
    
        // botão "gerenciar questões" que leva o admin para a tela de gerenciar questões
        JButton botaoQuestoes = new JButton("Gerenciar Questões"); 
        botaoQuestoes.setBounds(350, 150, 300, 40); // define a posição do botão
        botaoQuestoes.addActionListener(e -> mostrarTelaGerenciarQuestoes(janela)); 
        janela.add(botaoQuestoes); // adiciona o botão à tela
    
        // botão "adicionar questão" para permitir ao admin adicionar questões
        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
        botaoAdicionarQuestao.setBounds(350, 220, 300, 40); 
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao(janela)); // abrir a tela de adicionar questão
        janela.add(botaoAdicionarQuestao); // adiciona o botão à tela
    
        // botão "ver usuários" para visualizar os usuários cadastrados
        JButton botaoUsuarios = new JButton("Ver Usuários"); 
        botaoUsuarios.setBounds(350, 290, 300, 40); // define a posição do botão
        janela.add(botaoUsuarios); // adiciona o botão à tela
    
        // botão "sair" para voltar à tela inicial
        JButton botaoVoltar = new JButton("Sair"); 
        botaoVoltar.setBounds(350, 360, 300, 40); 
        botaoVoltar.addActionListener(e -> criarTelaInicial(janela)); 
        janela.add(botaoVoltar); 
    
        janela.revalidate(); // atualiza a tela
        janela.repaint(); // redesenha a tela
    }
    

    public static void mostrarTelaGerenciarQuestoes(JFrame janela){
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);
    
        JLabel titulo = new JLabel("Questões cadastradas", SwingConstants.CENTER); // swingconstants.center deixa no centro da tela 
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);
    
        // chama o controller para pegar as questões do banco
        QuestaoController questaoController = new QuestaoController();
        ArrayList<Questao> lista = questaoController.listar();
    
        int y = 120; // posição inicial para o primeiro botão, fizemos isso para que não fique todos os botões um em cima do outro
        for(Questao q : lista) {
            // exibindo id e enunciado da questão para que o admin saiba qual o id na hora que quiser fazer alguma função (deletar, inserir, editar)
            JLabel lbl = new JLabel("ID: " + q.getIdQuestao() + " - " + q.getEnunciado());
            lbl.setBounds(100, y, 800, 30);
            janela.add(lbl);
    
            JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
            botaoAdicionarQuestao.setBounds(350, 100, 300, 40); // posição do botão
            botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao(janela)); // abre tela de adicionar
            janela.add(botaoAdicionarQuestao);
    
            // botão pra editar a questão
            JButton editar = new JButton("Editar");
            editar.setBounds(650, y, 100, 25);
            editar.addActionListener(e -> editarQuestao(janela, q));
            janela.add(editar);
    
            // botão pra excluir a questão
            JButton excluir = new JButton("Excluir");
            excluir.setBounds(760, y, 100, 25);
            excluir.addActionListener(e -> excluirQuestao(janela, q.getIdQuestao()));
            janela.add(excluir);
    
            y += 40; // aqui que ele vai somar para o próximo item e não ficar um em cima do outro
        }
    
        // botão para voltar ao painel do admin
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, y + 40, 300, 40);
        voltar.addActionListener(e -> mostrarTelaAdmin(janela));
        janela.add(voltar);
    
        janela.revalidate();
        janela.repaint();
    }
    
    public static void mostrarTelaAdicionarQuestao(JFrame janela) {
        janela.getContentPane().removeAll();// vai remover a tela anterior
        janela.repaint();// cria uma nova
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);
    
        JLabel titulo = new JLabel("Adicionar Nova Questão", SwingConstants.CENTER); // enuciado da questao no meio da tela 
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);
    
        // criando o campo da nova questão
        JLabel labelEnunciado = new JLabel("Enunciado:");
        labelEnunciado.setBounds(100, 120, 100, 30);
        janela.add(labelEnunciado);
    
        JTextField campoEnunciado = new JTextField(); //é um campo que vai receber um texto
        campoEnunciado.setBounds(200, 120, 500, 30); // de tamanho tal
        janela.add(campoEnunciado);
    
        // botão pra adicionar a nova questão
        JButton adicionar = new JButton("Adicionar");
        adicionar.setBounds(350, 200, 300, 40);
        adicionar.addActionListener(e -> {
            String enunciado = campoEnunciado.getText(); // deixa o admin colocar a questão que vai ser coocada dentro do banco
    
            if (enunciado.trim().isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Enunciado não pode ser vazio!");
                return;
            }
            Questao novaQuestao = new Questao();
            novaQuestao.setEnunciado(enunciado);
    
            QuestaoController questaoController = new QuestaoController();
            try {
                questaoController.inserir(novaQuestao, 1); // 1 é o id do reino
                JOptionPane.showMessageDialog(janela, "Questão adicionada com sucesso!"); // só pra avisar q foi adicionada 
                mostrarTelaGerenciarQuestoes(janela); // atualiza a lista de questões
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao adicionar questão: " + ex.getMessage()); //avisa que nao foi 
            }
        });
        janela.add(adicionar);
    
        // botão para voltar à tela anterior
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes(janela)); // volta para a lista de questões
        janela.add(voltar);
    
        janela.revalidate();
        janela.repaint();
    }
    
    public static void editarQuestao(JFrame janela, Questao questao) {
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
    
        //aqui vai mostrar o enunciado "antigo". Pensamos em deixar o admin editar o enunciado porque acontece de conceitos, carc., mudarem conforme os anos
        JTextField campoEnunciado = new JTextField(questao.getEnunciado());
        campoEnunciado.setBounds(200, 120, 500, 30);
        janela.add(campoEnunciado);
    
        // aqui o admin vai clicar em salvar para atualizar o enunciado no banco e atualizar a lista de questões
        JButton salvar = new JButton("Salvar");
        salvar.setBounds(350, 200, 300, 40);
        salvar.addActionListener(e -> {
            questao.setEnunciado(campoEnunciado.getText()); // Atualiza o enunciado
            new QuestaoController().atualizar(questao.getIdQuestao(), questao); // Atualiza o banco
            mostrarTelaGerenciarQuestoes(janela); // volta pra tela de gerenciamento
        });
        janela.add(salvar);
    
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes(janela)); // volta para as quesotes listadas
        janela.add(voltar);
    
        janela.revalidate();
        janela.repaint();
    }

    public static void excluirQuestao(JFrame janela, int idQuestao) {
        new QuestaoController().deletar(idQuestao); // Deleta a questão do banco
            mostrarTelaGerenciarQuestoes(janela); // Atualiza a tela com as questões restantes
        
    }

    public static void mostrarTelaGerenciarReinos(){


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
