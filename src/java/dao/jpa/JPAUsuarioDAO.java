package dao.jpa;

import dao.core.UsuarioDAO;
import java.util.HashMap;
import java.util.Map;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public class JPAUsuarioDAO extends JPADAO<Usuario> implements UsuarioDAO {

    @Override
    public Usuario login(String email, String senha) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("senha", senha);
        return (Usuario) getJPAUtil().consultaNomeada("usuario.login", params);
    }

    @Override
    protected Class<Usuario> classEntity() {
        return Usuario.class;
    }

    @Override
    public String listarCPF(String cpf) {
        Map<String, Object> params = new HashMap<>();
        params.put("cpf", cpf);
        return (String) new JPAUtil<String>().consultaNomeada(cpf, params);
    }

}
