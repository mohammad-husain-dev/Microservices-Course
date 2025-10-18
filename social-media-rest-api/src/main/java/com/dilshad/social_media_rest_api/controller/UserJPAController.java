package com.dilshad.social_media_rest_api.controller;

import com.dilshad.social_media_rest_api.Dao.UserDaoService;
import com.dilshad.social_media_rest_api.beans.User;
import com.dilshad.social_media_rest_api.exceptions.UserNotFoundException;
import com.dilshad.social_media_rest_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {

//    @Autowired
//    UserDaoService userDaoService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users=userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findUserById(@PathVariable int id) {
        User user=userRepository.findById(id).orElse(null);
        if(user==null) {
            throw new UserNotFoundException("id:"+id);
        }
        EntityModel<User> userModel = EntityModel.of(user);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).findAllUsers());
        userModel.add(link.withRel("all-users"));
        userModel.add(linkTo(methodOn(this.getClass()).deleteUserById(id)).withRel("delete-user"));
        return userModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser=userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(savedUser.getId())
                                                  .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
