package minimax;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GradeJogo extends JPanel {

    private static final long serialVersionUID = -6311154071833953217L;

    private JButton jbut[][];

    private Font fonte;

    private TicTacToe game;

    private byte tipoJogoEscolhido;
    private static final byte TAMANHO = 3;

    public GradeJogo() {
        game = new TicTacToe();
        setup();
    }

    private void setup() {
        setLayout(new GridLayout(TAMANHO, TAMANHO, 5, 5));
        setFonte("Arial", Font.BOLD, 80);

        jbut = new JButton[TAMANHO][TAMANHO];
        for (byte i = 0; i < TAMANHO; i++) {
            for (byte j = 0; j < TAMANHO; j++) {
                jbut[i][j] = new JButton();
                jbut[i][j].setFont(getFonte());
                jbut[i][j].addActionListener(new ControlaJogo());
                add(jbut[i][j]);
            }
        }

        Object[] tiposJogo = {"Computador Versus Humano",
            "Humano Versus Humano"};

        Object escolha = JOptionPane.showInputDialog(null,
                "Escolha a Forma do Jogo", "Jogo da Velha",
                JOptionPane.INFORMATION_MESSAGE, null, tiposJogo, tiposJogo[0]);

        if (escolha != null) {
            if (escolha.equals(tiposJogo[0])) {
                tipoJogoEscolhido = game.COMPUTADORxHUMANO;
            } else {
                tipoJogoEscolhido = game.HUMANOxHUMANO;
            }
        }
        this.init();
    }

    public void init() {
        game.initGame(tipoJogoEscolhido);
        if (game.getTurn() == game.COMPUTER) {
            JOptionPane.showMessageDialog(null, "Computador Come�a!",
                    "Jogo da Velha", JOptionPane.INFORMATION_MESSAGE);
            byte[] ret = game.movimentoCompudador();
            jbut[ret[0]][ret[1]].setText("O");
        } else if (game.getTurn() == game.HUMAN) {
            JOptionPane.showMessageDialog(null, "Jogador1 Come�a!",
                    "Jogo da Velha", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Jogador2 Come�a!",
                    "Jogo da Velha", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Font getFonte() {
        return fonte;
    }

    public void setFonte(String font, int style, int size) {
        this.fonte = new Font(font, style, size);
    }

    private class ControlaJogo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            byte ret[];
            if (e.getSource() == jbut[0][0]) {
                if (game.getPosition()[0][0] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 0, (byte) 0);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[0][0].setText("X");
                        } else {
                            jbut[0][0].setText("O");
                        }
                    } else {
                        jbut[0][0].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[0][1]) {
                if (game.getPosition()[0][1] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 0, (byte) 1);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[0][1].setText("X");
                        } else {
                            jbut[0][1].setText("O");
                        }
                    } else {
                        jbut[0][1].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[0][2]) {
                if (game.getPosition()[0][2] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 0, (byte) 2);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[0][2].setText("X");
                        } else {
                            jbut[0][2].setText("O");
                        }
                    } else {
                        jbut[0][2].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[1][0]) {
                if (game.getPosition()[1][0] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 1, (byte) 0);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[1][0].setText("X");
                        } else {
                            jbut[1][0].setText("O");
                        }
                    } else {
                        jbut[1][0].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[1][1]) {
                if (game.getPosition()[1][1] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 1, (byte) 1);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[1][1].setText("X");
                        } else {
                            jbut[1][1].setText("O");
                        }
                    } else {
                        jbut[1][1].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[1][2]) {
                if (game.getPosition()[1][2] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 1, (byte) 2);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[1][2].setText("X");
                        } else {
                            jbut[1][2].setText("O");
                        }
                    } else {
                        jbut[1][2].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[2][0]) {
                if (game.getPosition()[2][0] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 2, (byte) 0);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[2][0].setText("X");
                        } else {
                            jbut[2][0].setText("O");
                        }
                    } else {
                        jbut[2][0].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else if (e.getSource() == jbut[2][1]) {
                if (game.getPosition()[2][1] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 2, (byte) 1);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[2][1].setText("X");
                        } else {
                            jbut[2][1].setText("O");
                        }
                    } else {
                        jbut[2][1].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            } else {
                if (game.getPosition()[2][2] != game.EMPTY) {
                    JOptionPane.showMessageDialog(null, "Jogada Invalida!",
                            "Jogo da Velha", JOptionPane.ERROR_MESSAGE);
                } else {
                    game.setMovimentoJogador((byte) 2, (byte) 2);
                    if (tipoJogoEscolhido == game.HUMANOxHUMANO) {
                        if (game.getTurn() == game.HUMAN) {
                            jbut[2][2].setText("X");
                        } else if (game.getTurn() == game.HUMAN2) {
                            jbut[2][2].setText("O");
                        } else {
                            jbut[2][2].setText("X");
                            ret = game.movimentoCompudador();
                            jbut[ret[0]][ret[1]].setText("O");
                        }
                    } else {
                        jbut[2][2].setText("X");
                        ret = game.movimentoCompudador();
                        jbut[ret[0]][ret[1]].setText("O");
                    }
                }
            }

            if ((game.isWinner()) || (game.getCountTurns() == (TAMANHO * TAMANHO) - 1)) {
                JOptionPane.showMessageDialog(null, game.getWinner(),
                        "Jogo da Velha", JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.showConfirmDialog(null, "Deseja Jogar Novamente?",
                        "Jogo da Velha", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    limpaTela();
                    game = new TicTacToe();
                    init();
                }
            }
        }
    }

    public void limpaTela() {
        for (int i = 0; i < jbut.length; i++) {
            for (int j = 0; j < 3; j++) {
                jbut[i][j].setText("");
            }
        }
    }
}
