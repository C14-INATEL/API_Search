package com.api_search.project.controller;

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

    @PostMapping("/save")
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

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }


}
