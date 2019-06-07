package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.User;
import mum.edu.swe.trailerrentalserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
//@CrossOrigin(origins = "*", maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value ="/users")
    public List<User> getAllUsers(){
        System.out.println("getAllUsers =====");
        return (List<User>) userService.findAll();
    }

    @GetMapping(value ="/users/{id}")
    public User getUserById(@PathVariable("id") Long userId){
        System.out.println("getUserById =====");
        return userService.findById(userId);
    }

    @PostMapping(value = "/users", produces = "application/json")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public User saveUser(@Valid @RequestBody User user) {
        System.out.println("create / update - User =====");
//        if(userService.findUsersByEmail(user.getEmail()) != null){
//            return null;
//        }
        userService.save(user);
        return user;
    }

    @DeleteMapping(value ="/users/{id}")
    public boolean deleteUser(@PathVariable("id") Long userId) {
        System.out.println("deleteUser =====");
        return userService.delete(userId);
    }

    @GetMapping(value ="/users/searchEmail/{email}")
    public List<User> getUserByEmail(@PathVariable("email") String email){
        System.out.println("getUserByEmail =====");
        return userService.findUsersByEmail(email);
    }

    @GetMapping(value ="/users/searchName/{name}")
    public List<User> getUserByName(@PathVariable("name") String name){
        System.out.println("findUsersByName =====");
        return userService.findUsersByName(name);
    }
}
