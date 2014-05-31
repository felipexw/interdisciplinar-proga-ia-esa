package minimax;

import java.util.Random;
import javax.swing.JOptionPane;

public class TicTacToe {

    private byte bestc[];
    private byte bestr[];
    public static final byte COMPUTER = 5;
    public static final byte COMPUTADORxHUMANO = 8;
    public static final byte COMPUTER_WIN = 3;
    private byte count;
    public static byte DIRWIN;
    public static final byte DRAW = 2;
    public static final byte EMPTY = -1;
    public static byte GAMEOVER;
    public static final byte HUMAN = 4;
    public static final byte HUMAN2 = 6;
    public static final byte HUMAN_WIN = 1;
    public static final byte HUMANOxHUMANO = 9;
    private byte MOVES;
    public static final byte NO = -7;
    private byte[][] position;
    private Random random;
    public byte tipoJogo, playerWin;
    private byte turn;
    public static final byte TAMANHO = 3;
    public static final byte UNCLEAR = 0;
    public static final byte YES = 7;
    private boolean firstPlay;

    public TicTacToe() {
        bestr = new byte[1];
        bestc = new byte[1];
        count = 0;
        firstPlay = true;
    }

    public byte getTurn() {
        return turn;
    }

    public void setTurn(byte TURN) {
        this.turn = TURN;
    }

