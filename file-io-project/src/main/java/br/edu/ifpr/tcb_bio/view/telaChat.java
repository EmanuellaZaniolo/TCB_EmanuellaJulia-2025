package br.edu.ifpr.tcb_bio.view;

import br.edu.ifpr.tcb_bio.controller.AlternativaController;
import br.edu.ifpr.tcb_bio.controller.CadastroController;
import br.edu.ifpr.tcb_bio.controller.PQ_controller;
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
                    mostrarTelaReinos(usuarioLogado);
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
    private static void mostrarTelaAdmin() { // esssa tela so aparecerá se o tipo de usuario for admin
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Painel do Administrador", SwingConstants.CENTER);// vai aparec er um cabeçalho no topo da tela\ 
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(200, 100, 600, 50);
        janela.add(titulo);// adiciona o cabeçlho na tela 

        JButton botaoQuestoes = new JButton("Gerenciar Questões"); // botao clicável 
        botaoQuestoes.setBounds(350, 210, 300, 40);
        botaoQuestoes.addActionListener(e -> mostrarTelaGerenciarQuestoes());// se clicar aparece essa tela 
        janela.add(botaoQuestoes);// add o botão à tela 

        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");//botao clicavel
        botaoAdicionarQuestao.setBounds(350, 310, 300, 40);
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao());// se clicar aparece essa tela 
        janela.add(botaoAdicionarQuestao);// add o botão à tela 


        JButton botaoUsuarios = new JButton("Ver Usuários");//botao clicavel
        botaoUsuarios.setBounds(350, 410, 300, 40);
        botaoUsuarios.addActionListener(e -> mostrarUsuarios());
        janela.add(botaoUsuarios);// add o botão à tela 


        JButton botaoVoltar = new JButton("Sair");//botao clicavel
        botaoVoltar.setBounds(350, 510, 300, 40);
        botaoVoltar.addActionListener(e -> criarTelaInicial());// se clicar aparece essa tela 
        janela.add(botaoVoltar);// add o botão à tela 


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
        titulo.setBounds(200, 100, 600, 40);
        janela.add(titulo);

        //Botão global de adicionar questões
        JButton botaoAdicionarQuestao = new JButton("Adicionar Questão");
        botaoAdicionarQuestao.setBounds(350, 210, 300, 40);
        botaoAdicionarQuestao.addActionListener(e -> mostrarTelaAdicionarQuestao());
        janela.add(botaoAdicionarQuestao);

        JButton botaoListar= new JButton("Listar Questões");
        botaoListar.setBounds(350, 300, 300, 40);
        botaoListar.addActionListener(e -> mostrarTelaListagem());
        janela.add(botaoListar);

        JButton voltar = new JButton("Voltar"); 
        voltar.setBounds(350,390, 300, 40);
        voltar.addActionListener(e -> mostrarTelaAdmin()); // vai voltar para o "menu" do admin 
        janela.add(voltar);//adiciona à tela

        janela.revalidate();
        janela.repaint();
    }

    //-------------------------------listar-------------------//
    private static void mostrarTelaListagem() {
    janela.getContentPane().removeAll();
    janela.repaint();
    janela.setLayout(null);
    janela.setSize(1300, 900);
    janela.getContentPane().setBackground(Color.WHITE);

    JLabel titulo = new JLabel("Questões Cadastradas", SwingConstants.CENTER);// cabeçalho
    titulo.setFont(new Font("Arial", Font.BOLD, 28));//fonte e tamanho da letra
    titulo.setBounds(200, 20, 900, 50);// dimensoes
    janela.add(titulo);//add a tela

    ArrayList<Questao> lista = questaoController.listar();//vai listar todas as questpoes, para aparecerem na tela

    JPanel painelLista = new JPanel();// painel que vai expor a lista de questoes, ele é tipo um conteiner que vai ser usado prsa depois colocar os layouts 
    painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));//  os layouts serão colocados verticalmente 
    painelLista.setBackground(Color.WHITE);

    for (Questao q : lista) {
        JPanel bloco = new JPanel(); // conteiner que colocamos as questoes dentro dele
        bloco.setLayout(new BorderLayout(10, 10)); //esta colocando os componentes no bloco
        bloco.setPreferredSize(new Dimension(1000, 130));//vai definir o tamanho do bloco
        bloco.setMaximumSize(new Dimension(1100, 130)); // defini i tamanho max do paineo, isso evita que o bloco zeja maior do que a propria tela 
        bloco.setBackground(new Color(245, 245, 245)); // cor de fundo
        bloco.setBorder(BorderFactory.createLineBorder(Color.BLUE));

       
        JLabel lbl = new JLabel("ID: " + q.getId() + " - " + q.getEnunciado()); // vai pegar o enunciado da questao e o ide dela 
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bloco.add(lbl, BorderLayout.NORTH);

        
        JPanel painelBotoes = new JPanel(); // criar painel
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); // defini o layout, no centro, espaço horizontal e espaço vertical
        painelBotoes.setBackground(new Color(245, 245, 245));//cor de fundo do painel

        JButton alternativas = new JButton("Alternativas"); // vai acessar a alternativa caso queira edita-la
        alternativas.addActionListener(e -> mostrarTelaGerenciarAlternativas(q));//encaminha para a tela de gerenciar as alternativas

        JButton editar = new JButton("Editar");// vai editar a quyestaop
        editar.addActionListener(e -> editarQuestao(q));//mostarr a tela de edicao

        JButton excluir = new JButton("Excluir");//
        excluir.addActionListener(e -> {// vai aparecer uma caixinha de perguinta pra saber se relamente quer apagar
            int confirm = JOptionPane.showConfirmDialog(
                janela,
                "Deseja realmente excluir a questão com ID : " + q.getId() + "?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION // cria os botoezinhos
            );
            if (confirm == JOptionPane.YES_OPTION) { // se a confirmacao for verdadeira deeta
                questaoController.deletar(q.getId());
                mostrarTelaListagem(); // mostra novamente a tela de lista
            }
        });

        painelBotoes.add(alternativas);
        painelBotoes.add(editar);
        painelBotoes.add(excluir);

        bloco.add(painelBotoes, BorderLayout.SOUTH);

        painelLista.add(Box.createVerticalStrut(10));
        painelLista.add(bloco);
    }

    JScrollPane scroll = new JScrollPane(painelLista);// vai permitir que a tela rode
    scroll.setBounds(50, 100, 1200, 700);// defini i tamanho da gbarrinha
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    janela.add(scroll);

    janela.revalidate();
    janela.repaint();
}


    // ------------------ Adicionar Questão (Admin) ------------------
    private static void mostrarTelaAdicionarQuestao() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Adicionar Nova Questão", SwingConstants.CENTER); // enunciado da tela
        titulo.setFont(new Font("Arial", Font.BOLD, 22));//fonte e tamahnho
        titulo.setBounds(200, 40, 600, 40);//seta
        janela.add(titulo);//add na tela

        JLabel labelEnunciado = new JLabel("Enunciado:"); 
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

        //----------------------------Adicionar Alternativa(ADMIN)--------------------//
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

    // ----------------------------------Editar Alternativas(ADMIN)-----------------------//

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

    //---------------------------------mostrar usuarios-------------------------------//
    private static void mostrarUsuarios() {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.setSize(1300, 900);
        janela.getContentPane().setBackground(Color.WHITE);

    JLabel titulo = new JLabel("Perfis Cadastrados", SwingConstants.CENTER); // cabecalho da tela 
    titulo.setFont(new Font("Arial", Font.BOLD, 28));//tamnhho e fonte
    titulo.setBounds(200, 20, 900, 50);//seta o tamanho
    janela.add(titulo);//add à tela

    ArrayList<Perfil> perfis = null; 

    try {
        perfis = perfilController.listar(); //vai tentar pegar tpodos os perfis
    } catch (Exception e) {//se der erro cai aqui e mostra o erro que deu 
        e.printStackTrace(); 
        JLabel erro = new JLabel("ERRO ao carregar perfis: " + e.getMessage(), SwingConstants.CENTER);
        erro.setFont(new Font("Arial", Font.BOLD, 20));
        erro.setBounds(200, 200, 900, 50);
        janela.add(erro);
    }

    if (perfis == null || perfis.isEmpty()) {
        JLabel aviso = new JLabel("Nenhum perfil encontrado", SwingConstants.CENTER);
        aviso.setFont(new Font("Arial", Font.BOLD, 24));
        aviso.setBounds(200, 100, 900, 40);
        janela.add(aviso); // se a lista estuiver vazia vai avisar o admin com o aviso 
    } else {
        JPanel painelLista = new JPanel();// cria o painel onmde aparecderá os perfis
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(Color.WHITE);// a cor do painel

        for (Perfil p : perfis) {
            JPanel bloco = new JPanel(new BorderLayout(10, 10)); //  cria o painel
            bloco.setPreferredSize(new Dimension(1000, 60)); // vai setar a dimensao do painel 
            bloco.setMaximumSize(new Dimension(1100, 60)); // para o painel nao ficar maior que a tela
            bloco.setBackground(new Color(245, 245, 245)); // cor do painel 
            bloco.setBorder(BorderFactory.createLineBorder(Color.white)); 

            String nome = ""; // vai comecar nulo primeiro 
            try {
                nome = p.getCadastro().getNomePessoa(); // pega o id e o nome do usuario paraaparecer na tela
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(janela, "Erro ao cadastrar alternativa: " + e.getMessage());// se nao der s=certo exibe uma mensagem de errro


            }

            JLabel lbl = new JLabel("ID: " + p.getId() + " | " + nome + " | " + p.getTotalAcertos() + " acertos");// vai aparecer em cada layout
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // cria um espaco vazio em volta do texto
            bloco.add(lbl, BorderLayout.CENTER);

            painelLista.add(Box.createVerticalStrut(10)); // adiciona um espaço vertical 
            painelLista.add(bloco);
        }

        JScrollPane scroll = new JScrollPane(painelLista);// add um painel rolável 
        scroll.setBounds(50, 100, 1200, 700); // seta o tamanho
        janela.add(scroll);//add 
    }

    JButton voltar = new JButton("Voltar"); // botao de voltar
    voltar.setBounds(500, 800, 300, 40);
    voltar.addActionListener(e -> mostrarTelaAdmin());//volta para a tela de menu
    janela.add(voltar);

    janela.revalidate();
    janela.repaint();
}


    // ------------------ Tela Aluno ------------------//
    // esse da formatado certo 3e comentado
    private static void mostrarTelaReinos(Perfil usuario) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Escolha um Reino", SwingConstants.CENTER);// cabeçalho da tela
        Font novaFonte = new Font("Arial", Font.BOLD, 25);
        titulo.setFont(novaFonte);
        titulo.setBounds(200, 40, 600, 40);
        
        janela.add(titulo);

        ArrayList<Reino> reinos; // lista todos os reinos
        try {
            reinos = reinoController.listar();
        } catch (Exception e) {
            reinos = criarReinosExemplo();// serve como plano b
        }

        int y = 100;
        for (Reino reino : reinos) {
            JButton botao = new JButton(reino.getNomeReino());// pra cada reino tem um botao clicavel
            botao.setBounds(400, y, 200, 30);
            botao.addActionListener(e -> mostrarTelaQuestoes(reino));//mostra a tela de questoes
            janela.add(botao);//adicionar os botões
            y += 40; // para q os prox. fiquem um em baixo do outro
        }
        JButton botaoPerfil = new JButton("Perfil"); // botao clicavel de perfil
        botaoPerfil.setBounds(350, 460, 300, 40);
        botaoPerfil.addActionListener(e -> mostrarTelaPerfil(usuario));// mostra a tela de perfil
        janela.add(botaoPerfil);// add na tela


        JButton botaoVoltar = new JButton("Sair");//botao clicavel
        botaoVoltar.setBounds(350, 510, 300, 40);
        botaoVoltar.addActionListener(e -> criarTelaInicial());// se clicar aparece essa tela 
        janela.add(botaoVoltar);// add o botão à tela 


        janela.revalidate();
        janela.repaint();
    }

    //------------------------------tela de questoes------------------------------------//
    //formatado certo e comentado

      // método principal que inicia a sequência de questões
    private static void mostrarTelaQuestoes(Reino reino) {
    // pega todas as questoes do reino que o aluno escolheu
    ArrayList<Questao> questoes = questaoController.buscarPorReino(reino.getId());

    // se nao tiver nenhuma questao no banco avisa o aluno e volta para a tela de reinos
    if (questoes.isEmpty()) {
        JOptionPane.showMessageDialog(janela, "Nenhuma questão cadastrada para este reino.");
        mostrarTelaReinos(usuarioLogado);// volta para a tela de reinos
        return;
    }

    // chama a versão que mostra a questão por índice, iniciando na primeira
    mostrarQuestaoPorIndice(reino, questoes, 0);
}

