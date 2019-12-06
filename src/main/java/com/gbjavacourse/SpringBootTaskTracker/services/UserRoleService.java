package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.UserRole;
import com.gbjavacourse.SpringBootTaskTracker.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService  {
    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getAllUserRolesFromDB(){
        return userRoleRepository.getAllUserRolesFromDB();
    }

}
