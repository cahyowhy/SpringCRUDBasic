package cahyo.batch3.dao.custom;

import cahyo.batch3.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByPrefix(String name);
}
