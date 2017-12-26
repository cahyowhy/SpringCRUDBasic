package cahyo.batch5.dao;

import java.util.List;

public interface BaseDao<T> {
    T save(final T entity);

    T update(final T entity);

    String delete(final int id);

    List<T> findAll(final int offset, final int limit);

    T findbyId(final int id);
}
