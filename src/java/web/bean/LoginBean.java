package web.bean;

import dao.DAOFactory;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author Felipe
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable{

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginBean() {
        usuario = new Usuario();
    }

    public void login() throws IOException {
        Usuario user = DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().login(usuario.getEmail(), usuario.getSenha());
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user != null) {
            session.setAttribute("User", user);
            //Redirecionar para a página de jogo
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
            System.out.println("Enmtroou");
        } else {
            FacesMessage msg = new FacesMessage("Usuário ou senha inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Usuario getUsuarioSessao() {
        return (Usuario) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("User");
    }

    public String logout() throws Exception {
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssss");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("User");
        session.invalidate();
        return "index";
    }
}
