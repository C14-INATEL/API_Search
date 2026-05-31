package com.api_search.project.service;

import com.api_search.project.entity.User;
import com.api_search.project.excepetion.UserExcept;
import com.api_search.project.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    // constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) throws UserExcept {
        try{
            if (user.getPassword() == null) {
                throw new RuntimeException("PASSWORD IS NULL");
            }

            String hash = hashCrypt(user.getPassword());
            user.setPassword(hash);

            userRepository.save(user);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }
    public String hashCrypt(String password) throws UserExcept{
        try
        {
            String passwordCrypt;

            passwordCrypt = BCrypt.hashpw(password, BCrypt.gensalt());
            return  passwordCrypt;
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    public Boolean checkPassword(String password, String hashPassword) throws UserExcept {
        try {
            return BCrypt.checkpw(password, hashPassword);
        } catch (Exception e) {
            throw new UserExcept(e.getMessage());
        }
    }

    public Integer searchUserWithEmailPassword(String email, String password) throws UserExcept {
        try {

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            boolean validPassword = BCrypt.checkpw(password, user.getPassword());

            if (!validPassword) {
                throw new RuntimeException("Invalid Password");
            }

            return user.getId();

        } catch (Exception e) {
            throw new UserExcept(e.getMessage());
        }
    }

    public User searchById(Integer id) throws UserExcept{
        try {
            return userRepository.findById(id).orElse(null);
        }
        catch (Exception e){
            throw new UserExcept(e.getMessage());
        }
    }

    public List<User> searchAll() throws UserExcept{
        try {
            return userRepository.findAll();
        }
        catch (Exception e){
            throw new UserExcept(e.getMessage());
        }
    }

    public boolean existsByid(Integer id) throws UserExcept{
        try{
            return userRepository.existsById(id);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    public void deleteByid(Integer id) throws UserExcept{
        try{
            userRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new UserExcept(e.getMessage());
        }
    }

    public void deleteALL() throws UserExcept{
        try
        {
            userRepository.deleteAll();
        }
        catch (Exception e){
            throw new UserExcept(e.getMessage());
        }
    }

    public User update(Integer id, User userRequest) throws UserExcept{
        try
        {
            User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exist " + id));
            existing.setName(userRequest.getName());
            existing.setEmail(userRequest.getEmail());
            return userRepository.save(existing);
        }
        catch (Exception e){
            throw new UserExcept(e.getMessage());
        }
    }
}
