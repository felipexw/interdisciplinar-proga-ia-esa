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
import model.TipoUsuario;
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
    private String cpf;
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
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
            this.usuario = user;
//            ((ChatBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("chatBean")).addUsuario(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } else {
            FacesMessage msg = new FacesMessage("Usuário ou senha inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public Usuario getUserSession() {
        Usuario u = (Usuario) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("User");
        return (Usuario) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("User");
    }

    public String logout() {
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
    
    public boolean isAdmin() {
        return usuario != null && usuario.getTipo() == TipoUsuario.ADMINISTRADOR;
    }
}
