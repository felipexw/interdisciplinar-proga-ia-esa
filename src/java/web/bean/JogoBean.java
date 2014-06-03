package web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import model.Jogo;

/**
 *
 * @author Felipe
 */
@SessionScoped
@ManagedBean
public class JogoBean {

    private Jogo jogo;

    public JogoBean() {
        this.jogo = new Jogo();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
