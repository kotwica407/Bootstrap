package com.BootDataBootstrap.Bootstrap.Controller;


import com.BootDataBootstrap.Bootstrap.model.Task;
import com.BootDataBootstrap.Bootstrap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String newTask(HttpServletRequest request){
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_NEW");
        return "index";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute Task task, HttpServletRequest request){
        taskService.save(task);
        request.setAttribute("tasks",taskService.findAll());
        request.setAttribute("mode","MODE_TASKS");
        return "index";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("tasks",taskService.findTask(id));
        request.setAttribute("mode","MODE_UPDATE");
        return "index";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.delete(id);
        return "index";
    }

    @GetMapping("/save-task")
    @ResponseBody
    public String saveTask(@RequestParam String name, @RequestParam String desc){
        Task task = new Task(name, desc, new Date(), false);
        taskService.save(task);
        return "Task saved!!!";
    }

}
