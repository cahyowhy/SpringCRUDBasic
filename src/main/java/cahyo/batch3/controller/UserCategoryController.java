package cahyo.batch3.controller;

import cahyo.batch3.dao.UserCategoryRepository;
import cahyo.batch3.entity.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user-categories")
public class UserCategoryController {
    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    UserCategory save(@RequestBody UserCategory userCategory) {
        UserCategory userCategory1 = new UserCategory();
        userCategory1.setName(userCategory.getName());
        userCategory1.setUsers(userCategory.getUsers());
        userCategoryRepository.save(userCategory1);

        return userCategory1;
    }

    @GetMapping
    public @ResponseBody
    Iterable<UserCategory> getAllUserCategories() {
        return userCategoryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    UserCategory findById(@PathVariable String id) {
        return userCategoryRepository.findOne(Long.valueOf(id));
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    UserCategory putById(@PathVariable String id, @RequestBody UserCategory userCategory) {
        UserCategory userCategoryPut = new UserCategory();
        userCategoryPut.setName(userCategory.getName());
        userCategoryPut.setId(Long.valueOf(id));
        userCategoryRepository.save(userCategoryPut);

        return userCategoryPut;
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteById(@PathVariable String id) {
        userCategoryRepository.delete(Long.valueOf(id));
        return "delete success";
    }

    @GetMapping(path = "/like")
    public @ResponseBody
    List<UserCategory> findByPrefix(@RequestParam("name") String name) {
        return userCategoryRepository.findByPrefix(name);
    }
}
