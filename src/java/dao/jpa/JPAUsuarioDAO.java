package dao.jpa;

import dao.core.UsuarioDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public class JPAUsuarioDAO extends JPADAO<Usuario> implements UsuarioDAO {

    @Override
    public List<Usuario> listAll() {
        EntityManager em = getEntityManager();
        List<Usuario> lista = em.createQuery("SELECT u FROM Usuario u ORDER BY u.qtdVitorias DESC").getResultList();
        em.close();
        return lista;
    }

    @Override
    public Usuario findByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return (Usuario) getJPAUtil().consultaNomeada("usuario.findByEmail", params);
    }

    @Override
    public Usuario login(String email, String senha) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("senha", senha);
        return (Usuario) getJPAUtil().consultaNomeada("usuario.login", params);
    }

    @Override
    public Usuario findByNick(String nick) {
        Map<String, Object> params = new HashMap<>();
        params.put("nick", nick);
        return (Usuario) getJPAUtil().consultaNomeada("usuario.findByNick", params);
    }

    @Override
    protected Class<Usuario> classEntity() {
        return Usuario.class;
    }

    @Override
    public String listarCPF(String cpf) {
        Map<String, Object> params = new HashMap<>();
        params.put("cpf", cpf);
        return (String) new JPAUtil<String>().consultaNomeada("usuario.listarcpf", params);
    }

}
