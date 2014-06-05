package minimax;

public class TicTacToe implements java.io.Serializable {

    private byte bestc[];
    private byte bestr[];
    private byte countTurns;
    public static final byte COMPUTER = 5;
    public static final byte COMPUTADORxHUMANO = 8;
    public static final byte COMPUTER_WIN = 3;
    public static final byte DRAW = 2;
    public static final byte EMPTY = -1;
    public static final byte HUMAN = 4;
    public static final byte HUMAN2 = 6;
    public static final byte HUMAN_WIN = 1;
    public static final byte HUMANOxHUMANO = 9;
    public static final byte NO = -7;
    private byte[][] position;
    public byte tipoJogo, playerWin;
    private byte turn;
    public static final byte TAMANHO = 3;
    public static final byte UNCLEAR = 0;
    public static final byte YES = 7;
    private boolean winner;
    private byte valueWinner;

    public TicTacToe() {
        initGame();
        bestr = new byte[1];
        bestc = new byte[1];
        winner = false;
        countTurns = 0;
    }

    public byte getCountTurns() {
        return countTurns;
    }

    public boolean isWinner() {
        return winner;
    }

    public byte getTurn() {
        return turn;
    }

    public void setTurn(byte TURN) {
        this.turn = TURN;
    }

    public void initGame(byte tipoJogo) {
        this.tipoJogo = tipoJogo;
        turn = HUMAN;

        position = new byte[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                position[i][j] = EMPTY;
            }
        }
    }

    public void initGame() {
        turn = HUMAN;
        tipoJogo = COMPUTADORxHUMANO;
        position = new byte[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                position[i][j] = EMPTY;
            }
        }
    }

    private boolean theresWinColumn() {
        byte count = 0;
        byte value = position[0][0];
        for (byte i = 0; i < position.length; i++) {
            count = 0;
            value = position[0][i];
            for (byte j = 0; j < position.length - 1; j++) {
                if ((value != EMPTY) && (value == position[j + 1][i])) {
                    count++;
                }
            }
            if (count == TAMANHO - 1) {
                valueWinner = position[0][0];
                return true;
            }
        }
        return false;
    }

    private boolean theresWinLine() {
        byte value = position[0][0];
        byte count = 0;

        for (byte i = 0; i < position.length; i++) {
            value = position[i][0];
            count = 0;
            for (byte j = 0; j < position.length - 1; j++) {
                if (position[i][j + 1] == value && value != EMPTY) {
                    count++;
                }
            }
            if (count == TAMANHO - 1) {
                valueWinner = value;
                return true;
            }
        }
        return false;
    }

    public String getWinnerMessage() {
        if (winner) {
            if (valueWinner == HUMAN) {
                return "Jogador 1 Ganhou!";
            } else if (valueWinner == HUMAN2) {
                return "Jogador 2 Ganhou!";
            } else if (valueWinner == COMPUTER) {
                return "Computador Ganhou!";
            }
        }
        return "Deu velha";

    }

    private boolean theresWinMainDiag() {
        byte value = position[0][0];
        for (byte i = 0; i < position.length - 1; i++) {
            if ((value != position[i + 1][i + 1]) || (value == EMPTY)) {
                return false;
            }
        }
        valueWinner = value;
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
        valueWinner = position[0][TAMANHO - 1];
        return true;
    }

    public void win() {
        winner = theresWinColumn();
        if (winner) {
            System.out.println("Vencedor na coluna");
        } else {
            if ((winner = theresWinLine())) {
                System.out.println("Vencedor na linha");
            } else {
                if ((winner = theresWinMainDiag())) {
                    System.out.println("Vencedor na DP!");
                } else {
                    if ((winner = theresWinSecDiag())) {
                        System.out.println("Vencedor na DS!");
                    }
                }
            }
        }
    }

    public byte[] movimentoComputador() {
        miniMax(new GameState(position), COMPUTER, HUMAN_WIN, COMPUTER_WIN, bestc, bestr);
        byte ret[] = new byte[2];
        position[bestr[0]][bestc[0]] = COMPUTER;
        countTurns++;
        win();
        turn = COMPUTER;
        ret[0] = bestr[0];
        ret[1] = bestc[0];
        return ret;
    }

    private boolean isValidPlay(byte i, byte j) {
        return position[i][j] == EMPTY;
    }

    public boolean movePlayer(byte i, byte j) {
        /**
         * *********************************************************************
         * Stores human move in array. Precondition: Call from plotMove in
         * applet. Postcondition: Human move stored and flag set for computer to
         * make move.
         * ********************************************************************
         */
        if (isValidPlay(i, j)) {
            if (tipoJogo == COMPUTADORxHUMANO) {
                position[i][j] = HUMAN;
                turn = COMPUTER;
            } else {
                if (turn == HUMAN) {
                    position[i][j] = HUMAN;
                    turn = HUMAN2;
                } else {
                    position[i][j] = HUMAN2;
                    turn = HUMAN;
                }
            }
            countTurns++;
            System.out.println(countTurns);
            win();
            return true;
        }
        return false;
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
