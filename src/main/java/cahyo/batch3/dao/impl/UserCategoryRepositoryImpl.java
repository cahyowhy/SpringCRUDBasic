package cahyo.batch3.dao.impl;

import cahyo.batch3.dao.custom.UserCategoryRepositoryCustom;
import cahyo.batch3.entity.UserCategory;
import cahyo.batch3.util.Utilities;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserCategoryRepositoryImpl implements UserCategoryRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserCategory> findByPrefix(String name) {
        String sql = "SELECT * FROM user where user.name LIKE '" + name + "%' or " +
                "user.name LIKE '" + Utilities.capitalize(name) + "%'";
        Query query = entityManager.createNativeQuery(sql, UserCategory.class);

        return query.getResultList();
    }
}
