package com.spring.taskmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.taskmanager.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}
