package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {
    List<User> findAll();
    Optional<User> findById(Long id);
}