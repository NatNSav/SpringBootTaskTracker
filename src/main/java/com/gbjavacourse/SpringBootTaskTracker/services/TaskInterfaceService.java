package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.interfaces.TaskRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TaskInterfaceService {
    private TaskRepositoryInterface taskRepository;

    @Autowired
    public void setTaskRepositoryInterface(TaskRepositoryInterface taskRepositoryInterface) {
        this.taskRepository = taskRepositoryInterface;
    }

    public void printTasks()  {
        List<Task> tasksList = getAllTasksFromDB();
        for (Task t:tasksList) {
            t.printTask();
        }
    }

    public boolean isTaskInDB(Task task)  {
        return taskRepository.findById(task.getId()).get()!=null;
    }

    public Task getTaskById(Long id){
        return  taskRepository.findById(id).get();
    }

    public List<Task> getAllTasksFromDB(){
        return taskRepository.findAll();
    }

    public Page<Task> getAllTasksFromDB(Specification<Task> spec, Pageable pageable){
        return taskRepository.findAll(spec, pageable);
    }

    /*public Page<Task> getAllTasksByStatusFromDB(String status_id){
        return taskRepository.findByStatus_id(Long.parseLong(status_id));
    }

    public Page<Task> getAllTasksByStatusAndUserFromDB(String status_id,String user_id){
        return taskRepository.findByStatus_idAndOwner_id(Long.parseLong(status_id), Long.parseLong(user_id));
    }

    public Page<Task> getAllTasksByUserFromDB(String user_id){
        return taskRepository.findByOwner_id(Long.parseLong(user_id));
    }*/

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskFromDBById(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteTaskFromDB(Task task) {
        taskRepository.delete(task);
    }
}




