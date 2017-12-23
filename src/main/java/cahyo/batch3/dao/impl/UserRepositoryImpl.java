package cahyo.batch3.dao.impl;

import cahyo.batch3.dao.custom.UserRepositoryCustom;
import cahyo.batch3.entity.User;
import cahyo.batch3.util.Utilities;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findByPrefix(String name) {
        String sql = "SELECT * FROM user where user.name LIKE '"+ name + "%' or "+
                "user.name LIKE '"+ Utilities.capitalize(name) + "%'";
        Query query = entityManager.createNativeQuery(sql, User.class);

        return query.getResultList();
    }
}
