package com.dilshad.social_media_rest_api.controller;

import com.dilshad.social_media_rest_api.Dao.UserDaoService;
import com.dilshad.social_media_rest_api.beans.User;
import com.dilshad.social_media_rest_api.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping("users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users=userDaoService.finaAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        User user=userDaoService.findOne(id);
        if(user==null) {
            throw new UserNotFoundException("id:"+id);
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser=userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(savedUser.getId())
                                                  .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        userDaoService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
