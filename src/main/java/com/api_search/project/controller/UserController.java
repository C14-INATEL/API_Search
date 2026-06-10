package com.api_search.project.controller;

import com.api_search.project.entity.User;
import com.api_search.project.excepetion.UserExcept;
import com.api_search.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-search/users")
public class UserController {
    private UserService userService;

    //constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) throws UserExcept{
        try
        {
            userService.save(user);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User searchById(@PathVariable Integer id) throws UserExcept{
        try
        {
            return userService.searchById(id);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @GetMapping
    public List<User> search() throws UserExcept{
        try
        {
            return userService.searchAll();
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Integer searchUserWithEmailPassword(@RequestBody User user) throws UserExcept {
      try{
        return  userService.searchUserWithEmailPassword(user.getEmail(), user.getPassword());
      }
      catch (Exception e)
      {
          throw new UserExcept(e.getMessage());
      }
    }

    @GetMapping("/{id}/exist")
    public boolean existById(@PathVariable Integer id) throws UserExcept{
        try
        {
            return userService.existsByid(id);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws UserExcept{
        try
        {
            userService.deleteByid(id);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @DeleteMapping
    public void deleteALL() throws UserExcept{
        try
        {
            userService.deleteALL();
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) throws UserExcept{
        try
        {
            return userService.update(id, user);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }


}
