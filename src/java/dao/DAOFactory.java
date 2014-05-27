package dao;

import dao.jpa.JPADAOFactory;

/**
 *
 * @author Felipe
 */
public class DAOFactory {

    public static final Byte JPA = 1;

    public DAOFactory getDAOFactory(byte optionFactory) {
        if (optionFactory == JPA) {
            return new JPADAOFactory();
        }
        return null;
    }
}
