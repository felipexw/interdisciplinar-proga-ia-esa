package web.bean;

import java.io.Serializable;
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
public class JogoBean implements Serializable {

    private Jogo jogo;
    private UIComponent btn_0;
    private UIComponent btn_1;
    private UIComponent btn_2;
    private UIComponent btn_3;
    private UIComponent btn_4;
    private UIComponent btn_5;
    private UIComponent btn_6;
    private UIComponent btn_7;
    private UIComponent btn_8;

    public JogoBean() {
        this.jogo = new Jogo();
    }

    public UIComponent getBtn_0() {
        return btn_0;
    }

    public void setBtn_0(UIComponent btn_0) {
        this.btn_0 = btn_0;
    }

    public UIComponent getBtn_1() {
        return btn_1;
    }

    public void setBtn_1(UIComponent btn_1) {
        this.btn_1 = btn_1;
    }

    public UIComponent getBtn_2() {
        return btn_2;
    }

    public void setBtn_2(UIComponent btn_2) {
        this.btn_2 = btn_2;
    }

    public UIComponent getBtn_3() {
        return btn_3;
    }

    public void setBtn_3(UIComponent btn_3) {
        this.btn_3 = btn_3;
    }

    public UIComponent getBtn_4() {
        return btn_4;
    }

    public void setBtn_4(UIComponent btn_4) {
        this.btn_4 = btn_4;
    }

    public UIComponent getBtn_5() {
        return btn_5;
    }

    public void setBtn_5(UIComponent btn_5) {
        this.btn_5 = btn_5;
    }

    public UIComponent getBtn_6() {
        return btn_6;
    }

    public void setBtn_6(UIComponent btn_6) {
        this.btn_6 = btn_6;
    }

    public UIComponent getBtn_7() {
        return btn_7;
    }

    public void setBtn_7(UIComponent btn_7) {
        this.btn_7 = btn_7;
    }

    public UIComponent getBtn_8() {
        return btn_8;
    }

    public void setBtn_8(UIComponent btn_8) {
        this.btn_8 = btn_8;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
