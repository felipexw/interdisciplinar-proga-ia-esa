package dao;

import java.util.List;

/**
 *
 * @author Felipe
 */
public interface DefaultDAO<T> {

    void insert(T t);

    void update(T t);

    List<T> listAll();

    T find(Integer id);
    
    void remove(Integer id);
}
