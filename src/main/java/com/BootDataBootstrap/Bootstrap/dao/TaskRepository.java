package com.BootDataBootstrap.Bootstrap.dao;

import com.BootDataBootstrap.Bootstrap.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Integer> {

}
