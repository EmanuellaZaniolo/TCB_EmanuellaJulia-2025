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

        botaoVoltar.addActionListener(e -> criarTelaInicial());// aqui vai voltar a tela inicial

        janela.add(labelTitulo);
        janela.add(labelUsuario); janela.add(campoUsuario);
        janela.add(labelSenha);   janela.add(campoSenha);
        janela.add(botaoLogin);
        janela.add(botaoVoltar);// da linha 218 até a 222 são só as adições dos elemntos 

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

        //--------------------------------Botão global de adicionar questões ------------------------------------//
        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
        botaoAdicionarQuestao.setBounds(350, 100, 300, 40);
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao());
        janela.add(botaoAdicionarQuestao);

        for (Questao q : lista) {// vai percorrer por toda a minha lista de questoes 
            JLabel lbl = new JLabel("ID: " + q.getId() + " - " + q.getEnunciado()); // cria um 'texto' que vai mostrar o id e o enunciado da questao
            lbl.setBounds(100, y, 800, 30);//vai posicionar, o y ele foi usado para que a cada questao que for listada ela apareça y pra baixo da anterior
            //isso evita que todas as quesptesapareçam uma em cima da outra
            janela.add(lbl);//adiciona a trla

            //-----------------------------Botão editar------------------------------//
            JButton editar = new JButton("Editar");
            editar.setBounds(650, y, 100, 25); // o y tem a msm função da linha 288
            editar.addActionListener(e -> editarQuestao(q)); // vai chamar a tela de editar quesotes e passar q questao específica
            janela.add(editar); // adiciona o botão

            //-----------------------------Botão excluir ----------------------------//
            JButton excluir = new JButton("Excluir");
            excluir.setBounds(760, y, 100, 25);// y tem a msm função da linha 288
            excluir.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(janela,
                        "Deseja realmente excluir a questão com ID : " + q.getId() + "?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION); // aqui vai aparecer uma caixinha que vai perguntar ao admin se ele REALMENTE quer deletar permanentemente aquela pergunta
                if (confirm == JOptionPane.YES_OPTION) {
                    questaoController.deletar(q.getId()); // se ele apertar q sim vai deletá-la permanentemente do database
                    mostrarTelaGerenciarQuestoes();// volta para a tela de gerenciamento de questoes
                }
            });
            janela.add(excluir);

            //----------------------------Botão alternativa -----------------------//
            JButton gerenciarAlt = new JButton("Alternativas");
            gerenciarAlt.setBounds(530, y, 110, 25);// y com a mesma função da linha 288
            gerenciarAlt.addActionListener(e -> mostrarTelaGerenciarAlternativas(q));// vai encaminhar à tela de gerenciar alternativas, ja passando a questao q foi escolinha
            janela.add(gerenciarAlt); // adiciona à tela

            y += 40;// aqui vai almentar o y, pra que a prox. questao apareça mais abaixo
        }

        JButton voltar = new JButton("Voltar"); 
        voltar.setBounds(350, y + 40, 300, 40);
        voltar.addActionListener(e -> mostrarTelaAdmin()); // vai voltar para o "menu" do admin 
        janela.add(voltar);//adiciona à tela

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

        JLabel labelEnunciado = new JLabel("Enunciado:");// 
        labelEnunciado.setBounds(100, 120, 100, 30);
        janela.add(labelEnunciado);

        JTextField campoEnunciado = new JTextField();// aqui é onde o admin vai digitar o enunciado
        campoEnunciado.setBounds(200, 120, 500, 30); // define a posição
        janela.add(campoEnunciado); //adiciona a tela para que ele consiga digitar

        // combo com reinos do bd
        ArrayList<Reino> reinos;
        try {
            reinos = reinoController.listar();
        } catch (Exception ex) { // o fallback é só um plano b, se o plano a nao der cderto o plano b entra em ação para que não aonteça uma quebra no progrma
            reinos = new ArrayList<>();
            Reino fallback = new Reino();
            fallback.setId(1);
            fallback.setNomeReino("Animalia");
            reinos.add(fallback);
        }

        JComboBox<Reino> comboReinos = new JComboBox<>(); // usamos o comboBox para ser mais bonito visualmente, vai aparecer para o admin escolher qual reino ele deseja colocar o enunciado
        //ele vai ser um objeto do Reino e não somente um texto
        for (Reino r : reinos) comboReinos.addItem(r); // aqui que ele pega cada reino(nome) e coloca no combo, isso é permitido graças ao toString, que retorna só o nome 
        comboReinos.setBounds(200, 160, 300, 30);
        janela.add(comboReinos);//adiciona para que o admin tenha como clicar

        JButton adicionar = new JButton("Adicionar"); 
        adicionar.setBounds(350, 200, 300, 40);
        adicionar.addActionListener(e -> { // aqui vai executar tudo depois que clicado o adicionar, vai fazer os testes necessarios para adicionar no database
            String enunciado = campoEnunciado.getText(); // pega o texto digitado pelo usuario

            if (enunciado.trim().isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Enunciado não pode ser vazio!");
                return;
            }
            Questao novaQuestao = new Questao();//cria uma nova questao
            novaQuestao.setEnunciado(enunciado);// coloca dentro o texto digitado

            Reino selecionado = (Reino) comboReinos.getSelectedItem(); // pega o reino selecionado
            int idReino = (selecionado != null) ? selecionado.getId() : 1; // esse é o plano b, vamos contar que o usuario é inteligente e nunca vai cair no plano b,
            //mas se cair no plano b o reino "padrão" sera o de id 1

            try {
                int idGerado = questaoController.inserir(novaQuestao, idReino);//chama o controller pra salvar no database, aqui ele vai voltar o id que foi gerado no banco, ele deve ser maor do que 0,
                //pois no banco ja terão outras questões inseridas
                if (idGerado > 0) {
                    JOptionPane.showMessageDialog(janela, "Questão adicionada com sucesso! ID: " + idGerado); // se for maior do 0 sginifica que deu certo
                    Questao qInserida = questaoController.buscarPorId(idGerado);
                    if (qInserida != null) mostrarTelaGerenciarAlternativas(qInserida);// vai aparec er a outra tela para que seja inseridas agora as alternativas da questao
                    else mostrarTelaGerenciarQuestoes(); // algo deu errado ent 
                } else {
                    JOptionPane.showMessageDialog(janela, "Erro ao adicionar questão.");// algo deu errado na inserção da questao no database
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao adicionar questão: " + ex.getMessage());//tnbm deu algum erro na inserção da questão
            }
        });
        janela.add(adicionar); // adiciona á tela

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes());// caso queria voltar, volta a tela de gerenciar questoes 
        janela.add(voltar);

        janela.revalidate();
        janela.repaint();
    }

    //---------------------------------Editar quetões(admin) --------------------------//
    private static void editarQuestao(Questao questao) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Questão", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22)); // vai configurar o texto que será adicionado á tela
        titulo.setBounds(200, 40, 600, 40);// posiciona na tela
        janela.add(titulo);//adiciona á tela

        JLabel labelEnunciado = new JLabel("Enunciado:");
        labelEnunciado.setBounds(100, 120, 100, 30);
        janela.add(labelEnunciado); // vai mostar ao admin o enunciado

        JTextField campoEnunciado = new JTextField(questao.getEnunciado());// vai criar o espaço de edição, mas ja vai estar preenchido com o enunciado anterior, o admin edita conforme quiser
        campoEnunciado.setBounds(200, 120, 500, 30);
        janela.add(campoEnunciado);//adiciona à tela

        JButton salvar = new JButton("Salvar"); 
        salvar.setBounds(350, 200, 300, 40);
        salvar.addActionListener(e -> {
            questao.setEnunciado(campoEnunciado.getText()); // pega o texto editado
            questaoController.atualizar(questao.getId(), questao);//atualiza no banco
            mostrarTelaGerenciarQuestoes();//volta para a tela de gerenciamento
        });
        janela.add(salvar);// adiciona o botao

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes()); // volta para o gerenciamento
        janela.add(voltar); // adiciona o botão

        janela.revalidate();
        janela.repaint();
    }

    // ------------------ Gerenciar Alternativas (Admin) ------------------
    private static void mostrarTelaGerenciarAlternativas(Questao questao) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Alternativas - Questão ID: " + questao.getId(), SwingConstants.CENTER);// enunciado 
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);//adiciona o enunciadoà tela 

        ArrayList<Alternativa> alternativas = alternativaController.listarPorQuestao(questao.getId()); // lista as alternativas associadas a essa questao, usa o controller pra isso

        int y = 110;// tem a função de a cada alternativa descer y 
        for (Alternativa a : alternativas) {
            JLabel lbl = new JLabel((a.isCorreta() ? "[CORRETA] " : "") + a.getTexto()); // mostra a alternativa colocando se ela é correta ou incorreta antes do texto da alternativa 
            lbl.setBounds(100, y, 600, 25);
            janela.add(lbl);// adiciona o texto na tela

            //--------------------------------------Botão para editar a alternativa --------------------------------//
            JButton editar = new JButton("Editar");
            editar.setBounds(710, y, 80, 25);
            editar.addActionListener(e -> mostrarTelaEditarAlternativa(questao, a)); // encaminha para a tela de editar alternativa, passando a questao e a alernativa
            janela.add(editar);// add o botao

             //--------------------------------------Botão para excluir a alternativa --------------------------------//
            JButton delet = new JButton("Excluir");
            delet.setBounds(800, y, 80, 25);
            delet.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(janela,
                        "Excluir alternativa?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION); // vai aparecer uma cixinha, perguntando se ele relamente quer excluir permanentemente a alternativa
                if (confirm == JOptionPane.YES_OPTION) { // se clicar no sim usa o contoller para delet\r do database 
                    alternativaController.deletar(a.getId());
                    mostrarTelaGerenciarAlternativas(questao); // volta para a tela de gerenciamento de alternativa, ja com a alternativa excluida
                }
            });
            janela.add(delet); // adiciona à tela

            y += 35;//incrementa para que as demais alternativas sejam colocadas a baixo da anterior
        }

        //----------------------------Adicionar Alternativa--------------------//
        JLabel labelTexto = new JLabel("Texto:");
        labelTexto.setBounds(100, y + 10, 50, 25);
        JTextField campoTexto = new JTextField(); // lugar one o admin vai digitar a nova alternativa
        campoTexto.setBounds(150, y + 10, 500, 25);
        JCheckBox chkCorreta = new JCheckBox("Correta"); // o admin marca na caixinha se a questão està ou não correta
        chkCorreta.setBounds(660, y + 10, 100, 25);

        JButton botaoAdicionar = new JButton("Adicionar Alternativa");// botão de adicinar 
        botaoAdicionar.setBounds(350, y + 50, 300, 35);
        botaoAdicionar.addActionListener(e -> {
            String texto = campoTexto.getText(); // pega a alternativa  digitada
            boolean correta = chkCorreta.isSelected(); //  verifica se a caixinha esta ou nao marcada como correta

            if (texto.trim().isEmpty()) {//verifica se nao foi passda uma alternativa vazia
                JOptionPane.showMessageDialog(janela, "Texto não pode ser vazio!");
                return;
            }

            Alternativa nova = new Alternativa(); // cria um novo objeto
            nova.setTexto(texto);
            nova.setCorreta(correta); // prenche com os valores digitados
            try {
                alternativaController.cadastrar(nova, questao.getId());// o controller cadastra a nova alternativa no banco
                mostrarTelaGerenciarAlternativas(questao); //volta para a tela de gerenciamento de alternativas(ja com a alternativa adicionada)
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Erro ao cadastrar alternativa: " + ex.getMessage());// se nao der s=certo exibe uma mensagem de errro
            }
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, y + 100, 300, 35);
        voltar.addActionListener(e -> mostrarTelaGerenciarQuestoes()); // volta para a tela de gerenciamento de qeustoes (sem a alternativa gerenciada)

        janela.add(labelTexto);
        janela.add(campoTexto);
        janela.add(chkCorreta);
        janela.add(botaoAdicionar);
        janela.add(voltar);// 527 À 531 - adiciona tudo à tela

        janela.revalidate();
        janela.repaint();
    }

    // ----------------------------------Editar Alternativas-----------------------//

    private static void mostrarTelaEditarAlternativa(Questao questao, Alternativa alt) { // a questao que a alternativa pertence
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Alternativa - Questão ID: " + questao.getId(), SwingConstants.CENTER);// vai criar um cabeçalho à tela
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 40, 600, 40);
        janela.add(titulo);// adicio o cabeçalho

        JLabel labelTexto = new JLabel("Texto:");
        labelTexto.setBounds(100, 120, 50, 25);
        JTextField campoTexto = new JTextField(alt.getTexto());// cria um texto e ja preenche com a alternativa antiga
        campoTexto.setBounds(160, 120, 500, 25);
        JCheckBox chkCorreta = new JCheckBox("Correta", alt.isCorreta()); // cria uma caixinha de check, o argumento 2 defini se é ou não a alternativa correta 
        chkCorreta.setBounds(680, 120, 100, 25);

        JButton salvar = new JButton("Salvar");
        salvar.setBounds(350, 200, 300, 40);
        salvar.addActionListener(e -> { 
            alt.setTexto(campoTexto.getText()); // vai receber o texto e atuálizá-lo
            alt.setCorreta(chkCorreta.isSelected());// lê a caixinha e atualiza 
            alternativaController.atualizar(alt.getId(), alt); // manda a alteração para o controller que vai editar no database
            mostrarTelaGerenciarAlternativas(questao);// volta para a tela de gerenciamento de alternativas
        });

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(350, 260, 300, 40);
        voltar.addActionListener(e -> mostrarTelaGerenciarAlternativas(questao)); // volta para o gerenciamento de alternativas sem salvar

        janela.add(labelTexto);
        janela.add(campoTexto);
        janela.add(chkCorreta);
        janela.add(salvar);
        janela.add(voltar); // 567 à 571 adiciona tudo `a tela

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