// nova versão que mostra cada questão individualmente, baseada no índice
private static void mostrarQuestaoPorIndice(Reino reino, ArrayList<Questao> questoes, int indice) {
    janela.getContentPane().removeAll();
    janela.repaint();
    janela.setLayout(null);
    janela.getContentPane().setBackground(Color.WHITE);

    // pega a questão atual da lista
    Questao q = questoes.get(indice);

    // pega as alternativas da questao
    ArrayList<Alternativa> alternativas = alternativaController.listarPorQuestao(q.getId());
    q.setAlternativas(alternativas); // associa as alternativas à questão

    // cria um campo de texto para colocar o enunciado da questao
    JLabel titulo = new JLabel(q.getEnunciado(), SwingConstants.CENTER);// mostra a questão
    titulo.setBounds(100, 250, 800, 30);// seta a posicao que vai aparecer
    janela.add(titulo);

    // cria botões para cada alternativa
    int y = 300; // posição vertical inicial
    for (Alternativa alt : alternativas) { // usamos para aparecer todas as alternativas na tela 
        JButton botao = new JButton(alt.getTexto()); // cria um botao clicavel para todas as alternativas 
        botao.setBounds(100, y, 800, 30);//seta os botoes 

        // Ação quando o usuário clica na alternativa
        botao.addActionListener(e -> {
            int idQuestao=q.getId();
            int idPerfil=usuarioLogado.getId();
            PQ_controller pq_controller = new PQ_controller();
            pq_controller.registrarResposta(idPerfil, idQuestao);
            if (alt.isCorreta()) {
    usuarioLogado.adicionarAcerto();

    try {
        Perfil p = perfilController.buscarPorCadastroId(usuarioLogado.getCadastro().getId());
        if (p != null) {
            p.setTotalAcertos(usuarioLogado.getTotalAcertos());
            perfilController.atualizar(p.getId(), p);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    JOptionPane.showMessageDialog(janela, "Correto!");
}// exibe essa mensagem se deu certo
             else { // se estiver errada
                JOptionPane.showMessageDialog(janela, "Errado!");//exibe essa mensagem se deu errado
            }

            // verifica se tem próxima questão
            if (indice + 1 < questoes.size()) { // colocamos um indice aqui pq antes o nosso programa apresntava uma questao e voltava para a tela de reinos 
                mostrarQuestaoPorIndice(reino, questoes, indice + 1); // mostra a próxima questão
            } else {
                mostrarTelaReinos(usuarioLogado); // se acabou, volta para a tela de reinos
            }
        });

        janela.add(botao);
        y += 40; // aumenta a posição vertical para o próximo botão
    }

    janela.revalidate();
    janela.repaint();
}


    // ------------------ Tela Perfil ------------------//
    // esse ta formato certo e comentado 
    private static void mostrarTelaPerfil(Perfil usu) {// passei o usuario pq antes estvaa dando erro no usuario que estava sendo conytabilizado os acertos
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        String nomeUsuario = (usu != null && usu.getCadastro() != null)
                ? usu.getCadastro().getNomeUsuario() : "Usuário";

        JLabel titulo = new JLabel("Perfil de " + nomeUsuario, SwingConstants.CENTER); // cabeçalho da tela 
        Font fonte=  new Font("Arial", Font.BOLD, 25);
        titulo.setFont(fonte);
        titulo.setBounds(350, 50, 300, 30);

        janela.add(titulo);//add o cabeçalho

        int acertos = (usu != null) ? usu.getTotalAcertos() : 0; // pega os acertos do perfil passado se for nulo (nunca vai ser o caso) vai assumir como 0 acertos
        JLabel labelAcertos = new JLabel("Acertos: " + acertos, SwingConstants.CENTER); //aparece na tela o total de acertos 
       
        Font fonte2=  new Font("Arial", Font.BOLD, 25);
        titulo.setFont(fonte2);
        labelAcertos.setBounds(350, 100, 300, 30);
        janela.add(labelAcertos);//add na tela 

        JButton botaoVoltar = new JButton("Voltar");//botao clicável
        botaoVoltar.setBounds(450, 150, 100, 30);
        botaoVoltar.addActionListener(e -> mostrarTelaReinos(usu));//vai voltar para a tela de reinos
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
