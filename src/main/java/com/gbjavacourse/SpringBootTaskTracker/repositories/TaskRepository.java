package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>, PagingAndSortingRepository<Task,Long>, JpaSpecificationExecutor<Task> {
    List<Task> findAll();
    List<Task> findByTitle(String title);
}