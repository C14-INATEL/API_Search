package com.api_search.project.service;

import com.api_search.project.dto.UserDashboardDTO;
import com.api_search.project.entity.User;
import com.api_search.project.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    // constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        String password;
        String hash;

        password = user.getPassword();
        hash = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hash);

        userRepository.save(user);
    }

    public User saveObject(User user) {
        String password;
        String hash;

        password = user.getPassword();
        hash = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hash);

        return userRepository.save(user);
    }

    public User searchById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UserDashboardDTO> getDashboard(Integer userId) {
        return userRepository.buscarDashboard(userId);
    }

    public List<User> searchAll(){

        return userRepository.findAll();
    }

    public boolean existsByid(Integer id) {
        return userRepository.existsById(id);
    }

    public void deleteByid(Integer id){
        userRepository.deleteById(id);
    }

    public void deleteALL(){
        userRepository.deleteAll();
    }

}
