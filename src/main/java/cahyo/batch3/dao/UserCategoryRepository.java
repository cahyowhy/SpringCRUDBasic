package cahyo.batch3.dao;

import cahyo.batch3.dao.custom.UserCategoryRepositoryCustom;
import cahyo.batch3.entity.UserCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategory, Long>, UserCategoryRepositoryCustom {
}
