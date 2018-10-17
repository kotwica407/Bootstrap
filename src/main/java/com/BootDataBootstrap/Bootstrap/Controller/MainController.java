package com.BootDataBootstrap.Bootstrap.Controller;


import com.BootDataBootstrap.Bootstrap.model.Task;
import com.BootDataBootstrap.Bootstrap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(HttpServletRequest request){
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_HOME");
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/all-tasks")
    public String allTasks(HttpServletRequest request){
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_TASKS");
        return "index";
    }

    @GetMapping("/new-task")
    public String newTask(Model model, HttpServletRequest request){
        model.addAttribute("task", new Task());
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_NEW");
        return "index";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        task.setDateCreated(new Date());
        taskService.save(task);
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_TASKS");
        return "index";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam int id, Model model, HttpServletRequest request){
        model.addAttribute("task", taskService.findTask(id));
        //request.setAttribute("tasks",taskService.findTask(id));
        request.setAttribute("mode","MODE_UPDATE");
        return "index";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.delete(id);
        return "index";
    }


    @ResponseBody
    @GetMapping("/secure/admin")
    public String securedHelloAdmin(){
        return "Hello admin";
    }

    @ResponseBody
    @GetMapping("/secure/user")
    public String securedHelloUser(){
        return "Hello user";
    }


}