    public void initGame(byte tipoJogo) {
        /**
         * *********************************************************************
         * Inicializa as principais variaveis usadas no jogo.
         * ********************************************************************
         */
        MOVES = 0;
        GAMEOVER = NO;
        DIRWIN = 8; //winning directions 0-7, 8 is not a winning direction
        random = new Random();
        this.tipoJogo = tipoJogo;
        if (tipoJogo == COMPUTADORxHUMANO) {
            turn = (Math.abs(random.nextInt()) % 2 == 1) ? HUMAN : COMPUTER;
        } else {
            turn = (Math.abs(random.nextInt()) % 2 == 1) ? HUMAN : HUMAN2;
        }
        position = new byte[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                position[i][j] = EMPTY;
            }
        }
    }

    private boolean theresWinColumn() {
        byte value = position[0][0];
        byte count = 0;

        for (byte i = 0; i < position.length; i++) {
            count = 0;
            for (byte j = 0; j < position.length - 1; j++) {
                if ((value != EMPTY) && (value == position[j + 1][i])) {
                    count++;
                }
            }
            if (count == TAMANHO - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean theresWinLine() {
        byte value = position[0][0];
        byte count = 0;

        for (byte i = 0; i < position.length; i++) {
            count = 0;
            for (byte j = 0; j < position.length - 1; j++) {
                if (position[i][j + 1] == value && value != EMPTY) {
                    count++;
                }
            }
            if (count == TAMANHO - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean theresWinMainDiag() {
        byte value = position[0][0];

        for (byte i = 0; i < position.length - 1; i++) {
            if ((value != position[i + 1][i + 1]) || (value == EMPTY)) {
                return false;
            }
        }
        return true;
    }

    private boolean theresWinSecDiag() {
        byte aux = TAMANHO - 1;
        for (byte i = 0; i < TAMANHO - 1; i++) {
            if ((position[i][aux] != position[i + 1][aux - 1]) || (position[i][aux] == EMPTY)) {
                return false;
            }
            aux--;
        }
        return true;
    }

    public void win() {
        boolean winner = theresWinColumn();
        if (winner) {
            JOptionPane.showMessageDialog(null, "Vencedor na coluna");
        } else {
            if ((winner = theresWinLine())) {
                JOptionPane.showMessageDialog(null, "Vencedor na linha");
            } else {
                if ((winner = theresWinMainDiag())) {
                    JOptionPane.showMessageDialog(null, "Vencedor na DP!");
                } else {
                    if ((winner = theresWinSecDiag())) {
                        JOptionPane.showMessageDialog(null, "Vencedor na DS!");
                    }
                }
            }

        }
        if (DIRWIN != 8) {
            GAMEOVER = YES;
        }

        MOVES++;
        if (MOVES == 9) {
            GAMEOVER = YES;
        }

    }

    public byte[] movimentoCompudador() {
        miniMax(new GameState(position), COMPUTER, HUMAN_WIN, COMPUTER_WIN, bestc, bestr);
        byte ret[] = new byte[2];
        position[bestr[0]][bestc[0]] = COMPUTER;
        win();
        turn = COMPUTER;
        ret[0] = bestr[0];
        ret[1] = bestc[0];
        return ret;
    }

    public void setMovimentoJogador(byte x, byte y) {
        /**
         * *********************************************************************
         * Stores human move in array. Precondition: Call from plotMove in
         * applet. Postcondition: Human move stored and flag set for computer to
         * make move.
         * ********************************************************************
         */
        if (position[x][y] == EMPTY) {
            if (tipoJogo == COMPUTADORxHUMANO) {
                position[x][y] = HUMAN;
                win();
                turn = COMPUTER;
            } else {
                if (turn == HUMAN) {
                    position[x][y] = HUMAN;
                    win();
                    turn = HUMAN2;
                } else {
                    position[x][y] = HUMAN2;
                    win();
                    turn = HUMAN;
                }
            }
        }
    }

    public byte evaluate(GameState gs) {
        /**
         * *********************************************************************
         * Retorna o valor de um possivel movimento. Chamado pelo metodo
         * miniMax. Retorna um valor que indica um dos possiveis movimentos:
         * humano ganha, computador ganha, jogo deu velha ou jogo ainda nao
         * acabou
         * ********************************************************************
         */
        playerWin = UNCLEAR;
        for (byte i = 0; i < TAMANHO; i++) {
            if (temGanhador(gs.getPositionValue(i, (byte) 0), gs
                    .getPositionValue(i, (byte) 1), gs.getPositionValue(i, (byte) 2))) {//vertical
                // win
                playerWin = retornaGanhador(gs.getPositionValue(i, (byte) 0));
            } else if (temGanhador(gs.getPositionValue((byte) 0, (byte) i), gs
                    .getPositionValue((byte) 1, (byte) i), gs.getPositionValue((byte) 2, (byte) i))) {
                // win
                playerWin = retornaGanhador(gs.getPositionValue((byte) 0, (byte) i));
            } else if (temGanhador(gs.getPositionValue((byte) 0, (byte) 0), gs
                    .getPositionValue((byte) 1, (byte) 1), gs.getPositionValue((byte) 2, (byte) 2))) {//diagonal
                // win
                playerWin = retornaGanhador(gs.getPositionValue((byte) 0, (byte) 0));
            } else if (temGanhador(gs.getPositionValue((byte) 0, (byte) 2), gs
                    .getPositionValue((byte) 1, (byte) 1), gs.getPositionValue((byte) 2, (byte) 0))) {//diagonal
                // win
                playerWin = retornaGanhador(gs.getPositionValue((byte) 0, (byte) 2));
            }
        }
        if (playerWin == UNCLEAR) {
            for (byte i = 0; i < TAMANHO; i++) {
                for (byte j = 0; j < TAMANHO; j++) {
                    if (gs.getPositionValue(i, j) == EMPTY) {
                        return UNCLEAR;
                    }
                }
            }
            return DRAW; //Nao ha ganhador, deu Velha
        }
        return playerWin;
    }

    public byte retornaGanhador(byte playerWin) {
        byte retPlayerWin = 0;

        if (playerWin == COMPUTER) {
            retPlayerWin = COMPUTER_WIN;
        } else if (playerWin == HUMAN) {
            retPlayerWin = HUMAN_WIN;
        } else if (playerWin == HUMAN2) {
            retPlayerWin = HUMAN_WIN;
        }
        return retPlayerWin;
    }

    public boolean temGanhador(byte posValue1, byte posValue2, byte posValue3) {
        return ((posValue1 == posValue2) && (posValue2 == posValue3) && (posValue1 != EMPTY));
    }

    public byte miniMax(GameState gs, byte side, byte alpha, byte beta,
            byte bestc[], byte bestr[]) {
        byte opp = 0;
        byte reply = 0;
        byte value = 0;
        byte[] dc = new byte[1];

        if (evaluate(gs) != UNCLEAR) {
            return evaluate(gs);
        }
        if (side == COMPUTER) {
            opp = HUMAN;
            value = alpha;
        } else {
            opp = COMPUTER;
            value = beta;
        }

        for (byte row = 0; row < TAMANHO; row++) {
            for (byte col = 0; col < TAMANHO; col++) {
                if (gs.getPositionValue(row, col) == EMPTY) {
                    gs.setPositionValue(row, col, side);

                    reply = miniMax(gs, opp, alpha, beta, dc, dc);
                    gs.setPositionValue(row, col, EMPTY);

                    if (((side == COMPUTER) && (reply > value))
                            || ((side == HUMAN) && (reply < value))) {
                        if (side == COMPUTER) {
                            alpha = reply;
                            value = reply;
                        } else {
                            beta = reply;
                            value = reply;
                        }
                        bestr[0] = row;
                        bestc[0] = col;
                        if (alpha >= beta) {
                            return value;
                        }
                    }
                }
            }
        }
        return value;
    }

    public byte[][] getPosition() {
        return position;
    }

}
