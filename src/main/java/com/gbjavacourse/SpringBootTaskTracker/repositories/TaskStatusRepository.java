package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
    public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long>, PagingAndSortingRepository<TaskStatus,Long>, JpaSpecificationExecutor<TaskStatus> {
        List<TaskStatus> findAll();
        Optional<TaskStatus> findById(Long id);
    }