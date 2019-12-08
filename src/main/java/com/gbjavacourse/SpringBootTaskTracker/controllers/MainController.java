package com.gbjavacourse.SpringBootTaskTracker.controllers;

import com.gbjavacourse.SpringBootTaskTracker.entities.FormTask;
import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import com.gbjavacourse.SpringBootTaskTracker.repositories.specifications.TaskSpecifications;
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

import javax.validation.Valid;

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
    public String addTask(Model model, @ModelAttribute("formtask") @Valid FormTask formtask, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
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

    @RequestMapping(path = "/show")
    public String showAll(Model model,@ModelAttribute("formtask")  FormTask formtask,
                          @RequestParam(defaultValue="1") Long pageNumber,
                          @RequestParam(required = false) String owner_id,
                          @RequestParam(required = false) String status_id) {
        Page<Task> tasks;
        int taskPerPage=5;
        if(pageNumber==null || pageNumber<1L)pageNumber=1L;

        Specification<Task> spec = Specification.where(null);
        //if(formtask.getStatus_id()!=null&&formtask.getStatus_id()!="")spec=spec.and(TaskSpecifications.statusEq(Long.parseLong(formtask.getStatus_id())));
        //if(formtask.getOwner_id()!=null&&formtask.getOwner_id()!="")spec=spec.and(TaskSpecifications.ownerEq(Long.parseLong(formtask.getOwner_id())));
        if(status_id!=null&&status_id!="")spec=spec.and(TaskSpecifications.statusEq(Long.parseLong(status_id)));
        if(owner_id!=null&&owner_id!="")spec=spec.and(TaskSpecifications.ownerEq(Long.parseLong(owner_id)));

        tasks =  taskService.getAllTasksFromDB(spec, PageRequest.of(pageNumber.intValue()-1,taskPerPage, Sort.Direction.ASC, "id"));

        model.addAttribute("tasks", tasks);
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
