package com.api_search.project.controller;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.Leak;
import com.api_search.project.entity.User;
import com.api_search.project.service.LeakService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leak")
public class LeakController {
    private LeakService leakService;

    //constructor
    public LeakController(LeakService leakService) {
        this.leakService = leakService;
    }
    @GetMapping
    public List<Leak> search(){
        return leakService.searchAll();
    }

    @PostMapping
    public void save(@RequestBody Leak leak){
        leakService.searchAndSave(leak.getAccountMonitored());
        leakService.save(leak);
    }

    @GetMapping("/{id}")
    public Leak searchById(@PathVariable Integer id){
        return leakService.searchById(id);
    }

    @GetMapping("/{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return leakService.existsByid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        leakService.deleteByid(id);
    }

    @DeleteMapping
    public void deleteALL(){
        leakService.deleteALL();
    }

    @PutMapping("/{user_id}/update_accountMonitored")
    public Leak updateAccounMonitored(@PathVariable Integer id, @RequestBody Leak leak) {
        Leak leak_update = leakService.searchById(id);
        leak_update.setAccountMonitored(leak_update.getAccountMonitored());
        return leakService.saveObject(leak_update);
    }

    @PutMapping("/{user_id}/update_register")
    public Leak updateRegister(@PathVariable Integer id, @RequestBody Leak leak) {
        Leak leak_update = leakService.searchById(id);
        leak_update.setRegister(leak_update.getRegister());
        return leakService.saveObject(leak_update);
    }
}


