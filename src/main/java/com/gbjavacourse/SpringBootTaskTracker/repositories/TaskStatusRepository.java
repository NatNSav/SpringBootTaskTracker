package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.entities.TaskStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskStatusRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public TaskStatusRepository() {
    }

    public List<TaskStatus> getAllTaskStatusesFromDB() {
        List<TaskStatus> res = entityManager.createQuery("SELECT a from TaskStatus a", TaskStatus.class).getResultList();
        return res;
    }

    public TaskStatus getTaskStatusById(Long id)  {
        TaskStatus taskRes = entityManager.find(TaskStatus.class, id);
        return taskRes;
    }
}