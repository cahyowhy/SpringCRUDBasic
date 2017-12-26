package cahyo.batch3.dummy;

import cahyo.batch3.entity.User;
import cahyo.batch3.entity.UserCategory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private static class DummyDataHolder {

        private static final DummyData INSTANCE = new DummyData();
    }

    public static DummyData getInstance() {
        return DummyDataHolder.INSTANCE;
    }

    public User generateUserData() {
        User user = new User();
        user.setEmail("random@mail.com");
        user.setName("mark");
        Long id = Long.valueOf("1");
        user.setUserCategory(new UserCategory(id));

        return user;
    }

    public UserCategory generateUserCategoryData() {
        UserCategory userCategory = new UserCategory();
        userCategory.setName("randomUser");
        List<User> users = new ArrayList<User>();
        Long id = Long.valueOf("1");

        users.add(new User(id, "mark", "random@mail.com"));
        userCategory.setUsers(users);

        return userCategory;
    }

    /*public static void main(String[] args) {
        User user = getInstance().generateUserData();
        String payload = new Gson().toJson(user);
        System.out.println(user);
        System.out.println(payload);
    }*/
}
