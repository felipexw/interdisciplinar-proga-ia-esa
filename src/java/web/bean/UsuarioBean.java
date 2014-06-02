package web.bean;

import dao.DAOFactory;
import dao.core.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.Sexo;
import model.Usuario;
import web.bean.datamodel.DefaultDataModel;
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

    public UsuarioBean() {
        usuario = new Usuario();
        dataModel = new DefaultDataModel(getUsers());
        usuariosSelecionados = new ArrayList<>();
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

    public void validateCPF(FacesContext ctx, UIComponent component, Object o) {
        String cpf = (String) o;
        if (cpf.equals("111.111.111-11") || cpf.equals("222.222.222-22")
                || cpf.equals("000.000.000-00")
                || cpf.equals("333.333.333-33")
                || cpf.equals("444.444.444-44")
                || cpf.equals("555.555.555-55")
                || cpf.equals("666.666.666-66")
                || cpf.equals("777.777.777-77")
                || cpf.equals("888.888.888-88")
                || cpf.equals("999.999.999-99")) {
            throw new ValidatorException(new FacesMessage("CPF inválido. Por favor, informe outro valor."));
        } else {
            if (DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().listarCPF(cpf) != null) {
                throw new ValidatorException(new FacesMessage("Este CPF já está cadastrado. Por favor, informe outro valor."));
            }
        }
    }

    public List<Sexo> getEnumsSexo() {
        List items = new ArrayList<Sexo>();
        items.addAll(Arrays.asList(Sexo.values()));
        return items;
    }

}
