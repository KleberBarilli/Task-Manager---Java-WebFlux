package com.spring.taskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.taskmanager.models.Task;
import com.spring.taskmanager.repository.TaskRepository;

import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Mono<Task> insert(Task task) {
        return Mono.just(task).map(Task::insert).flatMap(this::save);
    }

    public Mono<List<Task>> list() {
        return Mono.just(taskRepository.findAll());
    }

    private Mono<Task> save(Task task) {
        return Mono.just(task).map(taskRepository::save);
    }

}
