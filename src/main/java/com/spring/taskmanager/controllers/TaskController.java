package com.spring.taskmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.taskmanager.controllers.dtos.TaskDto;
import com.spring.taskmanager.controllers.mappers.TaskDtoMapper;
import com.spring.taskmanager.models.Task;
import com.spring.taskmanager.models.TaskState;
import com.spring.taskmanager.services.TaskService;

import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskDtoMapper mapper;

    public TaskController(TaskService taskService, TaskDtoMapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @GetMapping()
    public Page<TaskDto> getTasks(@RequestParam(required = false) String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") int priority,
            @RequestParam(required = false) TaskState taskState,
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize

    ) {
        return taskService
                .list(mapper.convert(id, title, priority, description, taskState), pageNumber, pageSize)
                .map(mapper::mapper);
    }

    @PostMapping()
    public Mono<TaskDto> createTask(@RequestBody Task task) {
        return taskService.insert(task).map(mapper::mapper);
    }

}
