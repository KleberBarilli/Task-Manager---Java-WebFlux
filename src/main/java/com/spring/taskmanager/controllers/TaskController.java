package com.spring.taskmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.taskmanager.controllers.dtos.TaskDto;
import com.spring.taskmanager.controllers.mappers.TaskDtoMapper;
import com.spring.taskmanager.models.Task;
import com.spring.taskmanager.services.TaskService;

import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<List<TaskDto>> getTasks() {
        return taskService.list().map(mapper::mapper);
    }

    @PostMapping()
    public Mono<TaskDto> createTask(@RequestBody Task task) {
        return taskService.insert(task).map(mapper::mapper);
    }

}
