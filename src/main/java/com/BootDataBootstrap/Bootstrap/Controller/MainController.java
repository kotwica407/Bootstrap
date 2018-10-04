package com.BootDataBootstrap.Bootstrap.Controller;


import com.BootDataBootstrap.Bootstrap.model.Task;
import com.BootDataBootstrap.Bootstrap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(){return "index";}

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/all-tasks")
    @ResponseBody
    public String allTasks(){
        return taskService.findAll().toString();
    }

    @GetMapping("/save-task")
    @ResponseBody
    public String saveTask(@RequestParam String name, @RequestParam String desc){
        Task task = new Task(name, desc, new Date(), false);
        taskService.save(task);
        return "Task saved!!!";
    }

    @GetMapping("/delete-task")
    @ResponseBody
    public String deleteTask(@RequestParam int id){
        taskService.delete(id);
        return "Task deleted!!!";
    }
}
