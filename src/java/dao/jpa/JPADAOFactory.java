package dao.jpa;

import dao.DAOFactory;
import dao.core.MensagemDAO;
import dao.core.UsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Felipe
 */
public class JPADAOFactory extends DAOFactory {

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("inter").createEntityManager();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new JPAUsuarioDAO();
    }

    @Override
    public MensagemDAO getMensagemDAO() {
        return new JPAMensagemDAO();
    }

}
