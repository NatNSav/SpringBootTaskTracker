package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService  {
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
        return taskRepository.isTaskInDB(task);
    }

    public Task getTaskById(Long id){return  taskRepository.getTaskById(id);}

    public List<Task> getAllTasksFromDB(){
        return taskRepository.getAllTasksFromDB();
    }

    public List<Task> getAllTasksByStatusFromDB(String status_id){
        return taskRepository.getAllTasksByStatusFromDB(status_id);
    }

    public List<Task> getAllTasksByStatusAndUserFromDB(String status_id,String user_id){
        return taskRepository.getAllTasksByStatusAndUserFromDB(status_id, user_id);
    }

    public List<Task> getAllTasksByUserFromDB(String user_id){
        return taskRepository.getAllTasksByUserFromDB(user_id);
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public void deleteTaskFromDBById(Long id) {
        taskRepository.deleteTaskFromDBById(id);
    }

    public void deleteTaskFromDBByTitle(String title) {
        taskRepository.deleteTaskFromDBByTitle(title);
    }
}




