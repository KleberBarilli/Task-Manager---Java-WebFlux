package com.spring.taskmanager.controllers.mappers;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.taskmanager.controllers.dtos.TaskDto;
import com.spring.taskmanager.models.Task;
import com.spring.taskmanager.models.TaskState;

@Component
public class TaskDtoMapper {

    public TaskDto mapper(Task task) {

        return Optional.ofNullable(task)
                .map(t -> {
                    TaskDto taskDto = new TaskDto();
                    taskDto.setId(t.getId());
                    taskDto.setTitle(t.getTitle());
                    taskDto.setDescription(t.getDescription());
                    taskDto.setPriority(t.getPriority());
                    taskDto.setState(t.getState());
                    return taskDto;
                })
                .orElse(null);
    }

    public Task convert(String id, String title, int priority, String description, TaskState state) {
        return Task.builder().withId(id).withTitle(title).withPriority(priority).withDescription(description)
                .withState(state).build();
    }

    public Task mapper(TaskDto taskDto) {
        return Optional.ofNullable(taskDto)
                .map(t -> Task.builder().withId(t.getId()).withTitle(t.getTitle()).withDescription(t.getDescription())
                        .withPriority(t.getPriority())
                        .withState(t.getState()).build())
                .orElse(null);

    }

    public List<TaskDto> mapper(List<Task> tasks) {
        return Optional.ofNullable(tasks).map(array -> array.stream().map(this::mapper).collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
