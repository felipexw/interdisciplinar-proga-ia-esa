package web.bean;

import dao.DAOFactory;
import dao.core.UsuarioDAO;
import java.io.Serializable;
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

/**
 *
 * @author Felipe
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void save() {
        UsuarioDAO dao = DAOFactory.getDAOFactory(DAOFactory.JPA)
                .getUsuarioDAO();
        dao.update(usuario);
    }

    public void validateEmail(FacesContext ctx, UIComponent component, Object o) {
        String cpf = (String) o;

        if (cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("00000000000")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {
            throw new ValidatorException(new FacesMessage("CPF inválido. Por favor, informe outro valor."));
        } else {
            if (DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().listarCPF(cpf) != null) {
                throw new ValidatorException(new FacesMessage("Este CPF já está cadastrado. Por favor, informe outro valor."));
            }
        }
    }

    public List<Sexo> getEnumsSexo() {
        return Arrays.asList(Sexo.values());
    }

}
