package com.gbjavacourse.SpringBootTaskTracker.services;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.entities.TaskStatus;
import com.gbjavacourse.SpringBootTaskTracker.repositories.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService  {
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    public void setTaskStatusRepository(TaskStatusRepository taskStatusRepository) {

        this.taskStatusRepository = taskStatusRepository;
    }

    public List<TaskStatus> getAllTaskStatusesFromDB(){
        return taskStatusRepository.findAll();
    }

    public TaskStatus getTaskStatusById(Long id){return  taskStatusRepository.findById(id).get();}
}
