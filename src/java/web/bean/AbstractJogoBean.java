package web.bean;

import minimax.TicTacToe;
import model.Jogo;
import model.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Felipe
 */
public abstract class AbstractJogoBean{

    protected Jogo jogo;
    protected TicTacToe game;

    public AbstractJogoBean() {
        this.jogo = new Jogo();
        this.game = new TicTacToe();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public TicTacToe getGame() {
        return game;
    }

    public void setGame(TicTacToe game) {
        this.game = game;
    }

    abstract public void jogar(long index, Usuario u);

    protected void showResults() {
        String str = "alert('" + game.getWinnerMessage() + "')";
        RequestContext.getCurrentInstance().execute(str);
        initGame();
    }

    protected void initGame() {
        for (byte i = 0; i < game.TAMANHO * game.TAMANHO; i++) {
            String id = "'formJogo:btn_" + i + "'";
            String str = "document.getElementById(" + id + ").innerHTML = ''";
            RequestContext.getCurrentInstance().execute(str);
        }
        game = new TicTacToe();
    }

}
