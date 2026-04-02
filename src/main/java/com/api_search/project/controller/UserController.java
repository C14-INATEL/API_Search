package com.api_search.project.controller;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.User;
import com.api_search.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    //constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/{id}")
    public User searchById(@PathVariable Integer id){
        return userService.searchById(id);
    }

    @GetMapping
    public List<User> search(){
        return userService.searchAll();
    }

    @GetMapping("/{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return userService.existsByid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.deleteByid(id);
    }

    @DeleteMapping
    public void deleteALL(){
        userService.deleteALL();
    }

    @PutMapping("/{user_id}/update_name")
    public User updateName(@PathVariable Integer id, @RequestBody User user) {
        User user_update = userService.searchById(id);
        user_update.setName(user.getName());
        return userService.saveObject(user_update);
    }

    @PutMapping("/{user_id}/email")
    public User updateEmail(@PathVariable Integer id, @RequestBody User user) {
        User user_update = userService.searchById(id);
        user_update.setEmail(user.getEmail());
        return userService.saveObject(user_update);
    }
    @PutMapping("/{user_id}/update_passowrd")
    public User updatePassword(@PathVariable Integer id, @RequestBody User user) {
        User user_update = userService.searchById(id);
        user_update.setPassword(user.getPassword());
        return userService.saveObject(user_update);
    }

    @PutMapping("/{user_id}/update_token")
    public User updateToken(@PathVariable Integer id, @RequestBody User user) {
        User user_update = userService.searchById(id);
        user_update.setToken(user.getToken());
        return userService.saveObject(user_update);
    }

}
