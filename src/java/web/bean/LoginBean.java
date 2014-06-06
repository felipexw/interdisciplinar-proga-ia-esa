package web.bean;

import dao.DAOFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import utili.JavaMailSender;

/**
 *
 * @author Felipe
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {

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

    public Usuario getUserSession() {
        return (Usuario) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("User");
    }

    public String logout() {
        System.out.println("123123123123123");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.removeAttribute("User");
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestContext.getCurrentInstance().execute("dlgSair.hide()");
        return "index";
    }

    public void restorePassword() {
        String cpf = usuario.getCpf();
        try {
            usuario = DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().findByEmail(usuario.getEmail());
            if (usuario != null) {
                if (usuario.getCpf().equalsIgnoreCase(cpf)) {
                    String passwd = generateRandomPasswd();
                    usuario.setSenha(passwd);
                    new JavaMailSender().sendEmail(usuario.getEmail(), passwd);
                    DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().update(usuario);
                    FacesContext.getCurrentInstance().
                            addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "A nova senha foi encaminha para o e-mail.", ""));
                } else {
                    FacesContext.getCurrentInstance().
                            addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "E-mail e CPF não conferem.", ""));
                }
            } else {
                FacesContext.getCurrentInstance().
                        addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Não há usuário cadastrado com esse e-mail.", ""));
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FacesContext.getCurrentInstance().
                    addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Uma nova senha foi encaminhada para esse e-mail.", ""));
        }

    }

    private String generateRandomPasswd() {
        StringBuilder strBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                strBuilder.append(random.nextInt(10));
            } else {
                strBuilder.append(random.nextGaussian());
            }
        }
        return strBuilder.toString();
    }

}
