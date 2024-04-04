package com.spring.taskmanager.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.spring.taskmanager.models.Task;
import com.spring.taskmanager.repository.TaskCustomRepository;
import com.spring.taskmanager.repository.TaskRepository;

import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskCustomRepository taskCustomRepository;

    public TaskService(TaskRepository taskRepository, TaskCustomRepository taskCustomRepository) {
        this.taskRepository = taskRepository;
        this.taskCustomRepository = taskCustomRepository;
    }

    public Mono<Task> insert(Task task) {
        return Mono.just(task).map(Task::insert).flatMap(this::save);
    }

    public Page<Task> list(Task task, Integer pageNumber, Integer pageSize) {
        return taskCustomRepository.findPaginated(task, pageNumber, pageSize);
    }

    private Mono<Task> save(Task task) {
        return Mono.just(task).map(taskRepository::save);
    }

}
