package dao.core;

import dao.DefaultDAO;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public interface UsuarioDAO extends DefaultDAO<Usuario> {

    Usuario login(String email, String senha);

    String listarCPF(String cpf);

    Usuario findByEmail(String email);

    Usuario findByNick(String nick);    
    
}
