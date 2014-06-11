package web.bean;

import dao.DAOFactory;
import dao.core.UsuarioDAO;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;
import model.TipoUsuario;
import model.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import web.bean.datamodel.DefaultDataModel;
import web.validator.CPFValidator;
import web.validator.EmailValidator;

/**
 *
 * @author Felipe
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    private DefaultDataModel dataModel;
    private Usuario usuario;
    private List<Usuario> usuariosSelecionados;
    private SelectOneRadio oneRadioTipo;

    public UsuarioBean() {
        usuario = new Usuario();
        dataModel = new DefaultDataModel(getUsers());
    }

    public SelectOneRadio getOneRadioTipo() {
        return oneRadioTipo;
    }

    public void setOneRadioTipo(SelectOneRadio oneRadioTipo) {
        this.oneRadioTipo = oneRadioTipo;
    }

    public List<Usuario> getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }

    public DefaultDataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DefaultDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public List<Usuario> getUsers() {
        return DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().listAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void save() {
        UsuarioDAO userDao = DAOFactory.getDAOFactory(DAOFactory.JPA)
                .getUsuarioDAO();
        Usuario outro = userDao.findByEmail(usuario.getEmail());
        if (outro == null) {
            userDao.insert(usuario);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", ""));
        } else {
            usuario.setId(outro.getId());
            userDao.update(usuario);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário atualizado com sucesso!", ""));
        }
    }

    public void validateEmail(FacesContext ctx, UIComponent component, Object o) {
        new EmailValidator().validate(ctx, component, o);
    }

    public void validateNick(FacesContext ctg, UIComponent ui, Object o) {
        String nick = (String) o;
        if (DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().findByNick(nick) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já há um nick cadastro com esse valor. Por favor, informe outro.", ""));
        }
    }

    public void validateCPF(FacesContext ctx, UIComponent component, Object o) {
        new CPFValidator().validate(ctx, component, o);
    }

    public TipoUsuario[] tiposUsuarios() {
        return TipoUsuario.values();
    }

    public void geraRelatorio() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            InputStream stream = externalContext.getResourceAsStream("/relatorios/relatorioUsuarios.jasper");

            JasperReport report = (JasperReport) JRLoader.loadObject(stream);

            List<Usuario> usuarios = DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().listAll();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);

            JasperPrint printer = JasperFillManager.fillReport(stream, new HashMap<String, Object>(), dataSource);

            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline filename=relatorioUsuarios.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
