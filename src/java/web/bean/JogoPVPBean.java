package web.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Felipe
 */
@ManagedBean
@ViewScoped
public class JogoPVPBean extends AbstractJogoBean implements Serializable {

    private Boolean jogador_1;
    private Boolean jogador_2;

    public JogoPVPBean() {
        super();
    }

    @Override
    public void jogar(long index, Usuario user) {
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
        }
        requestContext.execute(str);
    }

    public Boolean isJogador_1() {
        return jogador_1;
    }

    public void setJogador_1(Boolean jogador_1) {
        this.jogador_1 = jogador_1;
    }

    public Boolean isJogador_2() {
        return jogador_2;
    }

    public void setJogador_2(Boolean jogador_2) {
        this.jogador_2 = jogador_2;
    }

}
