package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.TaskStatus;
import com.gbjavacourse.SpringBootTaskTracker.entities.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UserRoleRepository() {
    }

    public List<UserRole> getAllUserRolesFromDB() {
        List<UserRole> res = entityManager.createQuery("SELECT a from UserRole a", UserRole.class).getResultList();
        return res;
    }
}