package com.esprit.springjwt.service;

import com.esprit.springjwt.entity.User;
import com.esprit.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private UserRepository userRepository;


    //get All Users

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User changeEnabledUser(Long id) {
        User user = userRepository.findById(id).get();
        if (user.getEnabled()) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
      return  userRepository.save(user);
    }


    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public List<User> findByTypeFormationAndStatus(String typeFormation, Boolean status) {
        return userRepository.findByTypeFormationAndStatus(typeFormation, status);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
