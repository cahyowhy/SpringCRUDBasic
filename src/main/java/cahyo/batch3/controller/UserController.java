package cahyo.batch3.controller;

import cahyo.batch3.dao.UserRepository;
import cahyo.batch3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    User save(@RequestBody User user) {
        User userSave = new User();
        userSave.setName(user.getName());
        userSave.setEmail(user.getEmail());
        userSave.setUserCategory(user.getUserCategory());
        userRepository.save(userSave);

        return userSave;
    }

    @GetMapping
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    User findById(@PathVariable String id) {
        return userRepository.findOne(Long.valueOf(id));
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    User putById(@PathVariable String id, @RequestBody User user) {
        User userPut = new User();
        userPut.setId(Long.valueOf(id));
        userPut.setName(user.getName());
        userPut.setEmail(user.getEmail());
        userPut.setUserCategory(user.getUserCategory());
        userRepository.save(userPut);

        return userPut;
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteById(@PathVariable String id) {
        userRepository.delete(Long.valueOf(id));
        return "delete success";
    }

    @GetMapping(path = "/like")
    public @ResponseBody
    List<User> findByPrefix(@RequestParam("name") String name) {
        return userRepository.findByPrefix(name);
    }
}
