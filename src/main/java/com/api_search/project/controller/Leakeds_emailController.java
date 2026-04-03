package com.api_search.project.controller;


import com.api_search.project.entity.Leakeds_email;
import com.api_search.project.service.Leakeds_emailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leakeds_email")
public class Leakeds_emailController{
    private Leakeds_emailService leakeds_emailService;

    // Constructor

    public Leakeds_emailController(Leakeds_emailService leakeds_emailService){
        this.leakeds_emailService = leakeds_emailService;
    }

    @PostMapping
    public void save(@RequestBody Leakeds_email Leakeds_email){
        leakeds_emailService.save(Leakeds_email);
    }

    @GetMapping("{id}")
    public Leakeds_email searchById(@PathVariable Integer id){
        return leakeds_emailService.searchById(id);
    }

    @GetMapping
    public List<Leakeds_email> search(){
        return leakeds_emailService.searchAll();
    }

    @GetMapping("{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return leakeds_emailService.existByid(id);
    }

    @PutMapping("{id}/update_email_id")
    public Leakeds_email updateEmail_id(@PathVariable Integer id, @RequestBody Leakeds_email leakeds_email){
        Leakeds_email leakeds_email_update = leakeds_emailService.searchById(id);
        leakeds_email_update.setEmail_id(leakeds_email.getEmail_id());
        return leakeds_emailService.saveObject(leakeds_email_update);
    }

}
