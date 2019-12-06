package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository() {
    }

    public boolean isUserInDB(User user)  {
        User userRes = entityManager.find(User.class, user.getId());
        return userRes!=null;
    }

    public User getUserById(Long id)  {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsersFromDB(){
        List<User> res = entityManager.createQuery("SELECT a from User a", User.class).getResultList();
        return res;
    }

    public void addUser(User user) {
        entityManager.persist(user);
    }
}





