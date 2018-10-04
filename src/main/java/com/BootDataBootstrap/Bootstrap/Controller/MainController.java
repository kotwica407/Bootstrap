package com.BootDataBootstrap.Bootstrap.Controller;


import com.BootDataBootstrap.Bootstrap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String allTasks(){
        return taskService.findAll().toString();
    }
}
