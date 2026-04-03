package com.api_search.project.service;

import com.api_search.project.entity.Leakeds_email;
import com.api_search.project.entity.User;
import com.api_search.project.repository.Leakeds_emailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Leakeds_emailService {
    private Leakeds_emailRepository leakeds_emailRepository;

    // Constructor
    public Leakeds_emailService(Leakeds_emailRepository leakeds_emailRepository) {
        this.leakeds_emailRepository = leakeds_emailRepository;
    }

    public Leakeds_email saveObject(Leakeds_email leakeds_email) {
        return leakeds_emailRepository.save(leakeds_email);
    }

    public void save(Leakeds_email leakeds_email){
        leakeds_emailRepository.save(leakeds_email);
    }

    public Leakeds_email searchById(Integer id){
        return leakeds_emailRepository.findById(id).orElse(null);
    }

    public List<Leakeds_email> searchAll(){
        return leakeds_emailRepository.findAll();
    }

    public boolean existByid(Integer id){
        return  leakeds_emailRepository.existsById(id);
    }

}
