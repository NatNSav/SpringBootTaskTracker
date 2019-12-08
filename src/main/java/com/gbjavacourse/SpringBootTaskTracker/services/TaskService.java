package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepositoryInterface(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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




