package cahyo.batch3.dao;

import cahyo.batch3.dao.custom.UserRepositoryCustom;
import cahyo.batch3.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    List<User> findByEmail(String email);
}
