package dao.core;

import dao.DefaultDAO;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public interface UsuarioDAO extends DefaultDAO<Usuario> {

    Usuario login(String email, String senha);
}
