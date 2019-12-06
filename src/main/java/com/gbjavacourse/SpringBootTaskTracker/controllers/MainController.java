package com.gbjavacourse.SpringBootTaskTracker.controllers;

import com.gbjavacourse.SpringBootTaskTracker.entities.FormTask;
import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.services.TaskService;
import com.gbjavacourse.SpringBootTaskTracker.services.TaskStatusService;
import com.gbjavacourse.SpringBootTaskTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private TaskService taskService;
    private UserService userService;
    private TaskStatusService taskStatusService;
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setTaskStatusService(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("formtask", new FormTask());
        model.addAttribute("users", userService.getAllUsersFromDB());
        model.addAttribute("taskstatuses", taskStatusService.getAllTaskStatusesFromDB());

        return "add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addTask(Model model,@ModelAttribute("formtask") FormTask formtask){
        Task task = new Task();
        task.setTitle(formtask.getTitle());
        task.setDescription(formtask.getDescription());
        task.setStatus(taskStatusService.getTaskStatusById(Long.parseLong(formtask.getStatus_id())));
        task.setExecuter(userService.getUserById(Long.parseLong(formtask.getExecuter_id())));
        task.setOwner(userService.getUserById(Long.parseLong(formtask.getOwner_id())));
        taskService.addTask(task);
        model.addAttribute("formtask", new FormTask());

        return "redirect:/show";
    }

    @RequestMapping(path = "/show")
    public String showAll(Model model, @ModelAttribute("formtask") FormTask formtask) {
        List<Task> tasks;
        if(formtask!=null&&formtask.getStatus_id()!=null&&formtask.getOwner_id()!=null
                &&!formtask.getStatus_id().equals("0")&&!formtask.getOwner_id().equals("0")){tasks = taskService.getAllTasksByStatusAndUserFromDB(formtask.getStatus_id(),formtask.getOwner_id());}
        else if(formtask!=null&&formtask.getStatus_id()!=null&&!formtask.getStatus_id().equals("0")){tasks = taskService.getAllTasksByStatusFromDB(formtask.getStatus_id());}
        else if(formtask!=null&&formtask.getOwner_id()!=null&&!formtask.getOwner_id().equals("0")){tasks = taskService.getAllTasksByStatusFromDB(formtask.getOwner_id());}
        else{
            tasks = taskService.getAllTasksFromDB();
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("formtask", new FormTask());
        model.addAttribute("users", userService.getAllUsersFromDB());
        model.addAttribute("taskstatuses", taskStatusService.getAllTaskStatusesFromDB());
        return "all";
    }

    @GetMapping("/task")
    public String showInfoPage(Model model, @RequestParam(name = "id", required = true) String id) {
        model.addAttribute("task", taskService.getTaskById(Long.parseLong(id)));
        return "info";
    }
}
