package com.gbjavacourse.SpringBootTaskTracker.controllers;

import com.gbjavacourse.SpringBootTaskTracker.entities.FormTask;
import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.repositories.specifications.TaskSpecifications;
import com.gbjavacourse.SpringBootTaskTracker.services.TaskInterfaceService;
import com.gbjavacourse.SpringBootTaskTracker.services.TaskService;
import com.gbjavacourse.SpringBootTaskTracker.services.TaskStatusService;
import com.gbjavacourse.SpringBootTaskTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.invoke.empty.Empty;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    //private TaskService taskService;
    private TaskInterfaceService taskService;
    private UserService userService;
    private TaskStatusService taskStatusService;
    @Autowired
    //public void setTaskService(TaskService taskService) {
    public void setTaskService(TaskInterfaceService taskService) {
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
    public String addTask(Model model, @ModelAttribute("formtask") @Valid FormTask formtask, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("formtask", new FormTask());
            model.addAttribute("users", userService.getAllUsersFromDB());
            model.addAttribute("taskstatuses", taskStatusService.getAllTaskStatusesFromDB());
            return "add";
        }
        Task task = new Task();
        task.setTitle(formtask.getTitle());
        task.setDescription(formtask.getDescription());
        task.setStatus(taskStatusService.getTaskStatusById(Long.parseLong(formtask.getStatus_id())));
        task.setExecuter(userService.getUserById(Long.parseLong(formtask.getExecuter_id())));
        task.setOwner(userService.getUserById(Long.parseLong(formtask.getOwner_id())));
        taskService.addTask(task);
        return "redirect:/show";
    }

    @GetMapping(path = "/show")
    public String showAll(Model model, @RequestParam(required = false) String owner_id,
                          @RequestParam(required = false) String status_id,
                          @RequestParam(defaultValue="1") Long pageNumber) {
        Page<Task> tasks;
        int taskPerPage=5;
        Specification<Task> spec = Specification.where(null);
        if(pageNumber==null || pageNumber<1L)pageNumber=1L;
        if(status_id!=null&&status_id!="")spec=spec.and(TaskSpecifications.status_idEq(Long.parseLong(status_id)));
        if(owner_id!=null&&owner_id!="")spec=spec.and(TaskSpecifications.owner_idEq(Long.parseLong(owner_id)));
        /*if(formtask!=null&&formtask.getStatus_id()!=null&&formtask.getOwner_id()!=null
                &&!formtask.getStatus_id().equals("0")&&!formtask.getOwner_id().equals("0")){tasks = taskService.getAllTasksByStatusAndUserFromDB(formtask.getStatus_id(),formtask.getOwner_id());}
        else if(formtask!=null&&formtask.getStatus_id()!=null&&!formtask.getStatus_id().equals("0")){tasks = taskService.getAllTasksByStatusFromDB(formtask.getStatus_id());}
        else if(formtask!=null&&formtask.getOwner_id()!=null&&!formtask.getOwner_id().equals("0")){tasks = taskService.getAllTasksByStatusFromDB(formtask.getOwner_id());}
        else{
        */
            tasks =  taskService.getAllTasksFromDB(spec, PageRequest.of(pageNumber.intValue()-1,taskPerPage, Sort.Direction.ASC, "id"));
        //}
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
