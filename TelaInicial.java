import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaInicial {
    public static void main(String[] args) {

        // Cria a janela principal
        JFrame janela = new JFrame("Byolia - Estudo dirigido de biologia");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.getContentPane().setBackground(Color.WHITE);

        // Mostra a tela inicial
        criarTelaInicial(janela);

        janela.setVisible(true);
    }

    // 🟩 Função que cria e mostra a tela inicial
    public static void criarTelaInicial(JFrame janela) {
        // Limpa o que tinha antes
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        // Imagem
        ImageIcon img = new ImageIcon("planaria.jpeg");
        JLabel imagem = new JLabel(img);
        imagem.setBounds(100, 30, 300, 200);
        janela.add(imagem);

        // Título
        JLabel titulo = new JLabel("Bem-vindo ao Byolia", SwingConstants.CENTER);
        titulo.setBounds(100, 250, 300, 30);
        janela.add(titulo);

        // Botão Entrar
        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(150, 300, 90, 30);
        botaoEntrar.setBackground(new Color(60, 179, 113));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFocusPainted(false);
        janela.add(botaoEntrar);

        // Botão Login
        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(260, 300, 90, 30);
        botaoLogin.setBackground(new Color(60, 179, 113));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        janela.add(botaoLogin);

        // Ação do botão Login → vai pra tela de login
        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTelaLogin(janela);
            }
        });

        janela.revalidate();
        janela.repaint();
    }

             //---------------------------Tela de login --------------------------- //
    public static void mostrarTelaLogin(JFrame janela) {
        janela.getContentPane().removeAll();
        janela.repaint();
        janela.getContentPane().setBackground(Color.WHITE);

        JLabel labelUsuario = new JLabel("Usuário:");// JLabel -> só exibe o texto
        labelUsuario.setBounds(100, 100, 80, 25);
        JTextField campoUsuario = new JTextField(); // JTextField -> permite que seja editado algo
        campoUsuario.setBounds(180, 100, 200, 25);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(100, 140, 80, 25);
        JPasswordField campoSenha = new JPasswordField(); // JPasswordField -> não deixa a senha a mostra - fica aquelas bolinhas no lugar
        campoSenha.setBounds(180, 140, 200, 25);

        JButton botaoConfirmar = new JButton("Confirmar"); // -> responsável por fazer o botão aparecer
        botaoConfirmar.setBounds(150, 190, 100, 30);
        botaoConfirmar.setBackground(new Color(60, 179, 113));
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.setFocusPainted(false);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(260, 190, 100, 30);
        botaoVoltar.setBackground(new Color(60, 179, 113));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);

        // Ação do botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (usuario.equals("nome q vai ser passado de outra classe") && senha.equals("msm coisa q o nome")) {
                    JOptionPane.showMessageDialog(janela, "Login realizado com sucesso!"); // mensagem que vai  aparecer na tela
                } else {
                    JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos.");
                }
            }
        });

        // o botão volta vai novamente para a tela de inicio 
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarTelaInicial(janela); //volta pra tela inicial
            }
        });

        // Adiciona os elementos à tela
        janela.add(labelUsuario);
        janela.add(campoUsuario);
        janela.add(labelSenha);
        janela.add(campoSenha);
        janela.add(botaoConfirmar);
        janela.add(botaoVoltar);

        janela.revalidate();
        janela.repaint();
    }
}
