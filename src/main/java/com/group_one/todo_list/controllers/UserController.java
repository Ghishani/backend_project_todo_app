package com.group_one.todo_list.controllers;

import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.models.User;
import com.group_one.todo_list.models.UserDTO;
import com.group_one.todo_list.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping //localhost:8080/users
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")//localhost:8080/users/1
    public ResponseEntity<User> getUsersById(@PathVariable Long id){
        Optional<User> foundUser = userService.getUserById(id);
        if (foundUser.isPresent()){
            return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping    //localhost:8080/users
    public ResponseEntity<User> addNewUser(@RequestBody UserDTO userDTO){
        User newUser = userService.createUser(userDTO);

        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{id}") //localhost:8080/users/1
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<User> foundUser = userService.getUserById(id);
        if (foundUser.isPresent()) {
            User updatedUser = userService.updateUser(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    TODO: Add paths for filtering


//    we are deleting user here because it will be able to delete the task that is linked to them as well

    @DeleteMapping(value = "/{id}") //localhost:8080/users/1
    public ResponseEntity<Long> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }








}
