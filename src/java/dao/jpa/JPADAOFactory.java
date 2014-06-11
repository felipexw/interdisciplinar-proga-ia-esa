package dao.jpa;

import dao.DAOFactory;
import dao.core.UsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Felipe
 */
public class JPADAOFactory extends DAOFactory {

    protected EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("inter");
        }
        return emf.createEntityManager();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new JPAUsuarioDAO();
    }

}
