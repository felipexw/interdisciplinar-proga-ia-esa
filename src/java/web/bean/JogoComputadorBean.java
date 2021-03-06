package web.bean;

import java.io.Serializable;
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
@ViewScoped
@ManagedBean
public class JogoComputadorBean extends AbstractJogoBean implements Serializable {

    public JogoComputadorBean() {
        super();
    }

    @Override
    public void jogar(long index, Usuario u) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        String id = "'formJogo:btn_" + index + "'";
        String str = "document.getElementById(" + id + ").innerHTML = 'X'";
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
            } else {
                computerPlay();
            }
        }
    }

    private void computerPlay() {
        StringBuilder strBuilder = new StringBuilder("document.getElementById('formJogo:btn_");
        byte[] computer = game.movimentoComputador((byte) 5);

        if (computer[0] == 0) {
            if (computer[1] == 0) {
                strBuilder.append(0);
            } else if (computer[1] == 1) {
                strBuilder.append(1);
            } else {
                strBuilder.append(2);
            }
        } else if (computer[0] == 1) {
            if (computer[1] == 0) {
                strBuilder.append(3);
            } else if (computer[1] == 1) {
                strBuilder.append(4);
            } else {
                strBuilder.append(5);
            }
        } else if (computer[0] == 2) {
            if (computer[1] == 0) {
                strBuilder.append(6);
            } else if (computer[1] == 1) {
                strBuilder.append(7);
            } else {
                strBuilder.append(8);
            }
        }
        strBuilder.append("').innerHTML = 'O'");
        System.out.println(strBuilder);
        RequestContext.getCurrentInstance().execute(strBuilder.toString());

        if ((game.isWinner()) || (game.getCountTurns() == TicTacToe.TAMANHO * TicTacToe.TAMANHO)) {
            showResults();
        }
    }

}
