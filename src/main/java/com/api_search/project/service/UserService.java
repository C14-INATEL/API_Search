package com.api_search.project.service;

import com.api_search.project.entity.User;
import com.api_search.project.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    // constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        if (user.getPassword() == null) {
            throw new RuntimeException("PASSWORD IS NULL");
        }

        String hash = hashCrypt(user.getPassword());
        user.setPassword(hash);

        userRepository.save(user);
    }
    public String hashCrypt(String password){
        String passwordCrypt;

        passwordCrypt = BCrypt.hashpw(password, BCrypt.gensalt());
        return  passwordCrypt;
    }

    public Boolean checkPassword(String password){
        String passwordCrypt = hashCrypt(password);
        boolean isValid = BCrypt.checkpw(password,passwordCrypt);
        if (isValid){
            return true;
        }else
        {
            return false;
        }
    }
    public User searchById(Integer id){
        return userRepository.findById(id).orElse(null);
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

    public User update(Integer id, User userRequest) {
        User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exist " + id));
        existing.setName(userRequest.getName());
        existing.setEmail(userRequest.getEmail());
        return userRepository.save(existing);
    }
}
