package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.User;
import com.gbjavacourse.SpringBootTaskTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUserById(Long id){return  userRepository.findById(id).get();}

    public List<User> getAllUsersFromDB(){
        return userRepository.findAll();
    }

}




