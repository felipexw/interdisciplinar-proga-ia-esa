package web.bean;

import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import minimax.TicTacToe;
import model.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Felipe
 */
@ManagedBean
@ViewScoped
public class JogoPVPBean extends AbstractJogoBean implements Serializable {

    private String label;
    private String player;

    public JogoPVPBean() {
        super();
        label = "'X'";
    }

    private void calcLabel() {
        label = (label.equals("'X'") ? "'O'" : "'X'");
    }

    @Override
    public void jogar(long index, Usuario user) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        String id = "'formJogo:btn_" + index + "'";

        String str = "document.getElementById(" + id + ").innerHTML = " + label;
        System.out.println("----------------------------JOGADOR" + str);

        boolean bPlayed = false;

        switch ((int) index) {
            case 0:
                if (!(bPlayed = game.movePlayer((byte) 0, (byte) 0))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 1:
                if (!(bPlayed = game.movePlayer((byte) 0, (byte) 1))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 2:
                if (!(bPlayed = game.movePlayer((byte) 0, (byte) 2))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 3:
                if (!(bPlayed = game.movePlayer((byte) 1, (byte) 0))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 4:
                if (!(bPlayed = game.movePlayer((byte) 1, (byte) 1))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 5:
                if (!(bPlayed = game.movePlayer((byte) 1, (byte) 2))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 6:
                if (!(bPlayed = game.movePlayer((byte) 2, (byte) 0))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 7:
                if (!(bPlayed = game.movePlayer((byte) 2, (byte) 1))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;

            case 8:
                if (!(bPlayed = game.movePlayer((byte) 2, (byte) 2))) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Posição inválida. Por favor, clique em outro quadrado.", ""));
                }
                break;
        }
        if (bPlayed) {
            requestContext.execute(str);
            if ((game.isWinner()) || (game.getCountTurns() == TicTacToe.TAMANHO * TicTacToe.TAMANHO)) {
                showResults();
            }
            calcLabel();
        }
    }

    public void generateRandomPlayer() {
        Random random = new Random();
        int resto = (random.nextInt() * random.nextInt() / random.nextInt()) % 2;
        if (resto == 0) {
            player = "jogador_1";
        } else {
            player = "jogador_2";
        }
    }

}
