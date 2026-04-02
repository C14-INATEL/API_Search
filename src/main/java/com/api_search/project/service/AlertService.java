package com.api_search.project.service;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.User;
import com.api_search.project.repository.AlertRepository;
import com.api_search.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    private AlertRepository alertRepository;

    // constructor
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void save(Alert alert){
        alertRepository.save(alert);
    }

    public Alert saveObject(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert searchById(Integer id){
        return alertRepository.findById(id).orElse(null);
    }

    public List<Alert> searchAll(){
        return alertRepository.findAll();
    }

    public boolean existsByid(Integer id) {
        return alertRepository.existsById(id);
    }

    public void deleteByid(Integer id){
        alertRepository.deleteById(id);
    }

    public void deleteALL(){
        alertRepository.deleteAll();
    }
}
