package com.gbjavacourse.SpringBootTaskTracker.repositories;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
        public class TaskRepository {
        @PersistenceContext
        private EntityManager entityManager;

        public TaskRepository() {
        }

        public boolean isTaskInDB(Task task)  {
            Task taskRes = entityManager.find(Task.class, task.getId());
            return taskRes!=null;
        }

        public Task getTaskById(Long id)  {
            Task taskRes = entityManager.find(Task.class, id);
            return taskRes;
        }

        public List<Task> getAllTasksFromDB(){
            List<Task> res = entityManager.createQuery("SELECT a from Task a", Task.class).getResultList();
            return res;
        }

        public List<Task> getAllTasksByStatusFromDB(String status_id){
            List<Task> res = entityManager.createQuery("SELECT a from Task a where a.status=" +status_id, Task.class).getResultList();
            return res;
        }

    public List<Task> getAllTasksByStatusAndUserFromDB(String status_id,String user_id){
        List<Task> res = entityManager.createQuery("SELECT a from Task a where a.status=" +status_id + " and a.owner="+user_id, Task.class).getResultList();
        return res;
    }

        public List<Task> getAllTasksByUserFromDB(String user_id){
            List<Task> res = entityManager.createQuery("SELECT a from Task a where a.owner=" +user_id, Task.class).getResultList();
            return res;
        }

        public void addTask(Task task) {
            entityManager.persist(task);
        }

        public void deleteTaskFromDBById(Long id) {
            for (Task task : entityManager.createQuery("SELECT a from Task a WHERE a.id = '" + id + "'", Task.class).getResultList()) {
                entityManager.remove(task);
            }
        }

        public void deleteTaskFromDBByTitle(String title) {
            for (Task task : entityManager.createQuery("SELECT a from Task a WHERE a.title = '" + title + "'", Task.class).getResultList()) {
                entityManager.remove(task);
            }
        }
    }





