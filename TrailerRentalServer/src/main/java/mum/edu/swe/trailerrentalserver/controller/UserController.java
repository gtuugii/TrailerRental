package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.User;
import mum.edu.swe.trailerrentalserver.exceptions.UserNotFoundException;
import mum.edu.swe.trailerrentalserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return (List<User>) userService.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping(value ="/user/{id}")
    public User getUserById(@PathVariable("id") Long userId, HttpServletResponse response){
        User user = null;
        try {
            System.out.println("getUserById =====");
            user = userService.findById(userId);
        }
        catch(Exception ex){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User Not Found", ex);
        }
        return user;
    }

    @PostMapping(value = "/user", produces = "application/json")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public User saveUser(@Valid @RequestBody User user) {
        System.out.println("create / update - User =====");
        userService.save(user);
        return user;
    }

    @DeleteMapping(value ="/user/{id}")
    public User deleteUser(@PathVariable("id") Long userId, HttpServletResponse response) {
        try{
            System.out.println("deleteUser =====");
            User user = userService.findById(userId);
            userService.delete(userId);
            return user;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
            //throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User Not Found", ex);
        }
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
