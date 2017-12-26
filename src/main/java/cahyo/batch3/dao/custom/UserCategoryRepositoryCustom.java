package cahyo.batch3.dao.custom;

import cahyo.batch3.entity.UserCategory;

import java.util.List;

public interface UserCategoryRepositoryCustom {
    List<UserCategory> findByPrefix(String name);
}
