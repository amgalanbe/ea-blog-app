package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.dto.UserDto;
import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @PostMapping("")
    public Object save(@RequestBody User user) {
        Long savedUserId = userService.save(user);
        return savedUserId != null ? new RedirectView("users/" + savedUserId)
                : ResponseEntity.accepted().body("Failed to create an user. User with this username already exists");
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody User user) {

        if(userService.findById(id) == null)
            return ResponseEntity.accepted().body("user with Id: " + id + " does not exist.");

        user.setId(id);
        userService.update(user);

        return ResponseEntity.accepted().body("user has successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if(userService.findById(id) == null)
            return ResponseEntity.accepted().body("user with Id: " + id + " does not exist.");

        userService.deleteById(id);

        return ResponseEntity.accepted().body("user has successfully deleted");
    }
}
