package com.api_search.project.service;

import com.api_search.project.entity.Leak;
import com.api_search.project.entity.Leakeds_email;
import com.api_search.project.repository.LeakRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class LeakService {
    private LeakRepository leakRepository;
    private final Leakeds_emailService leakeds_emailService;

    //constructor
    public LeakService(LeakRepository leakRepository,
                       Leakeds_emailService leakeds_emailService) {
        this.leakRepository = leakRepository;
        this.leakeds_emailService = leakeds_emailService;
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