package dao.jpa;

import dao.DefaultDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 * @param <T>
 */
abstract class JPADAO<T extends Object> extends JPADAOFactory implements DefaultDAO<T> {

    protected abstract Class<T> classEntity();

    protected JPAUtil<T> getJPAUtil() {
        return new JPAUtil<>();
    }

    @Override
    public void insert(T t) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T t) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> listAll() {
        EntityManager em = getEntityManager();
        List<T> lista = em.createQuery("SELECT t FROM "
                + classEntity().getCanonicalName().substring(classEntity()
                        .getCanonicalName().lastIndexOf(".") + 1, classEntity()
                        .getCanonicalName().length()) + " t").getResultList();
        em.close();
        return lista;
    }

    @Override
    public T find(Integer id) {
        return getEntityManager().find(classEntity(), id);
    }

    @Override
    public void remove(Integer id) {
        EntityManager em = getEntityManager();
        try {
            T t = em.find(classEntity(), id);
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

}
