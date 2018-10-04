package com.BootDataBootstrap.Bootstrap.service;

import com.BootDataBootstrap.Bootstrap.dao.TaskRepository;
import com.BootDataBootstrap.Bootstrap.model.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findTask(int id){
        Optional<Task> task = taskRepository.findById(id);
        return task;
    }
    public List<Task> findAll(){
        List<Task> tasks = new ArrayList<>();
        for(Task task : taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void delete(int id){
        taskRepository.deleteById(id);
    }
}
