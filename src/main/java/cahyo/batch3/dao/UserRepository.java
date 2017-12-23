package cahyo.batch3.dao;

import cahyo.batch3.dao.custom.UserRepositoryCustom;
import cahyo.batch3.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
}
