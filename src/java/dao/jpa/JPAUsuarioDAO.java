package dao.jpa;

import dao.core.UsuarioDAO;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public class JPAUsuarioDAO extends JPADAO<Usuario> implements UsuarioDAO {

    @Override
    public Usuario login(String email, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Class<Usuario> classEntity() {
        return Usuario.class;
    }

}
