package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Leak;
import com.api_search.project.repository.LeakRepository;

import java.util.List;

public class LeakService {
    private LeakRepository leakRepository;

    //contructor

    public LeakService(LeakRepository leakRepository) {
        this.leakRepository = leakRepository;
    }

    public void save(Leak leak){
        leakRepository.save(leak);
    }

    public Leak saveObject(Leak leak) {
        return leakRepository.save(leak);
    }

    public Leak searchById(Integer id){
        return leakRepository.findById(id).orElse(null);
    }

    public List<Leak> searchAll(){
        return leakRepository.findAll();
    }

    public boolean existsByid(Integer id) {
        return leakRepository.existsById(id);
    }

    public void deleteByid(Integer id){
        leakRepository.deleteById(id);
    }

    public void deleteALL(){
        leakRepository.deleteAll();
    }
}
