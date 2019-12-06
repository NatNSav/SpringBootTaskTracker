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

    public void printUsers()  {
        List<User> usersList = getAllUsersFromDB();
        for (User t:usersList) {
            t.printUser();
        }
    }

    public boolean isUserInDB(User user)  {
        return userRepository.isUserInDB(user);
    }

    public User getUserById(Long id){return  userRepository.getUserById(id);}

    public List<User> getAllUsersFromDB(){
        return userRepository.getAllUsersFromDB();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

}




